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
import model.PermissoesFuncao;

/**
 *
 * @author Maicon
 */
public class PermissoesFuncaoJpaController implements Serializable {

    public PermissoesFuncaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PermissoesFuncao permissoesFuncao) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(permissoesFuncao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPermissoesFuncao(permissoesFuncao.getCodperm()) != null) {
                throw new PreexistingEntityException("PermissoesFuncao " + permissoesFuncao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PermissoesFuncao permissoesFuncao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            permissoesFuncao = em.merge(permissoesFuncao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = permissoesFuncao.getCodperm();
                if (findPermissoesFuncao(id) == null) {
                    throw new NonexistentEntityException("The permissoesFuncao with id " + id + " no longer exists.");
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
            PermissoesFuncao permissoesFuncao;
            try {
                permissoesFuncao = em.getReference(PermissoesFuncao.class, id);
                permissoesFuncao.getCodperm();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The permissoesFuncao with id " + id + " no longer exists.", enfe);
            }
            em.remove(permissoesFuncao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PermissoesFuncao> findPermissoesFuncaoEntities() {
        return findPermissoesFuncaoEntities(true, -1, -1);
    }

    public List<PermissoesFuncao> findPermissoesFuncaoEntities(int maxResults, int firstResult) {
        return findPermissoesFuncaoEntities(false, maxResults, firstResult);
    }

    private List<PermissoesFuncao> findPermissoesFuncaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PermissoesFuncao.class));
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

    public PermissoesFuncao findPermissoesFuncao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PermissoesFuncao.class, id);
        } finally {
            em.close();
        }
    }

    public int getPermissoesFuncaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PermissoesFuncao> rt = cq.from(PermissoesFuncao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
