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
import model.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Funcao;

/**
 *
 * @author Maicon
 */
public class FuncaoJpaController implements Serializable {

    public FuncaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Funcao funcao) throws PreexistingEntityException, Exception {
        if (funcao.getFuncionarioList() == null) {
            funcao.setFuncionarioList(new ArrayList<Funcionario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Funcionario> attachedFuncionarioList = new ArrayList<Funcionario>();
            for (Funcionario funcionarioListFuncionarioToAttach : funcao.getFuncionarioList()) {
                funcionarioListFuncionarioToAttach = em.getReference(funcionarioListFuncionarioToAttach.getClass(), funcionarioListFuncionarioToAttach.getCodfunc());
                attachedFuncionarioList.add(funcionarioListFuncionarioToAttach);
            }
            funcao.setFuncionarioList(attachedFuncionarioList);
            em.persist(funcao);
            for (Funcionario funcionarioListFuncionario : funcao.getFuncionarioList()) {
                Funcao oldCodfuncaoOfFuncionarioListFuncionario = funcionarioListFuncionario.getCodfuncao();
                funcionarioListFuncionario.setCodfuncao(funcao);
                funcionarioListFuncionario = em.merge(funcionarioListFuncionario);
                if (oldCodfuncaoOfFuncionarioListFuncionario != null) {
                    oldCodfuncaoOfFuncionarioListFuncionario.getFuncionarioList().remove(funcionarioListFuncionario);
                    oldCodfuncaoOfFuncionarioListFuncionario = em.merge(oldCodfuncaoOfFuncionarioListFuncionario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFuncao(funcao.getCodfuncao()) != null) {
                throw new PreexistingEntityException("Funcao " + funcao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcao funcao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcao persistentFuncao = em.find(Funcao.class, funcao.getCodfuncao());
            List<Funcionario> funcionarioListOld = persistentFuncao.getFuncionarioList();
            List<Funcionario> funcionarioListNew = funcao.getFuncionarioList();
            List<Funcionario> attachedFuncionarioListNew = new ArrayList<Funcionario>();
            for (Funcionario funcionarioListNewFuncionarioToAttach : funcionarioListNew) {
                funcionarioListNewFuncionarioToAttach = em.getReference(funcionarioListNewFuncionarioToAttach.getClass(), funcionarioListNewFuncionarioToAttach.getCodfunc());
                attachedFuncionarioListNew.add(funcionarioListNewFuncionarioToAttach);
            }
            funcionarioListNew = attachedFuncionarioListNew;
            funcao.setFuncionarioList(funcionarioListNew);
            funcao = em.merge(funcao);
            for (Funcionario funcionarioListOldFuncionario : funcionarioListOld) {
                if (!funcionarioListNew.contains(funcionarioListOldFuncionario)) {
                    funcionarioListOldFuncionario.setCodfuncao(null);
                    funcionarioListOldFuncionario = em.merge(funcionarioListOldFuncionario);
                }
            }
            for (Funcionario funcionarioListNewFuncionario : funcionarioListNew) {
                if (!funcionarioListOld.contains(funcionarioListNewFuncionario)) {
                    Funcao oldCodfuncaoOfFuncionarioListNewFuncionario = funcionarioListNewFuncionario.getCodfuncao();
                    funcionarioListNewFuncionario.setCodfuncao(funcao);
                    funcionarioListNewFuncionario = em.merge(funcionarioListNewFuncionario);
                    if (oldCodfuncaoOfFuncionarioListNewFuncionario != null && !oldCodfuncaoOfFuncionarioListNewFuncionario.equals(funcao)) {
                        oldCodfuncaoOfFuncionarioListNewFuncionario.getFuncionarioList().remove(funcionarioListNewFuncionario);
                        oldCodfuncaoOfFuncionarioListNewFuncionario = em.merge(oldCodfuncaoOfFuncionarioListNewFuncionario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = funcao.getCodfuncao();
                if (findFuncao(id) == null) {
                    throw new NonexistentEntityException("The funcao with id " + id + " no longer exists.");
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
            Funcao funcao;
            try {
                funcao = em.getReference(Funcao.class, id);
                funcao.getCodfuncao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcao with id " + id + " no longer exists.", enfe);
            }
            List<Funcionario> funcionarioList = funcao.getFuncionarioList();
            for (Funcionario funcionarioListFuncionario : funcionarioList) {
                funcionarioListFuncionario.setCodfuncao(null);
                funcionarioListFuncionario = em.merge(funcionarioListFuncionario);
            }
            em.remove(funcao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcao> findFuncaoEntities() {
        return findFuncaoEntities(true, -1, -1);
    }

    public List<Funcao> findFuncaoEntities(int maxResults, int firstResult) {
        return findFuncaoEntities(false, maxResults, firstResult);
    }

    private List<Funcao> findFuncaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcao.class));
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

    public Funcao findFuncao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcao.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcao> rt = cq.from(Funcao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
