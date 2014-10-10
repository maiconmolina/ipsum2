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
import model.Funcionario;
import model.Lancamento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.LancamentoPagfunc;

/**
 *
 * @author Luis
 */
public class LancamentoPagfuncJpaController implements Serializable {

    public LancamentoPagfuncJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LancamentoPagfunc lancamentoPagfunc) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Lancamento lancamentoOrphanCheck = lancamentoPagfunc.getLancamento();
        if (lancamentoOrphanCheck != null) {
            LancamentoPagfunc oldLancamentoPagfuncOfLancamento = lancamentoOrphanCheck.getLancamentoPagfunc();
            if (oldLancamentoPagfuncOfLancamento != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Lancamento " + lancamentoOrphanCheck + " already has an item of type LancamentoPagfunc whose lancamento column cannot be null. Please make another selection for the lancamento field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario codfunc = lancamentoPagfunc.getCodfunc();
            if (codfunc != null) {
                codfunc = em.getReference(codfunc.getClass(), codfunc.getCodfunc());
                lancamentoPagfunc.setCodfunc(codfunc);
            }
            Lancamento lancamento = lancamentoPagfunc.getLancamento();
            if (lancamento != null) {
                lancamento = em.getReference(lancamento.getClass(), lancamento.getCodlanc());
                lancamentoPagfunc.setLancamento(lancamento);
            }
            em.persist(lancamentoPagfunc);
            if (codfunc != null) {
                codfunc.getLancamentoPagfuncList().add(lancamentoPagfunc);
                codfunc = em.merge(codfunc);
            }
            if (lancamento != null) {
                lancamento.setLancamentoPagfunc(lancamentoPagfunc);
                lancamento = em.merge(lancamento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLancamentoPagfunc(lancamentoPagfunc.getCodlanc()) != null) {
                throw new PreexistingEntityException("LancamentoPagfunc " + lancamentoPagfunc + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LancamentoPagfunc lancamentoPagfunc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LancamentoPagfunc persistentLancamentoPagfunc = em.find(LancamentoPagfunc.class, lancamentoPagfunc.getCodlanc());
            Funcionario codfuncOld = persistentLancamentoPagfunc.getCodfunc();
            Funcionario codfuncNew = lancamentoPagfunc.getCodfunc();
            Lancamento lancamentoOld = persistentLancamentoPagfunc.getLancamento();
            Lancamento lancamentoNew = lancamentoPagfunc.getLancamento();
            List<String> illegalOrphanMessages = null;
            if (lancamentoNew != null && !lancamentoNew.equals(lancamentoOld)) {
                LancamentoPagfunc oldLancamentoPagfuncOfLancamento = lancamentoNew.getLancamentoPagfunc();
                if (oldLancamentoPagfuncOfLancamento != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Lancamento " + lancamentoNew + " already has an item of type LancamentoPagfunc whose lancamento column cannot be null. Please make another selection for the lancamento field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codfuncNew != null) {
                codfuncNew = em.getReference(codfuncNew.getClass(), codfuncNew.getCodfunc());
                lancamentoPagfunc.setCodfunc(codfuncNew);
            }
            if (lancamentoNew != null) {
                lancamentoNew = em.getReference(lancamentoNew.getClass(), lancamentoNew.getCodlanc());
                lancamentoPagfunc.setLancamento(lancamentoNew);
            }
            lancamentoPagfunc = em.merge(lancamentoPagfunc);
            if (codfuncOld != null && !codfuncOld.equals(codfuncNew)) {
                codfuncOld.getLancamentoPagfuncList().remove(lancamentoPagfunc);
                codfuncOld = em.merge(codfuncOld);
            }
            if (codfuncNew != null && !codfuncNew.equals(codfuncOld)) {
                codfuncNew.getLancamentoPagfuncList().add(lancamentoPagfunc);
                codfuncNew = em.merge(codfuncNew);
            }
            if (lancamentoOld != null && !lancamentoOld.equals(lancamentoNew)) {
                lancamentoOld.setLancamentoPagfunc(null);
                lancamentoOld = em.merge(lancamentoOld);
            }
            if (lancamentoNew != null && !lancamentoNew.equals(lancamentoOld)) {
                lancamentoNew.setLancamentoPagfunc(lancamentoPagfunc);
                lancamentoNew = em.merge(lancamentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lancamentoPagfunc.getCodlanc();
                if (findLancamentoPagfunc(id) == null) {
                    throw new NonexistentEntityException("The lancamentoPagfunc with id " + id + " no longer exists.");
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
            LancamentoPagfunc lancamentoPagfunc;
            try {
                lancamentoPagfunc = em.getReference(LancamentoPagfunc.class, id);
                lancamentoPagfunc.getCodlanc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lancamentoPagfunc with id " + id + " no longer exists.", enfe);
            }
            Funcionario codfunc = lancamentoPagfunc.getCodfunc();
            if (codfunc != null) {
                codfunc.getLancamentoPagfuncList().remove(lancamentoPagfunc);
                codfunc = em.merge(codfunc);
            }
            Lancamento lancamento = lancamentoPagfunc.getLancamento();
            if (lancamento != null) {
                lancamento.setLancamentoPagfunc(null);
                lancamento = em.merge(lancamento);
            }
            em.remove(lancamentoPagfunc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LancamentoPagfunc> findLancamentoPagfuncEntities() {
        return findLancamentoPagfuncEntities(true, -1, -1);
    }

    public List<LancamentoPagfunc> findLancamentoPagfuncEntities(int maxResults, int firstResult) {
        return findLancamentoPagfuncEntities(false, maxResults, firstResult);
    }

    private List<LancamentoPagfunc> findLancamentoPagfuncEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LancamentoPagfunc.class));
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

    public LancamentoPagfunc findLancamentoPagfunc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LancamentoPagfunc.class, id);
        } finally {
            em.close();
        }
    }

    public int getLancamentoPagfuncCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LancamentoPagfunc> rt = cq.from(LancamentoPagfunc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
