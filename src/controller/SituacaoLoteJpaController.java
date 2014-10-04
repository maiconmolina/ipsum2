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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.SituacaoLote;

/**
 *
 * @author Maicon
 */
public class SituacaoLoteJpaController implements Serializable {

    public SituacaoLoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SituacaoLote situacaoLote) throws PreexistingEntityException, Exception {
        if (situacaoLote.getLoteList() == null) {
            situacaoLote.setLoteList(new ArrayList<Lote>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Lote> attachedLoteList = new ArrayList<Lote>();
            for (Lote loteListLoteToAttach : situacaoLote.getLoteList()) {
                loteListLoteToAttach = em.getReference(loteListLoteToAttach.getClass(), loteListLoteToAttach.getCodlote());
                attachedLoteList.add(loteListLoteToAttach);
            }
            situacaoLote.setLoteList(attachedLoteList);
            em.persist(situacaoLote);
            for (Lote loteListLote : situacaoLote.getLoteList()) {
                SituacaoLote oldSitloteOfLoteListLote = loteListLote.getSitlote();
                loteListLote.setSitlote(situacaoLote);
                loteListLote = em.merge(loteListLote);
                if (oldSitloteOfLoteListLote != null) {
                    oldSitloteOfLoteListLote.getLoteList().remove(loteListLote);
                    oldSitloteOfLoteListLote = em.merge(oldSitloteOfLoteListLote);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSituacaoLote(situacaoLote.getSitlote()) != null) {
                throw new PreexistingEntityException("SituacaoLote " + situacaoLote + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SituacaoLote situacaoLote) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SituacaoLote persistentSituacaoLote = em.find(SituacaoLote.class, situacaoLote.getSitlote());
            List<Lote> loteListOld = persistentSituacaoLote.getLoteList();
            List<Lote> loteListNew = situacaoLote.getLoteList();
            List<Lote> attachedLoteListNew = new ArrayList<Lote>();
            for (Lote loteListNewLoteToAttach : loteListNew) {
                loteListNewLoteToAttach = em.getReference(loteListNewLoteToAttach.getClass(), loteListNewLoteToAttach.getCodlote());
                attachedLoteListNew.add(loteListNewLoteToAttach);
            }
            loteListNew = attachedLoteListNew;
            situacaoLote.setLoteList(loteListNew);
            situacaoLote = em.merge(situacaoLote);
            for (Lote loteListOldLote : loteListOld) {
                if (!loteListNew.contains(loteListOldLote)) {
                    loteListOldLote.setSitlote(null);
                    loteListOldLote = em.merge(loteListOldLote);
                }
            }
            for (Lote loteListNewLote : loteListNew) {
                if (!loteListOld.contains(loteListNewLote)) {
                    SituacaoLote oldSitloteOfLoteListNewLote = loteListNewLote.getSitlote();
                    loteListNewLote.setSitlote(situacaoLote);
                    loteListNewLote = em.merge(loteListNewLote);
                    if (oldSitloteOfLoteListNewLote != null && !oldSitloteOfLoteListNewLote.equals(situacaoLote)) {
                        oldSitloteOfLoteListNewLote.getLoteList().remove(loteListNewLote);
                        oldSitloteOfLoteListNewLote = em.merge(oldSitloteOfLoteListNewLote);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = situacaoLote.getSitlote();
                if (findSituacaoLote(id) == null) {
                    throw new NonexistentEntityException("The situacaoLote with id " + id + " no longer exists.");
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
            SituacaoLote situacaoLote;
            try {
                situacaoLote = em.getReference(SituacaoLote.class, id);
                situacaoLote.getSitlote();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The situacaoLote with id " + id + " no longer exists.", enfe);
            }
            List<Lote> loteList = situacaoLote.getLoteList();
            for (Lote loteListLote : loteList) {
                loteListLote.setSitlote(null);
                loteListLote = em.merge(loteListLote);
            }
            em.remove(situacaoLote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SituacaoLote> findSituacaoLoteEntities() {
        return findSituacaoLoteEntities(true, -1, -1);
    }

    public List<SituacaoLote> findSituacaoLoteEntities(int maxResults, int firstResult) {
        return findSituacaoLoteEntities(false, maxResults, firstResult);
    }

    private List<SituacaoLote> findSituacaoLoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SituacaoLote.class));
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

    public SituacaoLote findSituacaoLote(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SituacaoLote.class, id);
        } finally {
            em.close();
        }
    }

    public int getSituacaoLoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SituacaoLote> rt = cq.from(SituacaoLote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
