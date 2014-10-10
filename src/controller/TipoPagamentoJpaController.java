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
import model.PagamentoLote;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.TipoPagamento;

/**
 *
 * @author Luis
 */
public class TipoPagamentoJpaController implements Serializable {

    public TipoPagamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoPagamento tipoPagamento) throws PreexistingEntityException, Exception {
        if (tipoPagamento.getPagamentoLoteList() == null) {
            tipoPagamento.setPagamentoLoteList(new ArrayList<PagamentoLote>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<PagamentoLote> attachedPagamentoLoteList = new ArrayList<PagamentoLote>();
            for (PagamentoLote pagamentoLoteListPagamentoLoteToAttach : tipoPagamento.getPagamentoLoteList()) {
                pagamentoLoteListPagamentoLoteToAttach = em.getReference(pagamentoLoteListPagamentoLoteToAttach.getClass(), pagamentoLoteListPagamentoLoteToAttach.getCodpaglote());
                attachedPagamentoLoteList.add(pagamentoLoteListPagamentoLoteToAttach);
            }
            tipoPagamento.setPagamentoLoteList(attachedPagamentoLoteList);
            em.persist(tipoPagamento);
            for (PagamentoLote pagamentoLoteListPagamentoLote : tipoPagamento.getPagamentoLoteList()) {
                TipoPagamento oldTipopagOfPagamentoLoteListPagamentoLote = pagamentoLoteListPagamentoLote.getTipopag();
                pagamentoLoteListPagamentoLote.setTipopag(tipoPagamento);
                pagamentoLoteListPagamentoLote = em.merge(pagamentoLoteListPagamentoLote);
                if (oldTipopagOfPagamentoLoteListPagamentoLote != null) {
                    oldTipopagOfPagamentoLoteListPagamentoLote.getPagamentoLoteList().remove(pagamentoLoteListPagamentoLote);
                    oldTipopagOfPagamentoLoteListPagamentoLote = em.merge(oldTipopagOfPagamentoLoteListPagamentoLote);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoPagamento(tipoPagamento.getTipopag()) != null) {
                throw new PreexistingEntityException("TipoPagamento " + tipoPagamento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoPagamento tipoPagamento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoPagamento persistentTipoPagamento = em.find(TipoPagamento.class, tipoPagamento.getTipopag());
            List<PagamentoLote> pagamentoLoteListOld = persistentTipoPagamento.getPagamentoLoteList();
            List<PagamentoLote> pagamentoLoteListNew = tipoPagamento.getPagamentoLoteList();
            List<PagamentoLote> attachedPagamentoLoteListNew = new ArrayList<PagamentoLote>();
            for (PagamentoLote pagamentoLoteListNewPagamentoLoteToAttach : pagamentoLoteListNew) {
                pagamentoLoteListNewPagamentoLoteToAttach = em.getReference(pagamentoLoteListNewPagamentoLoteToAttach.getClass(), pagamentoLoteListNewPagamentoLoteToAttach.getCodpaglote());
                attachedPagamentoLoteListNew.add(pagamentoLoteListNewPagamentoLoteToAttach);
            }
            pagamentoLoteListNew = attachedPagamentoLoteListNew;
            tipoPagamento.setPagamentoLoteList(pagamentoLoteListNew);
            tipoPagamento = em.merge(tipoPagamento);
            for (PagamentoLote pagamentoLoteListOldPagamentoLote : pagamentoLoteListOld) {
                if (!pagamentoLoteListNew.contains(pagamentoLoteListOldPagamentoLote)) {
                    pagamentoLoteListOldPagamentoLote.setTipopag(null);
                    pagamentoLoteListOldPagamentoLote = em.merge(pagamentoLoteListOldPagamentoLote);
                }
            }
            for (PagamentoLote pagamentoLoteListNewPagamentoLote : pagamentoLoteListNew) {
                if (!pagamentoLoteListOld.contains(pagamentoLoteListNewPagamentoLote)) {
                    TipoPagamento oldTipopagOfPagamentoLoteListNewPagamentoLote = pagamentoLoteListNewPagamentoLote.getTipopag();
                    pagamentoLoteListNewPagamentoLote.setTipopag(tipoPagamento);
                    pagamentoLoteListNewPagamentoLote = em.merge(pagamentoLoteListNewPagamentoLote);
                    if (oldTipopagOfPagamentoLoteListNewPagamentoLote != null && !oldTipopagOfPagamentoLoteListNewPagamentoLote.equals(tipoPagamento)) {
                        oldTipopagOfPagamentoLoteListNewPagamentoLote.getPagamentoLoteList().remove(pagamentoLoteListNewPagamentoLote);
                        oldTipopagOfPagamentoLoteListNewPagamentoLote = em.merge(oldTipopagOfPagamentoLoteListNewPagamentoLote);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoPagamento.getTipopag();
                if (findTipoPagamento(id) == null) {
                    throw new NonexistentEntityException("The tipoPagamento with id " + id + " no longer exists.");
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
            TipoPagamento tipoPagamento;
            try {
                tipoPagamento = em.getReference(TipoPagamento.class, id);
                tipoPagamento.getTipopag();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoPagamento with id " + id + " no longer exists.", enfe);
            }
            List<PagamentoLote> pagamentoLoteList = tipoPagamento.getPagamentoLoteList();
            for (PagamentoLote pagamentoLoteListPagamentoLote : pagamentoLoteList) {
                pagamentoLoteListPagamentoLote.setTipopag(null);
                pagamentoLoteListPagamentoLote = em.merge(pagamentoLoteListPagamentoLote);
            }
            em.remove(tipoPagamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoPagamento> findTipoPagamentoEntities() {
        return findTipoPagamentoEntities(true, -1, -1);
    }

    public List<TipoPagamento> findTipoPagamentoEntities(int maxResults, int firstResult) {
        return findTipoPagamentoEntities(false, maxResults, firstResult);
    }

    private List<TipoPagamento> findTipoPagamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoPagamento.class));
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

    public TipoPagamento findTipoPagamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoPagamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoPagamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoPagamento> rt = cq.from(TipoPagamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
