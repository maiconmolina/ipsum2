/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Lancamento;
import model.SituacaoLancamento;
import model.SituacaoLancamentoPK;

/**
 *
 * @author Luis
 */
public class SituacaoLancamentoJpaController implements Serializable {

    public SituacaoLancamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SituacaoLancamento situacaoLancamento) throws PreexistingEntityException, Exception {
        if (situacaoLancamento.getSituacaoLancamentoPK() == null) {
            situacaoLancamento.setSituacaoLancamentoPK(new SituacaoLancamentoPK());
        }
        situacaoLancamento.getSituacaoLancamentoPK().setCodlanc(situacaoLancamento.getLancamento().getCodlanc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lancamento lancamento = situacaoLancamento.getLancamento();
            if (lancamento != null) {
                lancamento = em.getReference(lancamento.getClass(), lancamento.getCodlanc());
                situacaoLancamento.setLancamento(lancamento);
            }
            em.persist(situacaoLancamento);
            if (lancamento != null) {
                lancamento.getSituacaoLancamentoList().add(situacaoLancamento);
                lancamento = em.merge(lancamento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSituacaoLancamento(situacaoLancamento.getSituacaoLancamentoPK()) != null) {
                throw new PreexistingEntityException("SituacaoLancamento " + situacaoLancamento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SituacaoLancamento situacaoLancamento) throws NonexistentEntityException, Exception {
        situacaoLancamento.getSituacaoLancamentoPK().setCodlanc(situacaoLancamento.getLancamento().getCodlanc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SituacaoLancamento persistentSituacaoLancamento = em.find(SituacaoLancamento.class, situacaoLancamento.getSituacaoLancamentoPK());
            Lancamento lancamentoOld = persistentSituacaoLancamento.getLancamento();
            Lancamento lancamentoNew = situacaoLancamento.getLancamento();
            if (lancamentoNew != null) {
                lancamentoNew = em.getReference(lancamentoNew.getClass(), lancamentoNew.getCodlanc());
                situacaoLancamento.setLancamento(lancamentoNew);
            }
            situacaoLancamento = em.merge(situacaoLancamento);
            if (lancamentoOld != null && !lancamentoOld.equals(lancamentoNew)) {
                lancamentoOld.getSituacaoLancamentoList().remove(situacaoLancamento);
                lancamentoOld = em.merge(lancamentoOld);
            }
            if (lancamentoNew != null && !lancamentoNew.equals(lancamentoOld)) {
                lancamentoNew.getSituacaoLancamentoList().add(situacaoLancamento);
                lancamentoNew = em.merge(lancamentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SituacaoLancamentoPK id = situacaoLancamento.getSituacaoLancamentoPK();
                if (findSituacaoLancamento(id) == null) {
                    throw new NonexistentEntityException("The situacaoLancamento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SituacaoLancamentoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SituacaoLancamento situacaoLancamento;
            try {
                situacaoLancamento = em.getReference(SituacaoLancamento.class, id);
                situacaoLancamento.getSituacaoLancamentoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The situacaoLancamento with id " + id + " no longer exists.", enfe);
            }
            Lancamento lancamento = situacaoLancamento.getLancamento();
            if (lancamento != null) {
                lancamento.getSituacaoLancamentoList().remove(situacaoLancamento);
                lancamento = em.merge(lancamento);
            }
            em.remove(situacaoLancamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SituacaoLancamento> findSituacaoLancamentoEntities() {
        return findSituacaoLancamentoEntities(true, -1, -1);
    }

    public List<SituacaoLancamento> findSituacaoLancamentoEntities(int maxResults, int firstResult) {
        return findSituacaoLancamentoEntities(false, maxResults, firstResult);
    }

    private List<SituacaoLancamento> findSituacaoLancamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SituacaoLancamento.class));
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

    public SituacaoLancamento findSituacaoLancamento(SituacaoLancamentoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SituacaoLancamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getSituacaoLancamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SituacaoLancamento> rt = cq.from(SituacaoLancamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
