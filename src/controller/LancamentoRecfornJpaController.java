/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Fornecedor;
import model.Lancamento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.LancamentoRecforn;

/**
 *
 * @author Maicon
 */
public class LancamentoRecfornJpaController implements Serializable {

    public LancamentoRecfornJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LancamentoRecforn lancamentoRecforn) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Lancamento lancamentoOrphanCheck = lancamentoRecforn.getLancamento();
        if (lancamentoOrphanCheck != null) {
            LancamentoRecforn oldLancamentoRecfornOfLancamento = lancamentoOrphanCheck.getLancamentoRecforn();
            if (oldLancamentoRecfornOfLancamento != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Lancamento " + lancamentoOrphanCheck + " already has an item of type LancamentoRecforn whose lancamento column cannot be null. Please make another selection for the lancamento field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor codfornec = lancamentoRecforn.getCodfornec();
            if (codfornec != null) {
                codfornec = em.getReference(codfornec.getClass(), codfornec.getCodfornec());
                lancamentoRecforn.setCodfornec(codfornec);
            }
            Lancamento lancamento = lancamentoRecforn.getLancamento();
            if (lancamento != null) {
                lancamento = em.getReference(lancamento.getClass(), lancamento.getCodlanc());
                lancamentoRecforn.setLancamento(lancamento);
            }
            em.persist(lancamentoRecforn);
            if (codfornec != null) {
                codfornec.getLancamentoRecfornList().add(lancamentoRecforn);
                codfornec = em.merge(codfornec);
            }
            if (lancamento != null) {
                lancamento.setLancamentoRecforn(lancamentoRecforn);
                lancamento = em.merge(lancamento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLancamentoRecforn(lancamentoRecforn.getCodlanc()) != null) {
                throw new PreexistingEntityException("LancamentoRecforn " + lancamentoRecforn + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LancamentoRecforn lancamentoRecforn) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LancamentoRecforn persistentLancamentoRecforn = em.find(LancamentoRecforn.class, lancamentoRecforn.getCodlanc());
            Fornecedor codfornecOld = persistentLancamentoRecforn.getCodfornec();
            Fornecedor codfornecNew = lancamentoRecforn.getCodfornec();
            Lancamento lancamentoOld = persistentLancamentoRecforn.getLancamento();
            Lancamento lancamentoNew = lancamentoRecforn.getLancamento();
            List<String> illegalOrphanMessages = null;
            if (lancamentoNew != null && !lancamentoNew.equals(lancamentoOld)) {
                LancamentoRecforn oldLancamentoRecfornOfLancamento = lancamentoNew.getLancamentoRecforn();
                if (oldLancamentoRecfornOfLancamento != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Lancamento " + lancamentoNew + " already has an item of type LancamentoRecforn whose lancamento column cannot be null. Please make another selection for the lancamento field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codfornecNew != null) {
                codfornecNew = em.getReference(codfornecNew.getClass(), codfornecNew.getCodfornec());
                lancamentoRecforn.setCodfornec(codfornecNew);
            }
            if (lancamentoNew != null) {
                lancamentoNew = em.getReference(lancamentoNew.getClass(), lancamentoNew.getCodlanc());
                lancamentoRecforn.setLancamento(lancamentoNew);
            }
            lancamentoRecforn = em.merge(lancamentoRecforn);
            if (codfornecOld != null && !codfornecOld.equals(codfornecNew)) {
                codfornecOld.getLancamentoRecfornList().remove(lancamentoRecforn);
                codfornecOld = em.merge(codfornecOld);
            }
            if (codfornecNew != null && !codfornecNew.equals(codfornecOld)) {
                codfornecNew.getLancamentoRecfornList().add(lancamentoRecforn);
                codfornecNew = em.merge(codfornecNew);
            }
            if (lancamentoOld != null && !lancamentoOld.equals(lancamentoNew)) {
                lancamentoOld.setLancamentoRecforn(null);
                lancamentoOld = em.merge(lancamentoOld);
            }
            if (lancamentoNew != null && !lancamentoNew.equals(lancamentoOld)) {
                lancamentoNew.setLancamentoRecforn(lancamentoRecforn);
                lancamentoNew = em.merge(lancamentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lancamentoRecforn.getCodlanc();
                if (findLancamentoRecforn(id) == null) {
                    throw new NonexistentEntityException("The lancamentoRecforn with id " + id + " no longer exists.");
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
            LancamentoRecforn lancamentoRecforn;
            try {
                lancamentoRecforn = em.getReference(LancamentoRecforn.class, id);
                lancamentoRecforn.getCodlanc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lancamentoRecforn with id " + id + " no longer exists.", enfe);
            }
            Fornecedor codfornec = lancamentoRecforn.getCodfornec();
            if (codfornec != null) {
                codfornec.getLancamentoRecfornList().remove(lancamentoRecforn);
                codfornec = em.merge(codfornec);
            }
            Lancamento lancamento = lancamentoRecforn.getLancamento();
            if (lancamento != null) {
                lancamento.setLancamentoRecforn(null);
                lancamento = em.merge(lancamento);
            }
            em.remove(lancamentoRecforn);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LancamentoRecforn> findLancamentoRecfornEntities() {
        return findLancamentoRecfornEntities(true, -1, -1);
    }

    public List<LancamentoRecforn> findLancamentoRecfornEntities(int maxResults, int firstResult) {
        return findLancamentoRecfornEntities(false, maxResults, firstResult);
    }

    private List<LancamentoRecforn> findLancamentoRecfornEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LancamentoRecforn.class));
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

    public LancamentoRecforn findLancamentoRecforn(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LancamentoRecforn.class, id);
        } finally {
            em.close();
        }
    }

    public int getLancamentoRecfornCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LancamentoRecforn> rt = cq.from(LancamentoRecforn.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
