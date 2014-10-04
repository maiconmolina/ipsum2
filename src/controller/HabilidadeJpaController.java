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
import model.Habilidade;

/**
 *
 * @author Maicon
 */
public class HabilidadeJpaController implements Serializable {

    public HabilidadeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habilidade habilidade) throws PreexistingEntityException, Exception {
        if (habilidade.getFuncionarioList() == null) {
            habilidade.setFuncionarioList(new ArrayList<Funcionario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Funcionario> attachedFuncionarioList = new ArrayList<Funcionario>();
            for (Funcionario funcionarioListFuncionarioToAttach : habilidade.getFuncionarioList()) {
                funcionarioListFuncionarioToAttach = em.getReference(funcionarioListFuncionarioToAttach.getClass(), funcionarioListFuncionarioToAttach.getCodfunc());
                attachedFuncionarioList.add(funcionarioListFuncionarioToAttach);
            }
            habilidade.setFuncionarioList(attachedFuncionarioList);
            em.persist(habilidade);
            for (Funcionario funcionarioListFuncionario : habilidade.getFuncionarioList()) {
                Habilidade oldCodhabOfFuncionarioListFuncionario = funcionarioListFuncionario.getCodhab();
                funcionarioListFuncionario.setCodhab(habilidade);
                funcionarioListFuncionario = em.merge(funcionarioListFuncionario);
                if (oldCodhabOfFuncionarioListFuncionario != null) {
                    oldCodhabOfFuncionarioListFuncionario.getFuncionarioList().remove(funcionarioListFuncionario);
                    oldCodhabOfFuncionarioListFuncionario = em.merge(oldCodhabOfFuncionarioListFuncionario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHabilidade(habilidade.getCodhab()) != null) {
                throw new PreexistingEntityException("Habilidade " + habilidade + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Habilidade habilidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habilidade persistentHabilidade = em.find(Habilidade.class, habilidade.getCodhab());
            List<Funcionario> funcionarioListOld = persistentHabilidade.getFuncionarioList();
            List<Funcionario> funcionarioListNew = habilidade.getFuncionarioList();
            List<Funcionario> attachedFuncionarioListNew = new ArrayList<Funcionario>();
            for (Funcionario funcionarioListNewFuncionarioToAttach : funcionarioListNew) {
                funcionarioListNewFuncionarioToAttach = em.getReference(funcionarioListNewFuncionarioToAttach.getClass(), funcionarioListNewFuncionarioToAttach.getCodfunc());
                attachedFuncionarioListNew.add(funcionarioListNewFuncionarioToAttach);
            }
            funcionarioListNew = attachedFuncionarioListNew;
            habilidade.setFuncionarioList(funcionarioListNew);
            habilidade = em.merge(habilidade);
            for (Funcionario funcionarioListOldFuncionario : funcionarioListOld) {
                if (!funcionarioListNew.contains(funcionarioListOldFuncionario)) {
                    funcionarioListOldFuncionario.setCodhab(null);
                    funcionarioListOldFuncionario = em.merge(funcionarioListOldFuncionario);
                }
            }
            for (Funcionario funcionarioListNewFuncionario : funcionarioListNew) {
                if (!funcionarioListOld.contains(funcionarioListNewFuncionario)) {
                    Habilidade oldCodhabOfFuncionarioListNewFuncionario = funcionarioListNewFuncionario.getCodhab();
                    funcionarioListNewFuncionario.setCodhab(habilidade);
                    funcionarioListNewFuncionario = em.merge(funcionarioListNewFuncionario);
                    if (oldCodhabOfFuncionarioListNewFuncionario != null && !oldCodhabOfFuncionarioListNewFuncionario.equals(habilidade)) {
                        oldCodhabOfFuncionarioListNewFuncionario.getFuncionarioList().remove(funcionarioListNewFuncionario);
                        oldCodhabOfFuncionarioListNewFuncionario = em.merge(oldCodhabOfFuncionarioListNewFuncionario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = habilidade.getCodhab();
                if (findHabilidade(id) == null) {
                    throw new NonexistentEntityException("The habilidade with id " + id + " no longer exists.");
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
            Habilidade habilidade;
            try {
                habilidade = em.getReference(Habilidade.class, id);
                habilidade.getCodhab();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habilidade with id " + id + " no longer exists.", enfe);
            }
            List<Funcionario> funcionarioList = habilidade.getFuncionarioList();
            for (Funcionario funcionarioListFuncionario : funcionarioList) {
                funcionarioListFuncionario.setCodhab(null);
                funcionarioListFuncionario = em.merge(funcionarioListFuncionario);
            }
            em.remove(habilidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habilidade> findHabilidadeEntities() {
        return findHabilidadeEntities(true, -1, -1);
    }

    public List<Habilidade> findHabilidadeEntities(int maxResults, int firstResult) {
        return findHabilidadeEntities(false, maxResults, firstResult);
    }

    private List<Habilidade> findHabilidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habilidade.class));
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

    public Habilidade findHabilidade(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habilidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabilidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Habilidade> rt = cq.from(Habilidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
