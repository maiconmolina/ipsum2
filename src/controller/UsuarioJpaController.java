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
import model.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Usuario;

/**
 *
 * @author Maicon
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Fornecedor fornecedorOrphanCheck = usuario.getFornecedor();
        if (fornecedorOrphanCheck != null) {
            Usuario oldUsuarioOfFornecedor = fornecedorOrphanCheck.getUsuario();
            if (oldUsuarioOfFornecedor != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Fornecedor " + fornecedorOrphanCheck + " already has an item of type Usuario whose fornecedor column cannot be null. Please make another selection for the fornecedor field.");
            }
        }
        Funcionario funcionarioOrphanCheck = usuario.getFuncionario();
        if (funcionarioOrphanCheck != null) {
            Usuario oldUsuarioOfFuncionario = funcionarioOrphanCheck.getUsuario();
            if (oldUsuarioOfFuncionario != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Funcionario " + funcionarioOrphanCheck + " already has an item of type Usuario whose funcionario column cannot be null. Please make another selection for the funcionario field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor fornecedor = usuario.getFornecedor();
            if (fornecedor != null) {
                fornecedor = em.getReference(fornecedor.getClass(), fornecedor.getCodfornec());
                usuario.setFornecedor(fornecedor);
            }
            Funcionario funcionario = usuario.getFuncionario();
            if (funcionario != null) {
                funcionario = em.getReference(funcionario.getClass(), funcionario.getCodfunc());
                usuario.setFuncionario(funcionario);
            }
            em.persist(usuario);
            if (fornecedor != null) {
                fornecedor.setUsuario(usuario);
                fornecedor = em.merge(fornecedor);
            }
            if (funcionario != null) {
                funcionario.setUsuario(usuario);
                funcionario = em.merge(funcionario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getCodigo()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getCodigo());
            Fornecedor fornecedorOld = persistentUsuario.getFornecedor();
            Fornecedor fornecedorNew = usuario.getFornecedor();
            Funcionario funcionarioOld = persistentUsuario.getFuncionario();
            Funcionario funcionarioNew = usuario.getFuncionario();
            List<String> illegalOrphanMessages = null;
            if (fornecedorNew != null && !fornecedorNew.equals(fornecedorOld)) {
                Usuario oldUsuarioOfFornecedor = fornecedorNew.getUsuario();
                if (oldUsuarioOfFornecedor != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Fornecedor " + fornecedorNew + " already has an item of type Usuario whose fornecedor column cannot be null. Please make another selection for the fornecedor field.");
                }
            }
            if (funcionarioNew != null && !funcionarioNew.equals(funcionarioOld)) {
                Usuario oldUsuarioOfFuncionario = funcionarioNew.getUsuario();
                if (oldUsuarioOfFuncionario != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Funcionario " + funcionarioNew + " already has an item of type Usuario whose funcionario column cannot be null. Please make another selection for the funcionario field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fornecedorNew != null) {
                fornecedorNew = em.getReference(fornecedorNew.getClass(), fornecedorNew.getCodfornec());
                usuario.setFornecedor(fornecedorNew);
            }
            if (funcionarioNew != null) {
                funcionarioNew = em.getReference(funcionarioNew.getClass(), funcionarioNew.getCodfunc());
                usuario.setFuncionario(funcionarioNew);
            }
            usuario = em.merge(usuario);
            if (fornecedorOld != null && !fornecedorOld.equals(fornecedorNew)) {
                fornecedorOld.setUsuario(null);
                fornecedorOld = em.merge(fornecedorOld);
            }
            if (fornecedorNew != null && !fornecedorNew.equals(fornecedorOld)) {
                fornecedorNew.setUsuario(usuario);
                fornecedorNew = em.merge(fornecedorNew);
            }
            if (funcionarioOld != null && !funcionarioOld.equals(funcionarioNew)) {
                funcionarioOld.setUsuario(null);
                funcionarioOld = em.merge(funcionarioOld);
            }
            if (funcionarioNew != null && !funcionarioNew.equals(funcionarioOld)) {
                funcionarioNew.setUsuario(usuario);
                funcionarioNew = em.merge(funcionarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getCodigo();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Fornecedor fornecedor = usuario.getFornecedor();
            if (fornecedor != null) {
                fornecedor.setUsuario(null);
                fornecedor = em.merge(fornecedor);
            }
            Funcionario funcionario = usuario.getFuncionario();
            if (funcionario != null) {
                funcionario.setUsuario(null);
                funcionario = em.merge(funcionario);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
