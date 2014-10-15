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
import model.ProdutoDoLote;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.ProducaoDiaria;
import model.MaterialDoProduto;
import model.Produto;

/**
 *
 * @author Luis
 */
public class ProdutoJpaController implements Serializable {

    public ProdutoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Produto produto) throws PreexistingEntityException, Exception {
        if (produto.getProdutoDoLoteList() == null) {
            produto.setProdutoDoLoteList(new ArrayList<ProdutoDoLote>());
        }
        if (produto.getProducaoDiariaList() == null) {
            produto.setProducaoDiariaList(new ArrayList<ProducaoDiaria>());
        }
        if (produto.getMaterialDoProdutoList() == null) {
            produto.setMaterialDoProdutoList(new ArrayList<MaterialDoProduto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ProdutoDoLote> attachedProdutoDoLoteList = new ArrayList<ProdutoDoLote>();
            for (ProdutoDoLote produtoDoLoteListProdutoDoLoteToAttach : produto.getProdutoDoLoteList()) {
                produtoDoLoteListProdutoDoLoteToAttach = em.getReference(produtoDoLoteListProdutoDoLoteToAttach.getClass(), produtoDoLoteListProdutoDoLoteToAttach.getProdutoDoLotePK());
                attachedProdutoDoLoteList.add(produtoDoLoteListProdutoDoLoteToAttach);
            }
            produto.setProdutoDoLoteList(attachedProdutoDoLoteList);
            List<ProducaoDiaria> attachedProducaoDiariaList = new ArrayList<ProducaoDiaria>();
            for (ProducaoDiaria producaoDiariaListProducaoDiariaToAttach : produto.getProducaoDiariaList()) {
                producaoDiariaListProducaoDiariaToAttach = em.getReference(producaoDiariaListProducaoDiariaToAttach.getClass(), producaoDiariaListProducaoDiariaToAttach.getProducaoDiariaPK());
                attachedProducaoDiariaList.add(producaoDiariaListProducaoDiariaToAttach);
            }
            produto.setProducaoDiariaList(attachedProducaoDiariaList);
            List<MaterialDoProduto> attachedMaterialDoProdutoList = new ArrayList<MaterialDoProduto>();
            for (MaterialDoProduto materialDoProdutoListMaterialDoProdutoToAttach : produto.getMaterialDoProdutoList()) {
                materialDoProdutoListMaterialDoProdutoToAttach = em.getReference(materialDoProdutoListMaterialDoProdutoToAttach.getClass(), materialDoProdutoListMaterialDoProdutoToAttach.getMaterialDoProdutoPK());
                attachedMaterialDoProdutoList.add(materialDoProdutoListMaterialDoProdutoToAttach);
            }
            produto.setMaterialDoProdutoList(attachedMaterialDoProdutoList);
            em.persist(produto);
            for (ProdutoDoLote produtoDoLoteListProdutoDoLote : produto.getProdutoDoLoteList()) {
                Produto oldProdutoOfProdutoDoLoteListProdutoDoLote = produtoDoLoteListProdutoDoLote.getProduto();
                produtoDoLoteListProdutoDoLote.setProduto(produto);
                produtoDoLoteListProdutoDoLote = em.merge(produtoDoLoteListProdutoDoLote);
                if (oldProdutoOfProdutoDoLoteListProdutoDoLote != null) {
                    oldProdutoOfProdutoDoLoteListProdutoDoLote.getProdutoDoLoteList().remove(produtoDoLoteListProdutoDoLote);
                    oldProdutoOfProdutoDoLoteListProdutoDoLote = em.merge(oldProdutoOfProdutoDoLoteListProdutoDoLote);
                }
            }
            for (ProducaoDiaria producaoDiariaListProducaoDiaria : produto.getProducaoDiariaList()) {
                Produto oldProdutoOfProducaoDiariaListProducaoDiaria = producaoDiariaListProducaoDiaria.getProduto();
                producaoDiariaListProducaoDiaria.setProduto(produto);
                producaoDiariaListProducaoDiaria = em.merge(producaoDiariaListProducaoDiaria);
                if (oldProdutoOfProducaoDiariaListProducaoDiaria != null) {
                    oldProdutoOfProducaoDiariaListProducaoDiaria.getProducaoDiariaList().remove(producaoDiariaListProducaoDiaria);
                    oldProdutoOfProducaoDiariaListProducaoDiaria = em.merge(oldProdutoOfProducaoDiariaListProducaoDiaria);
                }
            }
            for (MaterialDoProduto materialDoProdutoListMaterialDoProduto : produto.getMaterialDoProdutoList()) {
                Produto oldProdutoOfMaterialDoProdutoListMaterialDoProduto = materialDoProdutoListMaterialDoProduto.getProduto();
                materialDoProdutoListMaterialDoProduto.setProduto(produto);
                materialDoProdutoListMaterialDoProduto = em.merge(materialDoProdutoListMaterialDoProduto);
                if (oldProdutoOfMaterialDoProdutoListMaterialDoProduto != null) {
                    oldProdutoOfMaterialDoProdutoListMaterialDoProduto.getMaterialDoProdutoList().remove(materialDoProdutoListMaterialDoProduto);
                    oldProdutoOfMaterialDoProdutoListMaterialDoProduto = em.merge(oldProdutoOfMaterialDoProdutoListMaterialDoProduto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProduto(produto.getCodprod()) != null) {
                throw new PreexistingEntityException("Produto " + produto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Produto produto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto persistentProduto = em.find(Produto.class, produto.getCodprod());
            List<ProdutoDoLote> produtoDoLoteListOld = persistentProduto.getProdutoDoLoteList();
            List<ProdutoDoLote> produtoDoLoteListNew = produto.getProdutoDoLoteList();
            List<ProducaoDiaria> producaoDiariaListOld = persistentProduto.getProducaoDiariaList();
            List<ProducaoDiaria> producaoDiariaListNew = produto.getProducaoDiariaList();
            List<MaterialDoProduto> materialDoProdutoListOld = persistentProduto.getMaterialDoProdutoList();
            List<MaterialDoProduto> materialDoProdutoListNew = produto.getMaterialDoProdutoList();
            List<String> illegalOrphanMessages = null;
            for (ProdutoDoLote produtoDoLoteListOldProdutoDoLote : produtoDoLoteListOld) {
                if (!produtoDoLoteListNew.contains(produtoDoLoteListOldProdutoDoLote)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProdutoDoLote " + produtoDoLoteListOldProdutoDoLote + " since its produto field is not nullable.");
                }
            }
            for (ProducaoDiaria producaoDiariaListOldProducaoDiaria : producaoDiariaListOld) {
                if (!producaoDiariaListNew.contains(producaoDiariaListOldProducaoDiaria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProducaoDiaria " + producaoDiariaListOldProducaoDiaria + " since its produto field is not nullable.");
                }
            }
            for (MaterialDoProduto materialDoProdutoListOldMaterialDoProduto : materialDoProdutoListOld) {
                if (!materialDoProdutoListNew.contains(materialDoProdutoListOldMaterialDoProduto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MaterialDoProduto " + materialDoProdutoListOldMaterialDoProduto + " since its produto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ProdutoDoLote> attachedProdutoDoLoteListNew = new ArrayList<ProdutoDoLote>();
            for (ProdutoDoLote produtoDoLoteListNewProdutoDoLoteToAttach : produtoDoLoteListNew) {
                produtoDoLoteListNewProdutoDoLoteToAttach = em.getReference(produtoDoLoteListNewProdutoDoLoteToAttach.getClass(), produtoDoLoteListNewProdutoDoLoteToAttach.getProdutoDoLotePK());
                attachedProdutoDoLoteListNew.add(produtoDoLoteListNewProdutoDoLoteToAttach);
            }
            produtoDoLoteListNew = attachedProdutoDoLoteListNew;
            produto.setProdutoDoLoteList(produtoDoLoteListNew);
            List<ProducaoDiaria> attachedProducaoDiariaListNew = new ArrayList<ProducaoDiaria>();
            for (ProducaoDiaria producaoDiariaListNewProducaoDiariaToAttach : producaoDiariaListNew) {
                producaoDiariaListNewProducaoDiariaToAttach = em.getReference(producaoDiariaListNewProducaoDiariaToAttach.getClass(), producaoDiariaListNewProducaoDiariaToAttach.getProducaoDiariaPK());
                attachedProducaoDiariaListNew.add(producaoDiariaListNewProducaoDiariaToAttach);
            }
            producaoDiariaListNew = attachedProducaoDiariaListNew;
            produto.setProducaoDiariaList(producaoDiariaListNew);
            List<MaterialDoProduto> attachedMaterialDoProdutoListNew = new ArrayList<MaterialDoProduto>();
            for (MaterialDoProduto materialDoProdutoListNewMaterialDoProdutoToAttach : materialDoProdutoListNew) {
                materialDoProdutoListNewMaterialDoProdutoToAttach = em.getReference(materialDoProdutoListNewMaterialDoProdutoToAttach.getClass(), materialDoProdutoListNewMaterialDoProdutoToAttach.getMaterialDoProdutoPK());
                attachedMaterialDoProdutoListNew.add(materialDoProdutoListNewMaterialDoProdutoToAttach);
            }
            materialDoProdutoListNew = attachedMaterialDoProdutoListNew;
            produto.setMaterialDoProdutoList(materialDoProdutoListNew);
            produto = em.merge(produto);
            for (ProdutoDoLote produtoDoLoteListNewProdutoDoLote : produtoDoLoteListNew) {
                if (!produtoDoLoteListOld.contains(produtoDoLoteListNewProdutoDoLote)) {
                    Produto oldProdutoOfProdutoDoLoteListNewProdutoDoLote = produtoDoLoteListNewProdutoDoLote.getProduto();
                    produtoDoLoteListNewProdutoDoLote.setProduto(produto);
                    produtoDoLoteListNewProdutoDoLote = em.merge(produtoDoLoteListNewProdutoDoLote);
                    if (oldProdutoOfProdutoDoLoteListNewProdutoDoLote != null && !oldProdutoOfProdutoDoLoteListNewProdutoDoLote.equals(produto)) {
                        oldProdutoOfProdutoDoLoteListNewProdutoDoLote.getProdutoDoLoteList().remove(produtoDoLoteListNewProdutoDoLote);
                        oldProdutoOfProdutoDoLoteListNewProdutoDoLote = em.merge(oldProdutoOfProdutoDoLoteListNewProdutoDoLote);
                    }
                }
            }
            for (ProducaoDiaria producaoDiariaListNewProducaoDiaria : producaoDiariaListNew) {
                if (!producaoDiariaListOld.contains(producaoDiariaListNewProducaoDiaria)) {
                    Produto oldProdutoOfProducaoDiariaListNewProducaoDiaria = producaoDiariaListNewProducaoDiaria.getProduto();
                    producaoDiariaListNewProducaoDiaria.setProduto(produto);
                    producaoDiariaListNewProducaoDiaria = em.merge(producaoDiariaListNewProducaoDiaria);
                    if (oldProdutoOfProducaoDiariaListNewProducaoDiaria != null && !oldProdutoOfProducaoDiariaListNewProducaoDiaria.equals(produto)) {
                        oldProdutoOfProducaoDiariaListNewProducaoDiaria.getProducaoDiariaList().remove(producaoDiariaListNewProducaoDiaria);
                        oldProdutoOfProducaoDiariaListNewProducaoDiaria = em.merge(oldProdutoOfProducaoDiariaListNewProducaoDiaria);
                    }
                }
            }
            for (MaterialDoProduto materialDoProdutoListNewMaterialDoProduto : materialDoProdutoListNew) {
                if (!materialDoProdutoListOld.contains(materialDoProdutoListNewMaterialDoProduto)) {
                    Produto oldProdutoOfMaterialDoProdutoListNewMaterialDoProduto = materialDoProdutoListNewMaterialDoProduto.getProduto();
                    materialDoProdutoListNewMaterialDoProduto.setProduto(produto);
                    materialDoProdutoListNewMaterialDoProduto = em.merge(materialDoProdutoListNewMaterialDoProduto);
                    if (oldProdutoOfMaterialDoProdutoListNewMaterialDoProduto != null && !oldProdutoOfMaterialDoProdutoListNewMaterialDoProduto.equals(produto)) {
                        oldProdutoOfMaterialDoProdutoListNewMaterialDoProduto.getMaterialDoProdutoList().remove(materialDoProdutoListNewMaterialDoProduto);
                        oldProdutoOfMaterialDoProdutoListNewMaterialDoProduto = em.merge(oldProdutoOfMaterialDoProdutoListNewMaterialDoProduto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = produto.getCodprod();
                if (findProduto(id) == null) {
                    throw new NonexistentEntityException("The produto with id " + id + " no longer exists.");
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
            Produto produto;
            try {
                produto = em.getReference(Produto.class, id);
                produto.getCodprod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProdutoDoLote> produtoDoLoteListOrphanCheck = produto.getProdutoDoLoteList();
            for (ProdutoDoLote produtoDoLoteListOrphanCheckProdutoDoLote : produtoDoLoteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Produto (" + produto + ") cannot be destroyed since the ProdutoDoLote " + produtoDoLoteListOrphanCheckProdutoDoLote + " in its produtoDoLoteList field has a non-nullable produto field.");
            }
            List<ProducaoDiaria> producaoDiariaListOrphanCheck = produto.getProducaoDiariaList();
            for (ProducaoDiaria producaoDiariaListOrphanCheckProducaoDiaria : producaoDiariaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Produto (" + produto + ") cannot be destroyed since the ProducaoDiaria " + producaoDiariaListOrphanCheckProducaoDiaria + " in its producaoDiariaList field has a non-nullable produto field.");
            }
            List<MaterialDoProduto> materialDoProdutoListOrphanCheck = produto.getMaterialDoProdutoList();
            for (MaterialDoProduto materialDoProdutoListOrphanCheckMaterialDoProduto : materialDoProdutoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Produto (" + produto + ") cannot be destroyed since the MaterialDoProduto " + materialDoProdutoListOrphanCheckMaterialDoProduto + " in its materialDoProdutoList field has a non-nullable produto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(produto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Produto> findProdutoEntities() {
        return findProdutoEntities(true, -1, -1);
    }

    public List<Produto> findProdutoEntities(int maxResults, int firstResult) {
        return findProdutoEntities(false, maxResults, firstResult);
    }

    private List<Produto> findProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Produto.class));
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

    public Produto findProduto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Produto> rt = cq.from(Produto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public int ultimoId() {
        List<Produto> listProd;
        Produto prod;
        ProdutoJpaController produtoController = new ProdutoJpaController(ipsum2.Ipsum2.getFactory());
        listProd = produtoController.getEntityManager().createNamedQuery("Produto.findAll").getResultList();
        if (!listProd.isEmpty()) {
            prod = listProd.get(listProd.size() - 1);
            if (prod.getCodprod() > 0) {
                return prod.getCodprod() + 1;
            }
        }
        return 1;
    }

}
