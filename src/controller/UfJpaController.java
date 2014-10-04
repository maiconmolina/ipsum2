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
import model.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Uf;

/**
 *
 * @author Maicon
 */
public class UfJpaController implements Serializable {

    public UfJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Uf uf) throws PreexistingEntityException, Exception {
        if (uf.getFornecedorList() == null) {
            uf.setFornecedorList(new ArrayList<Fornecedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Fornecedor> attachedFornecedorList = new ArrayList<Fornecedor>();
            for (Fornecedor fornecedorListFornecedorToAttach : uf.getFornecedorList()) {
                fornecedorListFornecedorToAttach = em.getReference(fornecedorListFornecedorToAttach.getClass(), fornecedorListFornecedorToAttach.getCodfornec());
                attachedFornecedorList.add(fornecedorListFornecedorToAttach);
            }
            uf.setFornecedorList(attachedFornecedorList);
            em.persist(uf);
            for (Fornecedor fornecedorListFornecedor : uf.getFornecedorList()) {
                Uf oldCodufOfFornecedorListFornecedor = fornecedorListFornecedor.getCoduf();
                fornecedorListFornecedor.setCoduf(uf);
                fornecedorListFornecedor = em.merge(fornecedorListFornecedor);
                if (oldCodufOfFornecedorListFornecedor != null) {
                    oldCodufOfFornecedorListFornecedor.getFornecedorList().remove(fornecedorListFornecedor);
                    oldCodufOfFornecedorListFornecedor = em.merge(oldCodufOfFornecedorListFornecedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUf(uf.getCoduf()) != null) {
                throw new PreexistingEntityException("Uf " + uf + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Uf uf) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Uf persistentUf = em.find(Uf.class, uf.getCoduf());
            List<Fornecedor> fornecedorListOld = persistentUf.getFornecedorList();
            List<Fornecedor> fornecedorListNew = uf.getFornecedorList();
            List<Fornecedor> attachedFornecedorListNew = new ArrayList<Fornecedor>();
            for (Fornecedor fornecedorListNewFornecedorToAttach : fornecedorListNew) {
                fornecedorListNewFornecedorToAttach = em.getReference(fornecedorListNewFornecedorToAttach.getClass(), fornecedorListNewFornecedorToAttach.getCodfornec());
                attachedFornecedorListNew.add(fornecedorListNewFornecedorToAttach);
            }
            fornecedorListNew = attachedFornecedorListNew;
            uf.setFornecedorList(fornecedorListNew);
            uf = em.merge(uf);
            for (Fornecedor fornecedorListOldFornecedor : fornecedorListOld) {
                if (!fornecedorListNew.contains(fornecedorListOldFornecedor)) {
                    fornecedorListOldFornecedor.setCoduf(null);
                    fornecedorListOldFornecedor = em.merge(fornecedorListOldFornecedor);
                }
            }
            for (Fornecedor fornecedorListNewFornecedor : fornecedorListNew) {
                if (!fornecedorListOld.contains(fornecedorListNewFornecedor)) {
                    Uf oldCodufOfFornecedorListNewFornecedor = fornecedorListNewFornecedor.getCoduf();
                    fornecedorListNewFornecedor.setCoduf(uf);
                    fornecedorListNewFornecedor = em.merge(fornecedorListNewFornecedor);
                    if (oldCodufOfFornecedorListNewFornecedor != null && !oldCodufOfFornecedorListNewFornecedor.equals(uf)) {
                        oldCodufOfFornecedorListNewFornecedor.getFornecedorList().remove(fornecedorListNewFornecedor);
                        oldCodufOfFornecedorListNewFornecedor = em.merge(oldCodufOfFornecedorListNewFornecedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uf.getCoduf();
                if (findUf(id) == null) {
                    throw new NonexistentEntityException("The uf with id " + id + " no longer exists.");
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
            Uf uf;
            try {
                uf = em.getReference(Uf.class, id);
                uf.getCoduf();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uf with id " + id + " no longer exists.", enfe);
            }
            List<Fornecedor> fornecedorList = uf.getFornecedorList();
            for (Fornecedor fornecedorListFornecedor : fornecedorList) {
                fornecedorListFornecedor.setCoduf(null);
                fornecedorListFornecedor = em.merge(fornecedorListFornecedor);
            }
            em.remove(uf);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Uf> findUfEntities() {
        return findUfEntities(true, -1, -1);
    }

    public List<Uf> findUfEntities(int maxResults, int firstResult) {
        return findUfEntities(false, maxResults, firstResult);
    }

    private List<Uf> findUfEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Uf.class));
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

    public Uf findUf(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Uf.class, id);
        } finally {
            em.close();
        }
    }

    public int getUfCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Uf> rt = cq.from(Uf.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
