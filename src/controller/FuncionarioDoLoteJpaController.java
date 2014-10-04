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
import model.FuncionarioDoLote;
import model.FuncionarioDoLotePK;
import model.Lote;

/**
 *
 * @author Maicon
 */
public class FuncionarioDoLoteJpaController implements Serializable {

    public FuncionarioDoLoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FuncionarioDoLote funcionarioDoLote) throws PreexistingEntityException, Exception {
        if (funcionarioDoLote.getFuncionarioDoLotePK() == null) {
            funcionarioDoLote.setFuncionarioDoLotePK(new FuncionarioDoLotePK());
        }
        funcionarioDoLote.getFuncionarioDoLotePK().setCodlote(funcionarioDoLote.getLote().getCodlote());
        funcionarioDoLote.getFuncionarioDoLotePK().setCodfunc(funcionarioDoLote.getFuncionario().getCodfunc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario funcionario = funcionarioDoLote.getFuncionario();
            if (funcionario != null) {
                funcionario = em.getReference(funcionario.getClass(), funcionario.getCodfunc());
                funcionarioDoLote.setFuncionario(funcionario);
            }
            Lote lote = funcionarioDoLote.getLote();
            if (lote != null) {
                lote = em.getReference(lote.getClass(), lote.getCodlote());
                funcionarioDoLote.setLote(lote);
            }
            em.persist(funcionarioDoLote);
            if (funcionario != null) {
                funcionario.getFuncionarioDoLoteList().add(funcionarioDoLote);
                funcionario = em.merge(funcionario);
            }
            if (lote != null) {
                lote.getFuncionarioDoLoteList().add(funcionarioDoLote);
                lote = em.merge(lote);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFuncionarioDoLote(funcionarioDoLote.getFuncionarioDoLotePK()) != null) {
                throw new PreexistingEntityException("FuncionarioDoLote " + funcionarioDoLote + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FuncionarioDoLote funcionarioDoLote) throws NonexistentEntityException, Exception {
        funcionarioDoLote.getFuncionarioDoLotePK().setCodlote(funcionarioDoLote.getLote().getCodlote());
        funcionarioDoLote.getFuncionarioDoLotePK().setCodfunc(funcionarioDoLote.getFuncionario().getCodfunc());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FuncionarioDoLote persistentFuncionarioDoLote = em.find(FuncionarioDoLote.class, funcionarioDoLote.getFuncionarioDoLotePK());
            Funcionario funcionarioOld = persistentFuncionarioDoLote.getFuncionario();
            Funcionario funcionarioNew = funcionarioDoLote.getFuncionario();
            Lote loteOld = persistentFuncionarioDoLote.getLote();
            Lote loteNew = funcionarioDoLote.getLote();
            if (funcionarioNew != null) {
                funcionarioNew = em.getReference(funcionarioNew.getClass(), funcionarioNew.getCodfunc());
                funcionarioDoLote.setFuncionario(funcionarioNew);
            }
            if (loteNew != null) {
                loteNew = em.getReference(loteNew.getClass(), loteNew.getCodlote());
                funcionarioDoLote.setLote(loteNew);
            }
            funcionarioDoLote = em.merge(funcionarioDoLote);
            if (funcionarioOld != null && !funcionarioOld.equals(funcionarioNew)) {
                funcionarioOld.getFuncionarioDoLoteList().remove(funcionarioDoLote);
                funcionarioOld = em.merge(funcionarioOld);
            }
            if (funcionarioNew != null && !funcionarioNew.equals(funcionarioOld)) {
                funcionarioNew.getFuncionarioDoLoteList().add(funcionarioDoLote);
                funcionarioNew = em.merge(funcionarioNew);
            }
            if (loteOld != null && !loteOld.equals(loteNew)) {
                loteOld.getFuncionarioDoLoteList().remove(funcionarioDoLote);
                loteOld = em.merge(loteOld);
            }
            if (loteNew != null && !loteNew.equals(loteOld)) {
                loteNew.getFuncionarioDoLoteList().add(funcionarioDoLote);
                loteNew = em.merge(loteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                FuncionarioDoLotePK id = funcionarioDoLote.getFuncionarioDoLotePK();
                if (findFuncionarioDoLote(id) == null) {
                    throw new NonexistentEntityException("The funcionarioDoLote with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(FuncionarioDoLotePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FuncionarioDoLote funcionarioDoLote;
            try {
                funcionarioDoLote = em.getReference(FuncionarioDoLote.class, id);
                funcionarioDoLote.getFuncionarioDoLotePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionarioDoLote with id " + id + " no longer exists.", enfe);
            }
            Funcionario funcionario = funcionarioDoLote.getFuncionario();
            if (funcionario != null) {
                funcionario.getFuncionarioDoLoteList().remove(funcionarioDoLote);
                funcionario = em.merge(funcionario);
            }
            Lote lote = funcionarioDoLote.getLote();
            if (lote != null) {
                lote.getFuncionarioDoLoteList().remove(funcionarioDoLote);
                lote = em.merge(lote);
            }
            em.remove(funcionarioDoLote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FuncionarioDoLote> findFuncionarioDoLoteEntities() {
        return findFuncionarioDoLoteEntities(true, -1, -1);
    }

    public List<FuncionarioDoLote> findFuncionarioDoLoteEntities(int maxResults, int firstResult) {
        return findFuncionarioDoLoteEntities(false, maxResults, firstResult);
    }

    private List<FuncionarioDoLote> findFuncionarioDoLoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FuncionarioDoLote.class));
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

    public FuncionarioDoLote findFuncionarioDoLote(FuncionarioDoLotePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FuncionarioDoLote.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionarioDoLoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FuncionarioDoLote> rt = cq.from(FuncionarioDoLote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
