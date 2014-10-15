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
import model.Fornecedor;
import model.MaterialDoProduto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Material;

/**
 *
 * @author Luis
 */
public class MaterialJpaController implements Serializable {

    public MaterialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Material material) throws PreexistingEntityException, Exception {
        if (material.getMaterialDoProdutoList() == null) {
            material.setMaterialDoProdutoList(new ArrayList<MaterialDoProduto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor codfornec = material.getCodfornec();
            if (codfornec != null) {
                codfornec = em.getReference(codfornec.getClass(), codfornec.getCodfornec());
                material.setCodfornec(codfornec);
            }
            List<MaterialDoProduto> attachedMaterialDoProdutoList = new ArrayList<MaterialDoProduto>();
            for (MaterialDoProduto materialDoProdutoListMaterialDoProdutoToAttach : material.getMaterialDoProdutoList()) {
                materialDoProdutoListMaterialDoProdutoToAttach = em.getReference(materialDoProdutoListMaterialDoProdutoToAttach.getClass(), materialDoProdutoListMaterialDoProdutoToAttach.getMaterialDoProdutoPK());
                attachedMaterialDoProdutoList.add(materialDoProdutoListMaterialDoProdutoToAttach);
            }
            material.setMaterialDoProdutoList(attachedMaterialDoProdutoList);
            em.persist(material);
            if (codfornec != null) {
                codfornec.getMaterialList().add(material);
                codfornec = em.merge(codfornec);
            }
            for (MaterialDoProduto materialDoProdutoListMaterialDoProduto : material.getMaterialDoProdutoList()) {
                Material oldMaterialOfMaterialDoProdutoListMaterialDoProduto = materialDoProdutoListMaterialDoProduto.getMaterial();
                materialDoProdutoListMaterialDoProduto.setMaterial(material);
                materialDoProdutoListMaterialDoProduto = em.merge(materialDoProdutoListMaterialDoProduto);
                if (oldMaterialOfMaterialDoProdutoListMaterialDoProduto != null) {
                    oldMaterialOfMaterialDoProdutoListMaterialDoProduto.getMaterialDoProdutoList().remove(materialDoProdutoListMaterialDoProduto);
                    oldMaterialOfMaterialDoProdutoListMaterialDoProduto = em.merge(oldMaterialOfMaterialDoProdutoListMaterialDoProduto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMaterial(material.getCodmat()) != null) {
                throw new PreexistingEntityException("Material " + material + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Material material) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Material persistentMaterial = em.find(Material.class, material.getCodmat());
            Fornecedor codfornecOld = persistentMaterial.getCodfornec();
            Fornecedor codfornecNew = material.getCodfornec();
            List<MaterialDoProduto> materialDoProdutoListOld = persistentMaterial.getMaterialDoProdutoList();
            List<MaterialDoProduto> materialDoProdutoListNew = material.getMaterialDoProdutoList();
            List<String> illegalOrphanMessages = null;
            for (MaterialDoProduto materialDoProdutoListOldMaterialDoProduto : materialDoProdutoListOld) {
                if (!materialDoProdutoListNew.contains(materialDoProdutoListOldMaterialDoProduto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MaterialDoProduto " + materialDoProdutoListOldMaterialDoProduto + " since its material field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codfornecNew != null) {
                codfornecNew = em.getReference(codfornecNew.getClass(), codfornecNew.getCodfornec());
                material.setCodfornec(codfornecNew);
            }
            List<MaterialDoProduto> attachedMaterialDoProdutoListNew = new ArrayList<MaterialDoProduto>();
            for (MaterialDoProduto materialDoProdutoListNewMaterialDoProdutoToAttach : materialDoProdutoListNew) {
                materialDoProdutoListNewMaterialDoProdutoToAttach = em.getReference(materialDoProdutoListNewMaterialDoProdutoToAttach.getClass(), materialDoProdutoListNewMaterialDoProdutoToAttach.getMaterialDoProdutoPK());
                attachedMaterialDoProdutoListNew.add(materialDoProdutoListNewMaterialDoProdutoToAttach);
            }
            materialDoProdutoListNew = attachedMaterialDoProdutoListNew;
            material.setMaterialDoProdutoList(materialDoProdutoListNew);
            material = em.merge(material);
            if (codfornecOld != null && !codfornecOld.equals(codfornecNew)) {
                codfornecOld.getMaterialList().remove(material);
                codfornecOld = em.merge(codfornecOld);
            }
            if (codfornecNew != null && !codfornecNew.equals(codfornecOld)) {
                codfornecNew.getMaterialList().add(material);
                codfornecNew = em.merge(codfornecNew);
            }
            for (MaterialDoProduto materialDoProdutoListNewMaterialDoProduto : materialDoProdutoListNew) {
                if (!materialDoProdutoListOld.contains(materialDoProdutoListNewMaterialDoProduto)) {
                    Material oldMaterialOfMaterialDoProdutoListNewMaterialDoProduto = materialDoProdutoListNewMaterialDoProduto.getMaterial();
                    materialDoProdutoListNewMaterialDoProduto.setMaterial(material);
                    materialDoProdutoListNewMaterialDoProduto = em.merge(materialDoProdutoListNewMaterialDoProduto);
                    if (oldMaterialOfMaterialDoProdutoListNewMaterialDoProduto != null && !oldMaterialOfMaterialDoProdutoListNewMaterialDoProduto.equals(material)) {
                        oldMaterialOfMaterialDoProdutoListNewMaterialDoProduto.getMaterialDoProdutoList().remove(materialDoProdutoListNewMaterialDoProduto);
                        oldMaterialOfMaterialDoProdutoListNewMaterialDoProduto = em.merge(oldMaterialOfMaterialDoProdutoListNewMaterialDoProduto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = material.getCodmat();
                if (findMaterial(id) == null) {
                    throw new NonexistentEntityException("The material with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Material material;
            try {
                material = em.getReference(Material.class, id);
                material.getCodmat();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The material with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MaterialDoProduto> materialDoProdutoListOrphanCheck = material.getMaterialDoProdutoList();
            for (MaterialDoProduto materialDoProdutoListOrphanCheckMaterialDoProduto : materialDoProdutoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Material (" + material + ") cannot be destroyed since the MaterialDoProduto " + materialDoProdutoListOrphanCheckMaterialDoProduto + " in its materialDoProdutoList field has a non-nullable material field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Fornecedor codfornec = material.getCodfornec();
            if (codfornec != null) {
                codfornec.getMaterialList().remove(material);
                codfornec = em.merge(codfornec);
            }
            em.remove(material);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Material> findMaterialEntities() {
        return findMaterialEntities(true, -1, -1);
    }

    public List<Material> findMaterialEntities(int maxResults, int firstResult) {
        return findMaterialEntities(false, maxResults, firstResult);
    }

    private List<Material> findMaterialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Material.class));
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

    public Material findMaterial(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Material.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaterialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Material> rt = cq.from(Material.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int ultimoId() {
        List<Material> list;
        Material prod;
        MaterialJpaController materialController = new MaterialJpaController(ipsum2.Ipsum2.getFactory());
        list = materialController.getEntityManager().createNamedQuery("Material.findAll").getResultList();
        if (!list.isEmpty()) {
            prod = list.get(list.size() - 1);
            if (prod.getCodmat() > 0) {
                return prod.getCodmat() + 1;
            }
        }
        return 1;
    }

    public List<Material> getAll() {
        MaterialJpaController ctrl = new MaterialJpaController(ipsum2.Ipsum2.getFactory());
        return ctrl.getEntityManager().createNamedQuery("Material.findAll").getResultList();
    }
}
