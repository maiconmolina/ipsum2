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
import model.Lote;
import model.TipoPagamento;
import model.Nfe;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.PagamentoLote;

/**
 *
 * @author Maicon
 */
public class PagamentoLoteJpaController implements Serializable {

    public PagamentoLoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PagamentoLote pagamentoLote) throws PreexistingEntityException, Exception {
        if (pagamentoLote.getNfeList() == null) {
            pagamentoLote.setNfeList(new ArrayList<Nfe>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lote codlote = pagamentoLote.getCodlote();
            if (codlote != null) {
                codlote = em.getReference(codlote.getClass(), codlote.getCodlote());
                pagamentoLote.setCodlote(codlote);
            }
            TipoPagamento tipopag = pagamentoLote.getTipopag();
            if (tipopag != null) {
                tipopag = em.getReference(tipopag.getClass(), tipopag.getTipopag());
                pagamentoLote.setTipopag(tipopag);
            }
            List<Nfe> attachedNfeList = new ArrayList<Nfe>();
            for (Nfe nfeListNfeToAttach : pagamentoLote.getNfeList()) {
                nfeListNfeToAttach = em.getReference(nfeListNfeToAttach.getClass(), nfeListNfeToAttach.getCodnfe());
                attachedNfeList.add(nfeListNfeToAttach);
            }
            pagamentoLote.setNfeList(attachedNfeList);
            em.persist(pagamentoLote);
            if (codlote != null) {
                codlote.getPagamentoLoteList().add(pagamentoLote);
                codlote = em.merge(codlote);
            }
            if (tipopag != null) {
                tipopag.getPagamentoLoteList().add(pagamentoLote);
                tipopag = em.merge(tipopag);
            }
            for (Nfe nfeListNfe : pagamentoLote.getNfeList()) {
                PagamentoLote oldCodpagloteOfNfeListNfe = nfeListNfe.getCodpaglote();
                nfeListNfe.setCodpaglote(pagamentoLote);
                nfeListNfe = em.merge(nfeListNfe);
                if (oldCodpagloteOfNfeListNfe != null) {
                    oldCodpagloteOfNfeListNfe.getNfeList().remove(nfeListNfe);
                    oldCodpagloteOfNfeListNfe = em.merge(oldCodpagloteOfNfeListNfe);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPagamentoLote(pagamentoLote.getCodpaglote()) != null) {
                throw new PreexistingEntityException("PagamentoLote " + pagamentoLote + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PagamentoLote pagamentoLote) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PagamentoLote persistentPagamentoLote = em.find(PagamentoLote.class, pagamentoLote.getCodpaglote());
            Lote codloteOld = persistentPagamentoLote.getCodlote();
            Lote codloteNew = pagamentoLote.getCodlote();
            TipoPagamento tipopagOld = persistentPagamentoLote.getTipopag();
            TipoPagamento tipopagNew = pagamentoLote.getTipopag();
            List<Nfe> nfeListOld = persistentPagamentoLote.getNfeList();
            List<Nfe> nfeListNew = pagamentoLote.getNfeList();
            if (codloteNew != null) {
                codloteNew = em.getReference(codloteNew.getClass(), codloteNew.getCodlote());
                pagamentoLote.setCodlote(codloteNew);
            }
            if (tipopagNew != null) {
                tipopagNew = em.getReference(tipopagNew.getClass(), tipopagNew.getTipopag());
                pagamentoLote.setTipopag(tipopagNew);
            }
            List<Nfe> attachedNfeListNew = new ArrayList<Nfe>();
            for (Nfe nfeListNewNfeToAttach : nfeListNew) {
                nfeListNewNfeToAttach = em.getReference(nfeListNewNfeToAttach.getClass(), nfeListNewNfeToAttach.getCodnfe());
                attachedNfeListNew.add(nfeListNewNfeToAttach);
            }
            nfeListNew = attachedNfeListNew;
            pagamentoLote.setNfeList(nfeListNew);
            pagamentoLote = em.merge(pagamentoLote);
            if (codloteOld != null && !codloteOld.equals(codloteNew)) {
                codloteOld.getPagamentoLoteList().remove(pagamentoLote);
                codloteOld = em.merge(codloteOld);
            }
            if (codloteNew != null && !codloteNew.equals(codloteOld)) {
                codloteNew.getPagamentoLoteList().add(pagamentoLote);
                codloteNew = em.merge(codloteNew);
            }
            if (tipopagOld != null && !tipopagOld.equals(tipopagNew)) {
                tipopagOld.getPagamentoLoteList().remove(pagamentoLote);
                tipopagOld = em.merge(tipopagOld);
            }
            if (tipopagNew != null && !tipopagNew.equals(tipopagOld)) {
                tipopagNew.getPagamentoLoteList().add(pagamentoLote);
                tipopagNew = em.merge(tipopagNew);
            }
            for (Nfe nfeListOldNfe : nfeListOld) {
                if (!nfeListNew.contains(nfeListOldNfe)) {
                    nfeListOldNfe.setCodpaglote(null);
                    nfeListOldNfe = em.merge(nfeListOldNfe);
                }
            }
            for (Nfe nfeListNewNfe : nfeListNew) {
                if (!nfeListOld.contains(nfeListNewNfe)) {
                    PagamentoLote oldCodpagloteOfNfeListNewNfe = nfeListNewNfe.getCodpaglote();
                    nfeListNewNfe.setCodpaglote(pagamentoLote);
                    nfeListNewNfe = em.merge(nfeListNewNfe);
                    if (oldCodpagloteOfNfeListNewNfe != null && !oldCodpagloteOfNfeListNewNfe.equals(pagamentoLote)) {
                        oldCodpagloteOfNfeListNewNfe.getNfeList().remove(nfeListNewNfe);
                        oldCodpagloteOfNfeListNewNfe = em.merge(oldCodpagloteOfNfeListNewNfe);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pagamentoLote.getCodpaglote();
                if (findPagamentoLote(id) == null) {
                    throw new NonexistentEntityException("The pagamentoLote with id " + id + " no longer exists.");
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
            PagamentoLote pagamentoLote;
            try {
                pagamentoLote = em.getReference(PagamentoLote.class, id);
                pagamentoLote.getCodpaglote();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pagamentoLote with id " + id + " no longer exists.", enfe);
            }
            Lote codlote = pagamentoLote.getCodlote();
            if (codlote != null) {
                codlote.getPagamentoLoteList().remove(pagamentoLote);
                codlote = em.merge(codlote);
            }
            TipoPagamento tipopag = pagamentoLote.getTipopag();
            if (tipopag != null) {
                tipopag.getPagamentoLoteList().remove(pagamentoLote);
                tipopag = em.merge(tipopag);
            }
            List<Nfe> nfeList = pagamentoLote.getNfeList();
            for (Nfe nfeListNfe : nfeList) {
                nfeListNfe.setCodpaglote(null);
                nfeListNfe = em.merge(nfeListNfe);
            }
            em.remove(pagamentoLote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PagamentoLote> findPagamentoLoteEntities() {
        return findPagamentoLoteEntities(true, -1, -1);
    }

    public List<PagamentoLote> findPagamentoLoteEntities(int maxResults, int firstResult) {
        return findPagamentoLoteEntities(false, maxResults, firstResult);
    }

    private List<PagamentoLote> findPagamentoLoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PagamentoLote.class));
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

    public PagamentoLote findPagamentoLote(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PagamentoLote.class, id);
        } finally {
            em.close();
        }
    }

    public int getPagamentoLoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PagamentoLote> rt = cq.from(PagamentoLote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
