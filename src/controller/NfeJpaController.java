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
import model.Fornecedor;
import model.Nfe;
import model.PagamentoLote;

/**
 *
 * @author Luis
 */
public class NfeJpaController implements Serializable {

    public NfeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nfe nfe) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor codfornec = nfe.getCodfornec();
            if (codfornec != null) {
                codfornec = em.getReference(codfornec.getClass(), codfornec.getCodfornec());
                nfe.setCodfornec(codfornec);
            }
            PagamentoLote codpaglote = nfe.getCodpaglote();
            if (codpaglote != null) {
                codpaglote = em.getReference(codpaglote.getClass(), codpaglote.getCodpaglote());
                nfe.setCodpaglote(codpaglote);
            }
            em.persist(nfe);
            if (codfornec != null) {
                codfornec.getNfeList().add(nfe);
                codfornec = em.merge(codfornec);
            }
            if (codpaglote != null) {
                codpaglote.getNfeList().add(nfe);
                codpaglote = em.merge(codpaglote);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNfe(nfe.getCodnfe()) != null) {
                throw new PreexistingEntityException("Nfe " + nfe + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nfe nfe) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nfe persistentNfe = em.find(Nfe.class, nfe.getCodnfe());
            Fornecedor codfornecOld = persistentNfe.getCodfornec();
            Fornecedor codfornecNew = nfe.getCodfornec();
            PagamentoLote codpagloteOld = persistentNfe.getCodpaglote();
            PagamentoLote codpagloteNew = nfe.getCodpaglote();
            if (codfornecNew != null) {
                codfornecNew = em.getReference(codfornecNew.getClass(), codfornecNew.getCodfornec());
                nfe.setCodfornec(codfornecNew);
            }
            if (codpagloteNew != null) {
                codpagloteNew = em.getReference(codpagloteNew.getClass(), codpagloteNew.getCodpaglote());
                nfe.setCodpaglote(codpagloteNew);
            }
            nfe = em.merge(nfe);
            if (codfornecOld != null && !codfornecOld.equals(codfornecNew)) {
                codfornecOld.getNfeList().remove(nfe);
                codfornecOld = em.merge(codfornecOld);
            }
            if (codfornecNew != null && !codfornecNew.equals(codfornecOld)) {
                codfornecNew.getNfeList().add(nfe);
                codfornecNew = em.merge(codfornecNew);
            }
            if (codpagloteOld != null && !codpagloteOld.equals(codpagloteNew)) {
                codpagloteOld.getNfeList().remove(nfe);
                codpagloteOld = em.merge(codpagloteOld);
            }
            if (codpagloteNew != null && !codpagloteNew.equals(codpagloteOld)) {
                codpagloteNew.getNfeList().add(nfe);
                codpagloteNew = em.merge(codpagloteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nfe.getCodnfe();
                if (findNfe(id) == null) {
                    throw new NonexistentEntityException("The nfe with id " + id + " no longer exists.");
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
            Nfe nfe;
            try {
                nfe = em.getReference(Nfe.class, id);
                nfe.getCodnfe();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nfe with id " + id + " no longer exists.", enfe);
            }
            Fornecedor codfornec = nfe.getCodfornec();
            if (codfornec != null) {
                codfornec.getNfeList().remove(nfe);
                codfornec = em.merge(codfornec);
            }
            PagamentoLote codpaglote = nfe.getCodpaglote();
            if (codpaglote != null) {
                codpaglote.getNfeList().remove(nfe);
                codpaglote = em.merge(codpaglote);
            }
            em.remove(nfe);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nfe> findNfeEntities() {
        return findNfeEntities(true, -1, -1);
    }

    public List<Nfe> findNfeEntities(int maxResults, int firstResult) {
        return findNfeEntities(false, maxResults, firstResult);
    }

    private List<Nfe> findNfeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nfe.class));
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

    public Nfe findNfe(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nfe.class, id);
        } finally {
            em.close();
        }
    }

    public int getNfeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nfe> rt = cq.from(Nfe.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
