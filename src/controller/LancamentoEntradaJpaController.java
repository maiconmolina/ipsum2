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
import model.Lancamento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.LancamentoEntrada;

/**
 *
 * @author Luis
 */
public class LancamentoEntradaJpaController implements Serializable {

    public LancamentoEntradaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LancamentoEntrada lancamentoEntrada) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Lancamento lancamentoOrphanCheck = lancamentoEntrada.getLancamento();
        if (lancamentoOrphanCheck != null) {
            LancamentoEntrada oldLancamentoEntradaOfLancamento = lancamentoOrphanCheck.getLancamentoEntrada();
            if (oldLancamentoEntradaOfLancamento != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Lancamento " + lancamentoOrphanCheck + " already has an item of type LancamentoEntrada whose lancamento column cannot be null. Please make another selection for the lancamento field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lancamento lancamento = lancamentoEntrada.getLancamento();
            if (lancamento != null) {
                lancamento = em.getReference(lancamento.getClass(), lancamento.getCodlanc());
                lancamentoEntrada.setLancamento(lancamento);
            }
            em.persist(lancamentoEntrada);
            if (lancamento != null) {
                lancamento.setLancamentoEntrada(lancamentoEntrada);
                lancamento = em.merge(lancamento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLancamentoEntrada(lancamentoEntrada.getCodlanc()) != null) {
                throw new PreexistingEntityException("LancamentoEntrada " + lancamentoEntrada + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LancamentoEntrada lancamentoEntrada) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LancamentoEntrada persistentLancamentoEntrada = em.find(LancamentoEntrada.class, lancamentoEntrada.getCodlanc());
            Lancamento lancamentoOld = persistentLancamentoEntrada.getLancamento();
            Lancamento lancamentoNew = lancamentoEntrada.getLancamento();
            List<String> illegalOrphanMessages = null;
            if (lancamentoNew != null && !lancamentoNew.equals(lancamentoOld)) {
                LancamentoEntrada oldLancamentoEntradaOfLancamento = lancamentoNew.getLancamentoEntrada();
                if (oldLancamentoEntradaOfLancamento != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Lancamento " + lancamentoNew + " already has an item of type LancamentoEntrada whose lancamento column cannot be null. Please make another selection for the lancamento field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (lancamentoNew != null) {
                lancamentoNew = em.getReference(lancamentoNew.getClass(), lancamentoNew.getCodlanc());
                lancamentoEntrada.setLancamento(lancamentoNew);
            }
            lancamentoEntrada = em.merge(lancamentoEntrada);
            if (lancamentoOld != null && !lancamentoOld.equals(lancamentoNew)) {
                lancamentoOld.setLancamentoEntrada(null);
                lancamentoOld = em.merge(lancamentoOld);
            }
            if (lancamentoNew != null && !lancamentoNew.equals(lancamentoOld)) {
                lancamentoNew.setLancamentoEntrada(lancamentoEntrada);
                lancamentoNew = em.merge(lancamentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lancamentoEntrada.getCodlanc();
                if (findLancamentoEntrada(id) == null) {
                    throw new NonexistentEntityException("The lancamentoEntrada with id " + id + " no longer exists.");
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
            LancamentoEntrada lancamentoEntrada;
            try {
                lancamentoEntrada = em.getReference(LancamentoEntrada.class, id);
                lancamentoEntrada.getCodlanc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lancamentoEntrada with id " + id + " no longer exists.", enfe);
            }
            Lancamento lancamento = lancamentoEntrada.getLancamento();
            if (lancamento != null) {
                lancamento.setLancamentoEntrada(null);
                lancamento = em.merge(lancamento);
            }
            em.remove(lancamentoEntrada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LancamentoEntrada> findLancamentoEntradaEntities() {
        return findLancamentoEntradaEntities(true, -1, -1);
    }

    public List<LancamentoEntrada> findLancamentoEntradaEntities(int maxResults, int firstResult) {
        return findLancamentoEntradaEntities(false, maxResults, firstResult);
    }

    private List<LancamentoEntrada> findLancamentoEntradaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LancamentoEntrada.class));
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

    public LancamentoEntrada findLancamentoEntrada(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LancamentoEntrada.class, id);
        } finally {
            em.close();
        }
    }

    public int getLancamentoEntradaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LancamentoEntrada> rt = cq.from(LancamentoEntrada.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
