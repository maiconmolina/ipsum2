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
import model.Funcionario;
import model.Lote;
import model.ProducaoDiaria;
import model.ProducaoDiariaPK;
import model.Produto;

/**
 *
 * @author Maicon
 */
public class ProducaoDiariaJpaController implements Serializable {

    public ProducaoDiariaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProducaoDiaria producaoDiaria) throws PreexistingEntityException, Exception {
        if (producaoDiaria.getProducaoDiariaPK() == null) {
            producaoDiaria.setProducaoDiariaPK(new ProducaoDiariaPK());
        }
        producaoDiaria.getProducaoDiariaPK().setCodfunc(producaoDiaria.getFuncionario().getCodfunc());
        producaoDiaria.getProducaoDiariaPK().setCodlote(producaoDiaria.getLote().getCodlote());
        producaoDiaria.getProducaoDiariaPK().setCodprod(producaoDiaria.getProduto().getCodprod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario funcionario = producaoDiaria.getFuncionario();
            if (funcionario != null) {
                funcionario = em.getReference(funcionario.getClass(), funcionario.getCodfunc());
                producaoDiaria.setFuncionario(funcionario);
            }
            Lote lote = producaoDiaria.getLote();
            if (lote != null) {
                lote = em.getReference(lote.getClass(), lote.getCodlote());
                producaoDiaria.setLote(lote);
            }
            Produto produto = producaoDiaria.getProduto();
            if (produto != null) {
                produto = em.getReference(produto.getClass(), produto.getCodprod());
                producaoDiaria.setProduto(produto);
            }
            em.persist(producaoDiaria);
            if (funcionario != null) {
                funcionario.getProducaoDiariaList().add(producaoDiaria);
                funcionario = em.merge(funcionario);
            }
            if (lote != null) {
                lote.getProducaoDiariaList().add(producaoDiaria);
                lote = em.merge(lote);
            }
            if (produto != null) {
                produto.getProducaoDiariaList().add(producaoDiaria);
                produto = em.merge(produto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProducaoDiaria(producaoDiaria.getProducaoDiariaPK()) != null) {
                throw new PreexistingEntityException("ProducaoDiaria " + producaoDiaria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProducaoDiaria producaoDiaria) throws NonexistentEntityException, Exception {
        producaoDiaria.getProducaoDiariaPK().setCodfunc(producaoDiaria.getFuncionario().getCodfunc());
        producaoDiaria.getProducaoDiariaPK().setCodlote(producaoDiaria.getLote().getCodlote());
        producaoDiaria.getProducaoDiariaPK().setCodprod(producaoDiaria.getProduto().getCodprod());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProducaoDiaria persistentProducaoDiaria = em.find(ProducaoDiaria.class, producaoDiaria.getProducaoDiariaPK());
            Funcionario funcionarioOld = persistentProducaoDiaria.getFuncionario();
            Funcionario funcionarioNew = producaoDiaria.getFuncionario();
            Lote loteOld = persistentProducaoDiaria.getLote();
            Lote loteNew = producaoDiaria.getLote();
            Produto produtoOld = persistentProducaoDiaria.getProduto();
            Produto produtoNew = producaoDiaria.getProduto();
            if (funcionarioNew != null) {
                funcionarioNew = em.getReference(funcionarioNew.getClass(), funcionarioNew.getCodfunc());
                producaoDiaria.setFuncionario(funcionarioNew);
            }
            if (loteNew != null) {
                loteNew = em.getReference(loteNew.getClass(), loteNew.getCodlote());
                producaoDiaria.setLote(loteNew);
            }
            if (produtoNew != null) {
                produtoNew = em.getReference(produtoNew.getClass(), produtoNew.getCodprod());
                producaoDiaria.setProduto(produtoNew);
            }
            producaoDiaria = em.merge(producaoDiaria);
            if (funcionarioOld != null && !funcionarioOld.equals(funcionarioNew)) {
                funcionarioOld.getProducaoDiariaList().remove(producaoDiaria);
                funcionarioOld = em.merge(funcionarioOld);
            }
            if (funcionarioNew != null && !funcionarioNew.equals(funcionarioOld)) {
                funcionarioNew.getProducaoDiariaList().add(producaoDiaria);
                funcionarioNew = em.merge(funcionarioNew);
            }
            if (loteOld != null && !loteOld.equals(loteNew)) {
                loteOld.getProducaoDiariaList().remove(producaoDiaria);
                loteOld = em.merge(loteOld);
            }
            if (loteNew != null && !loteNew.equals(loteOld)) {
                loteNew.getProducaoDiariaList().add(producaoDiaria);
                loteNew = em.merge(loteNew);
            }
            if (produtoOld != null && !produtoOld.equals(produtoNew)) {
                produtoOld.getProducaoDiariaList().remove(producaoDiaria);
                produtoOld = em.merge(produtoOld);
            }
            if (produtoNew != null && !produtoNew.equals(produtoOld)) {
                produtoNew.getProducaoDiariaList().add(producaoDiaria);
                produtoNew = em.merge(produtoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProducaoDiariaPK id = producaoDiaria.getProducaoDiariaPK();
                if (findProducaoDiaria(id) == null) {
                    throw new NonexistentEntityException("The producaoDiaria with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProducaoDiariaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProducaoDiaria producaoDiaria;
            try {
                producaoDiaria = em.getReference(ProducaoDiaria.class, id);
                producaoDiaria.getProducaoDiariaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producaoDiaria with id " + id + " no longer exists.", enfe);
            }
            Funcionario funcionario = producaoDiaria.getFuncionario();
            if (funcionario != null) {
                funcionario.getProducaoDiariaList().remove(producaoDiaria);
                funcionario = em.merge(funcionario);
            }
            Lote lote = producaoDiaria.getLote();
            if (lote != null) {
                lote.getProducaoDiariaList().remove(producaoDiaria);
                lote = em.merge(lote);
            }
            Produto produto = producaoDiaria.getProduto();
            if (produto != null) {
                produto.getProducaoDiariaList().remove(producaoDiaria);
                produto = em.merge(produto);
            }
            em.remove(producaoDiaria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProducaoDiaria> findProducaoDiariaEntities() {
        return findProducaoDiariaEntities(true, -1, -1);
    }

    public List<ProducaoDiaria> findProducaoDiariaEntities(int maxResults, int firstResult) {
        return findProducaoDiariaEntities(false, maxResults, firstResult);
    }

    private List<ProducaoDiaria> findProducaoDiariaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProducaoDiaria.class));
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

    public ProducaoDiaria findProducaoDiaria(ProducaoDiariaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProducaoDiaria.class, id);
        } finally {
            em.close();
        }
    }

    public int getProducaoDiariaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProducaoDiaria> rt = cq.from(ProducaoDiaria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
