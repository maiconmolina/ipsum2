/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Lancamento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Caixa;

/**
 *
 * @author Luis
 */
public class CaixaJpaController implements Serializable {

    public CaixaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Caixa caixa) throws PreexistingEntityException, Exception {
        if (caixa.getLancamentoList() == null) {
            caixa.setLancamentoList(new ArrayList<Lancamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Lancamento> attachedLancamentoList = new ArrayList<Lancamento>();
            for (Lancamento lancamentoListLancamentoToAttach : caixa.getLancamentoList()) {
                lancamentoListLancamentoToAttach = em.getReference(lancamentoListLancamentoToAttach.getClass(), lancamentoListLancamentoToAttach.getCodlanc());
                attachedLancamentoList.add(lancamentoListLancamentoToAttach);
            }
            caixa.setLancamentoList(attachedLancamentoList);
            em.persist(caixa);
            for (Lancamento lancamentoListLancamento : caixa.getLancamentoList()) {
                Caixa oldCodcaixaOfLancamentoListLancamento = lancamentoListLancamento.getCodcaixa();
                lancamentoListLancamento.setCodcaixa(caixa);
                lancamentoListLancamento = em.merge(lancamentoListLancamento);
                if (oldCodcaixaOfLancamentoListLancamento != null) {
                    oldCodcaixaOfLancamentoListLancamento.getLancamentoList().remove(lancamentoListLancamento);
                    oldCodcaixaOfLancamentoListLancamento = em.merge(oldCodcaixaOfLancamentoListLancamento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCaixa(caixa.getCodcaixa()) != null) {
                throw new PreexistingEntityException("Caixa " + caixa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Caixa caixa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caixa persistentCaixa = em.find(Caixa.class, caixa.getCodcaixa());
            List<Lancamento> lancamentoListOld = persistentCaixa.getLancamentoList();
            List<Lancamento> lancamentoListNew = caixa.getLancamentoList();
            List<Lancamento> attachedLancamentoListNew = new ArrayList<Lancamento>();
            for (Lancamento lancamentoListNewLancamentoToAttach : lancamentoListNew) {
                lancamentoListNewLancamentoToAttach = em.getReference(lancamentoListNewLancamentoToAttach.getClass(), lancamentoListNewLancamentoToAttach.getCodlanc());
                attachedLancamentoListNew.add(lancamentoListNewLancamentoToAttach);
            }
            lancamentoListNew = attachedLancamentoListNew;
            caixa.setLancamentoList(lancamentoListNew);
            caixa = em.merge(caixa);
            for (Lancamento lancamentoListOldLancamento : lancamentoListOld) {
                if (!lancamentoListNew.contains(lancamentoListOldLancamento)) {
                    lancamentoListOldLancamento.setCodcaixa(null);
                    lancamentoListOldLancamento = em.merge(lancamentoListOldLancamento);
                }
            }
            for (Lancamento lancamentoListNewLancamento : lancamentoListNew) {
                if (!lancamentoListOld.contains(lancamentoListNewLancamento)) {
                    Caixa oldCodcaixaOfLancamentoListNewLancamento = lancamentoListNewLancamento.getCodcaixa();
                    lancamentoListNewLancamento.setCodcaixa(caixa);
                    lancamentoListNewLancamento = em.merge(lancamentoListNewLancamento);
                    if (oldCodcaixaOfLancamentoListNewLancamento != null && !oldCodcaixaOfLancamentoListNewLancamento.equals(caixa)) {
                        oldCodcaixaOfLancamentoListNewLancamento.getLancamentoList().remove(lancamentoListNewLancamento);
                        oldCodcaixaOfLancamentoListNewLancamento = em.merge(oldCodcaixaOfLancamentoListNewLancamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = caixa.getCodcaixa();
                if (findCaixa(id) == null) {
                    throw new NonexistentEntityException("The caixa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Caixa caixa;
            try {
                caixa = em.getReference(Caixa.class, id);
                caixa.getCodcaixa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caixa with id " + id + " no longer exists.", enfe);
            }
            List<Lancamento> lancamentoList = caixa.getLancamentoList();
            for (Lancamento lancamentoListLancamento : lancamentoList) {
                lancamentoListLancamento.setCodcaixa(null);
                lancamentoListLancamento = em.merge(lancamentoListLancamento);
            }
            em.remove(caixa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Caixa> findCaixaEntities() {
        return findCaixaEntities(true, -1, -1);
    }

    public List<Caixa> findCaixaEntities(int maxResults, int firstResult) {
        return findCaixaEntities(false, maxResults, firstResult);
    }

    private List<Caixa> findCaixaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Caixa.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Caixa findCaixa(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Caixa.class, id);
        } finally {
            em.close();
        }
    }

    public int getCaixaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Caixa> rt = cq.from(Caixa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
