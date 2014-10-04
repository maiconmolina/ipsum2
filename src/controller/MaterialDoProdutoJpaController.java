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
import model.Material;
import model.MaterialDoProduto;
import model.MaterialDoProdutoPK;
import model.Produto;

/**
 *
 * @author Maicon
 */
public class MaterialDoProdutoJpaController implements Serializable {

    public MaterialDoProdutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MaterialDoProduto materialDoProduto) throws PreexistingEntityException, Exception {
        if (materialDoProduto.getMaterialDoProdutoPK() == null) {
            materialDoProduto.setMaterialDoProdutoPK(new MaterialDoProdutoPK());
        }
        materialDoProduto.getMaterialDoProdutoPK().setCodmat(materialDoProduto.getMaterial().getCodmat());
        materialDoProduto.getMaterialDoProdutoPK().setCodprod(materialDoProduto.getProduto().getCodprod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Material material = materialDoProduto.getMaterial();
            if (material != null) {
                material = em.getReference(material.getClass(), material.getCodmat());
                materialDoProduto.setMaterial(material);
            }
            Produto produto = materialDoProduto.getProduto();
            if (produto != null) {
                produto = em.getReference(produto.getClass(), produto.getCodprod());
                materialDoProduto.setProduto(produto);
            }
            em.persist(materialDoProduto);
            if (material != null) {
                material.getMaterialDoProdutoList().add(materialDoProduto);
                material = em.merge(material);
            }
            if (produto != null) {
                produto.getMaterialDoProdutoList().add(materialDoProduto);
                produto = em.merge(produto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMaterialDoProduto(materialDoProduto.getMaterialDoProdutoPK()) != null) {
                throw new PreexistingEntityException("MaterialDoProduto " + materialDoProduto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MaterialDoProduto materialDoProduto) throws NonexistentEntityException, Exception {
        materialDoProduto.getMaterialDoProdutoPK().setCodmat(materialDoProduto.getMaterial().getCodmat());
        materialDoProduto.getMaterialDoProdutoPK().setCodprod(materialDoProduto.getProduto().getCodprod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MaterialDoProduto persistentMaterialDoProduto = em.find(MaterialDoProduto.class, materialDoProduto.getMaterialDoProdutoPK());
            Material materialOld = persistentMaterialDoProduto.getMaterial();
            Material materialNew = materialDoProduto.getMaterial();
            Produto produtoOld = persistentMaterialDoProduto.getProduto();
            Produto produtoNew = materialDoProduto.getProduto();
            if (materialNew != null) {
                materialNew = em.getReference(materialNew.getClass(), materialNew.getCodmat());
                materialDoProduto.setMaterial(materialNew);
            }
            if (produtoNew != null) {
                produtoNew = em.getReference(produtoNew.getClass(), produtoNew.getCodprod());
                materialDoProduto.setProduto(produtoNew);
            }
            materialDoProduto = em.merge(materialDoProduto);
            if (materialOld != null && !materialOld.equals(materialNew)) {
                materialOld.getMaterialDoProdutoList().remove(materialDoProduto);
                materialOld = em.merge(materialOld);
            }
            if (materialNew != null && !materialNew.equals(materialOld)) {
                materialNew.getMaterialDoProdutoList().add(materialDoProduto);
                materialNew = em.merge(materialNew);
            }
            if (produtoOld != null && !produtoOld.equals(produtoNew)) {
                produtoOld.getMaterialDoProdutoList().remove(materialDoProduto);
                produtoOld = em.merge(produtoOld);
            }
            if (produtoNew != null && !produtoNew.equals(produtoOld)) {
                produtoNew.getMaterialDoProdutoList().add(materialDoProduto);
                produtoNew = em.merge(produtoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MaterialDoProdutoPK id = materialDoProduto.getMaterialDoProdutoPK();
                if (findMaterialDoProduto(id) == null) {
                    throw new NonexistentEntityException("The materialDoProduto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MaterialDoProdutoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MaterialDoProduto materialDoProduto;
            try {
                materialDoProduto = em.getReference(MaterialDoProduto.class, id);
                materialDoProduto.getMaterialDoProdutoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materialDoProduto with id " + id + " no longer exists.", enfe);
            }
            Material material = materialDoProduto.getMaterial();
            if (material != null) {
                material.getMaterialDoProdutoList().remove(materialDoProduto);
                material = em.merge(material);
            }
            Produto produto = materialDoProduto.getProduto();
            if (produto != null) {
                produto.getMaterialDoProdutoList().remove(materialDoProduto);
                produto = em.merge(produto);
            }
            em.remove(materialDoProduto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MaterialDoProduto> findMaterialDoProdutoEntities() {
        return findMaterialDoProdutoEntities(true, -1, -1);
    }

    public List<MaterialDoProduto> findMaterialDoProdutoEntities(int maxResults, int firstResult) {
        return findMaterialDoProdutoEntities(false, maxResults, firstResult);
    }

    private List<MaterialDoProduto> findMaterialDoProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MaterialDoProduto.class));
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

    public MaterialDoProduto findMaterialDoProduto(MaterialDoProdutoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MaterialDoProduto.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaterialDoProdutoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MaterialDoProduto> rt = cq.from(MaterialDoProduto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
