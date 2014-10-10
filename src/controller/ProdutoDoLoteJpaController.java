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
import model.Lote;
import model.Produto;
import model.ProdutoDoLote;
import model.ProdutoDoLotePK;

/**
 *
 * @author Luis
 */
public class ProdutoDoLoteJpaController implements Serializable {

    public ProdutoDoLoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProdutoDoLote produtoDoLote) throws PreexistingEntityException, Exception {
        if (produtoDoLote.getProdutoDoLotePK() == null) {
            produtoDoLote.setProdutoDoLotePK(new ProdutoDoLotePK());
        }
        produtoDoLote.getProdutoDoLotePK().setCodprod(produtoDoLote.getProduto().getCodprod());
        produtoDoLote.getProdutoDoLotePK().setCodlote(produtoDoLote.getLote().getCodlote());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lote lote = produtoDoLote.getLote();
            if (lote != null) {
                lote = em.getReference(lote.getClass(), lote.getCodlote());
                produtoDoLote.setLote(lote);
            }
            Produto produto = produtoDoLote.getProduto();
            if (produto != null) {
                produto = em.getReference(produto.getClass(), produto.getCodprod());
                produtoDoLote.setProduto(produto);
            }
            em.persist(produtoDoLote);
            if (lote != null) {
                lote.getProdutoDoLoteList().add(produtoDoLote);
                lote = em.merge(lote);
            }
            if (produto != null) {
                produto.getProdutoDoLoteList().add(produtoDoLote);
                produto = em.merge(produto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProdutoDoLote(produtoDoLote.getProdutoDoLotePK()) != null) {
                throw new PreexistingEntityException("ProdutoDoLote " + produtoDoLote + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProdutoDoLote produtoDoLote) throws NonexistentEntityException, Exception {
        produtoDoLote.getProdutoDoLotePK().setCodprod(produtoDoLote.getProduto().getCodprod());
        produtoDoLote.getProdutoDoLotePK().setCodlote(produtoDoLote.getLote().getCodlote());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProdutoDoLote persistentProdutoDoLote = em.find(ProdutoDoLote.class, produtoDoLote.getProdutoDoLotePK());
            Lote loteOld = persistentProdutoDoLote.getLote();
            Lote loteNew = produtoDoLote.getLote();
            Produto produtoOld = persistentProdutoDoLote.getProduto();
            Produto produtoNew = produtoDoLote.getProduto();
            if (loteNew != null) {
                loteNew = em.getReference(loteNew.getClass(), loteNew.getCodlote());
                produtoDoLote.setLote(loteNew);
            }
            if (produtoNew != null) {
                produtoNew = em.getReference(produtoNew.getClass(), produtoNew.getCodprod());
                produtoDoLote.setProduto(produtoNew);
            }
            produtoDoLote = em.merge(produtoDoLote);
            if (loteOld != null && !loteOld.equals(loteNew)) {
                loteOld.getProdutoDoLoteList().remove(produtoDoLote);
                loteOld = em.merge(loteOld);
            }
            if (loteNew != null && !loteNew.equals(loteOld)) {
                loteNew.getProdutoDoLoteList().add(produtoDoLote);
                loteNew = em.merge(loteNew);
            }
            if (produtoOld != null && !produtoOld.equals(produtoNew)) {
                produtoOld.getProdutoDoLoteList().remove(produtoDoLote);
                produtoOld = em.merge(produtoOld);
            }
            if (produtoNew != null && !produtoNew.equals(produtoOld)) {
                produtoNew.getProdutoDoLoteList().add(produtoDoLote);
                produtoNew = em.merge(produtoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProdutoDoLotePK id = produtoDoLote.getProdutoDoLotePK();
                if (findProdutoDoLote(id) == null) {
                    throw new NonexistentEntityException("The produtoDoLote with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProdutoDoLotePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProdutoDoLote produtoDoLote;
            try {
                produtoDoLote = em.getReference(ProdutoDoLote.class, id);
                produtoDoLote.getProdutoDoLotePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produtoDoLote with id " + id + " no longer exists.", enfe);
            }
            Lote lote = produtoDoLote.getLote();
            if (lote != null) {
                lote.getProdutoDoLoteList().remove(produtoDoLote);
                lote = em.merge(lote);
            }
            Produto produto = produtoDoLote.getProduto();
            if (produto != null) {
                produto.getProdutoDoLoteList().remove(produtoDoLote);
                produto = em.merge(produto);
            }
            em.remove(produtoDoLote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProdutoDoLote> findProdutoDoLoteEntities() {
        return findProdutoDoLoteEntities(true, -1, -1);
    }

    public List<ProdutoDoLote> findProdutoDoLoteEntities(int maxResults, int firstResult) {
        return findProdutoDoLoteEntities(false, maxResults, firstResult);
    }

    private List<ProdutoDoLote> findProdutoDoLoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProdutoDoLote.class));
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

    public ProdutoDoLote findProdutoDoLote(ProdutoDoLotePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProdutoDoLote.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutoDoLoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProdutoDoLote> rt = cq.from(ProdutoDoLote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
