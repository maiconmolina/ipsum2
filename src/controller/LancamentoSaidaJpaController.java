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
import model.LancamentoSaida;

/**
 *
 * @author Luis
 */
public class LancamentoSaidaJpaController implements Serializable {

    public LancamentoSaidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LancamentoSaida lancamentoSaida) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Lancamento lancamentoOrphanCheck = lancamentoSaida.getLancamento();
        if (lancamentoOrphanCheck != null) {
            LancamentoSaida oldLancamentoSaidaOfLancamento = lancamentoOrphanCheck.getLancamentoSaida();
            if (oldLancamentoSaidaOfLancamento != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Lancamento " + lancamentoOrphanCheck + " already has an item of type LancamentoSaida whose lancamento column cannot be null. Please make another selection for the lancamento field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lancamento lancamento = lancamentoSaida.getLancamento();
            if (lancamento != null) {
                lancamento = em.getReference(lancamento.getClass(), lancamento.getCodlanc());
                lancamentoSaida.setLancamento(lancamento);
            }
            em.persist(lancamentoSaida);
            if (lancamento != null) {
                lancamento.setLancamentoSaida(lancamentoSaida);
                lancamento = em.merge(lancamento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLancamentoSaida(lancamentoSaida.getCodlanc()) != null) {
                throw new PreexistingEntityException("LancamentoSaida " + lancamentoSaida + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LancamentoSaida lancamentoSaida) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LancamentoSaida persistentLancamentoSaida = em.find(LancamentoSaida.class, lancamentoSaida.getCodlanc());
            Lancamento lancamentoOld = persistentLancamentoSaida.getLancamento();
            Lancamento lancamentoNew = lancamentoSaida.getLancamento();
            List<String> illegalOrphanMessages = null;
            if (lancamentoNew != null && !lancamentoNew.equals(lancamentoOld)) {
                LancamentoSaida oldLancamentoSaidaOfLancamento = lancamentoNew.getLancamentoSaida();
                if (oldLancamentoSaidaOfLancamento != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Lancamento " + lancamentoNew + " already has an item of type LancamentoSaida whose lancamento column cannot be null. Please make another selection for the lancamento field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (lancamentoNew != null) {
                lancamentoNew = em.getReference(lancamentoNew.getClass(), lancamentoNew.getCodlanc());
                lancamentoSaida.setLancamento(lancamentoNew);
            }
            lancamentoSaida = em.merge(lancamentoSaida);
            if (lancamentoOld != null && !lancamentoOld.equals(lancamentoNew)) {
                lancamentoOld.setLancamentoSaida(null);
                lancamentoOld = em.merge(lancamentoOld);
            }
            if (lancamentoNew != null && !lancamentoNew.equals(lancamentoOld)) {
                lancamentoNew.setLancamentoSaida(lancamentoSaida);
                lancamentoNew = em.merge(lancamentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lancamentoSaida.getCodlanc();
                if (findLancamentoSaida(id) == null) {
                    throw new NonexistentEntityException("The lancamentoSaida with id " + id + " no longer exists.");
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
            LancamentoSaida lancamentoSaida;
            try {
                lancamentoSaida = em.getReference(LancamentoSaida.class, id);
                lancamentoSaida.getCodlanc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lancamentoSaida with id " + id + " no longer exists.", enfe);
            }
            Lancamento lancamento = lancamentoSaida.getLancamento();
            if (lancamento != null) {
                lancamento.setLancamentoSaida(null);
                lancamento = em.merge(lancamento);
            }
            em.remove(lancamentoSaida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LancamentoSaida> findLancamentoSaidaEntities() {
        return findLancamentoSaidaEntities(true, -1, -1);
    }

    public List<LancamentoSaida> findLancamentoSaidaEntities(int maxResults, int firstResult) {
        return findLancamentoSaidaEntities(false, maxResults, firstResult);
    }

    private List<LancamentoSaida> findLancamentoSaidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LancamentoSaida.class));
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

    public LancamentoSaida findLancamentoSaida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LancamentoSaida.class, id);
        } finally {
            em.close();
        }
    }

    public int getLancamentoSaidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LancamentoSaida> rt = cq.from(LancamentoSaida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
