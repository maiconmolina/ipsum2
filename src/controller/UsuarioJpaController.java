/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import enuns.Permissoes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Funcao;
import model.Funcionario;
import model.PermissoesFuncao;
import model.Usuario;

/**
 *
 * @author Luis
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public UsuarioJpaController() {
        this.emf = ipsum2.Ipsum2.getFactory();
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getLogin()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getLogin();
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

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getLogin();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
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

    public Usuario findUsuario(String id) {
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

    public void loginDesktop(String login, String senha) throws Exception {
        List<Usuario> usuarios = this.getEntityManager()
                .createNamedQuery("Usuario.findAll")
                .getResultList();
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                if (u.getSenha().equals(senha)) {
                    if (u.getTipo() == Funcionario.class) {
                        Usuario.setUsuarioLogado(u);
                        return;
                    }
                } else {
                    throw new Exception("Senha incorreta.");
                }
            }
        }
        throw new Exception("Login não cadastrado.");
    }

    public List<Permissoes> getPermissoesUsuario() {
        List<Permissoes> retorno = new ArrayList<>();

        List<Funcionario> func = new FuncionarioJpaController()
                .getEntityManager()
                .createNamedQuery("Funcionario.findByCodfunc")
                .setParameter("codfunc", Usuario.getUsuarioLogado().getCodigo())
                .getResultList();

        List<Funcao> funcao = new FuncaoJpaController()
                .getEntityManager()
                .createNamedQuery("Funcao.findByCodfuncao")
                .setParameter("codfuncao", func.get(0).getCodfuncao().getCodfuncao())
                .getResultList();

        List<PermissoesFuncao> permFuncs = this.getEntityManager()
                .createNamedQuery("PermissoesFuncao.findByCodfuncao")
                .setParameter("codfuncao", funcao.get(0).getCodfuncao())
                .getResultList();

        for (PermissoesFuncao p : permFuncs) {
            retorno.add(Permissoes.getByValue(p.getPermissao()));
        }

        return retorno;
    }

    public Integer getCodFuncaoUsuarioLogado() {
        List<Funcionario> func = new FuncionarioJpaController()
                .getEntityManager()
                .createNamedQuery("Funcionario.findByCodfunc")
                .setParameter("codfunc", Usuario.getUsuarioLogado().getCodigo())
                .getResultList();

        List<Funcao> funcao = new FuncaoJpaController()
                .getEntityManager()
                .createNamedQuery("Funcao.findByCodfuncao")
                .setParameter("codfuncao", func.get(0).getCodfuncao().getCodfuncao())
                .getResultList();

        return funcao.get(0).getCodfuncao();
    }

}
