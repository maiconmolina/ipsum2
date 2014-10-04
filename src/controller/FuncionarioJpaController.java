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
import model.Funcao;
import model.Habilidade;
import model.Usuario;
import model.FuncionarioDoLote;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Funcionario;
import model.ProducaoDiaria;
import model.LancamentoPagfunc;

/**
 *
 * @author Maicon
 */
public class FuncionarioJpaController implements Serializable {

    public FuncionarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Funcionario funcionario) throws PreexistingEntityException, Exception {
        if (funcionario.getFuncionarioDoLoteList() == null) {
            funcionario.setFuncionarioDoLoteList(new ArrayList<FuncionarioDoLote>());
        }
        if (funcionario.getProducaoDiariaList() == null) {
            funcionario.setProducaoDiariaList(new ArrayList<ProducaoDiaria>());
        }
        if (funcionario.getLancamentoPagfuncList() == null) {
            funcionario.setLancamentoPagfuncList(new ArrayList<LancamentoPagfunc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcao codfuncao = funcionario.getCodfuncao();
            if (codfuncao != null) {
                codfuncao = em.getReference(codfuncao.getClass(), codfuncao.getCodfuncao());
                funcionario.setCodfuncao(codfuncao);
            }
            Habilidade codhab = funcionario.getCodhab();
            if (codhab != null) {
                codhab = em.getReference(codhab.getClass(), codhab.getCodhab());
                funcionario.setCodhab(codhab);
            }
            Usuario usuario = funcionario.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getCodigo());
                funcionario.setUsuario(usuario);
            }
            List<FuncionarioDoLote> attachedFuncionarioDoLoteList = new ArrayList<FuncionarioDoLote>();
            for (FuncionarioDoLote funcionarioDoLoteListFuncionarioDoLoteToAttach : funcionario.getFuncionarioDoLoteList()) {
                funcionarioDoLoteListFuncionarioDoLoteToAttach = em.getReference(funcionarioDoLoteListFuncionarioDoLoteToAttach.getClass(), funcionarioDoLoteListFuncionarioDoLoteToAttach.getFuncionarioDoLotePK());
                attachedFuncionarioDoLoteList.add(funcionarioDoLoteListFuncionarioDoLoteToAttach);
            }
            funcionario.setFuncionarioDoLoteList(attachedFuncionarioDoLoteList);
            List<ProducaoDiaria> attachedProducaoDiariaList = new ArrayList<ProducaoDiaria>();
            for (ProducaoDiaria producaoDiariaListProducaoDiariaToAttach : funcionario.getProducaoDiariaList()) {
                producaoDiariaListProducaoDiariaToAttach = em.getReference(producaoDiariaListProducaoDiariaToAttach.getClass(), producaoDiariaListProducaoDiariaToAttach.getProducaoDiariaPK());
                attachedProducaoDiariaList.add(producaoDiariaListProducaoDiariaToAttach);
            }
            funcionario.setProducaoDiariaList(attachedProducaoDiariaList);
            List<LancamentoPagfunc> attachedLancamentoPagfuncList = new ArrayList<LancamentoPagfunc>();
            for (LancamentoPagfunc lancamentoPagfuncListLancamentoPagfuncToAttach : funcionario.getLancamentoPagfuncList()) {
                lancamentoPagfuncListLancamentoPagfuncToAttach = em.getReference(lancamentoPagfuncListLancamentoPagfuncToAttach.getClass(), lancamentoPagfuncListLancamentoPagfuncToAttach.getCodlanc());
                attachedLancamentoPagfuncList.add(lancamentoPagfuncListLancamentoPagfuncToAttach);
            }
            funcionario.setLancamentoPagfuncList(attachedLancamentoPagfuncList);
            em.persist(funcionario);
            if (codfuncao != null) {
                codfuncao.getFuncionarioList().add(funcionario);
                codfuncao = em.merge(codfuncao);
            }
            if (codhab != null) {
                codhab.getFuncionarioList().add(funcionario);
                codhab = em.merge(codhab);
            }
            if (usuario != null) {
                Funcionario oldFuncionarioOfUsuario = usuario.getFuncionario();
                if (oldFuncionarioOfUsuario != null) {
                    oldFuncionarioOfUsuario.setUsuario(null);
                    oldFuncionarioOfUsuario = em.merge(oldFuncionarioOfUsuario);
                }
                usuario.setFuncionario(funcionario);
                usuario = em.merge(usuario);
            }
            for (FuncionarioDoLote funcionarioDoLoteListFuncionarioDoLote : funcionario.getFuncionarioDoLoteList()) {
                Funcionario oldFuncionarioOfFuncionarioDoLoteListFuncionarioDoLote = funcionarioDoLoteListFuncionarioDoLote.getFuncionario();
                funcionarioDoLoteListFuncionarioDoLote.setFuncionario(funcionario);
                funcionarioDoLoteListFuncionarioDoLote = em.merge(funcionarioDoLoteListFuncionarioDoLote);
                if (oldFuncionarioOfFuncionarioDoLoteListFuncionarioDoLote != null) {
                    oldFuncionarioOfFuncionarioDoLoteListFuncionarioDoLote.getFuncionarioDoLoteList().remove(funcionarioDoLoteListFuncionarioDoLote);
                    oldFuncionarioOfFuncionarioDoLoteListFuncionarioDoLote = em.merge(oldFuncionarioOfFuncionarioDoLoteListFuncionarioDoLote);
                }
            }
            for (ProducaoDiaria producaoDiariaListProducaoDiaria : funcionario.getProducaoDiariaList()) {
                Funcionario oldFuncionarioOfProducaoDiariaListProducaoDiaria = producaoDiariaListProducaoDiaria.getFuncionario();
                producaoDiariaListProducaoDiaria.setFuncionario(funcionario);
                producaoDiariaListProducaoDiaria = em.merge(producaoDiariaListProducaoDiaria);
                if (oldFuncionarioOfProducaoDiariaListProducaoDiaria != null) {
                    oldFuncionarioOfProducaoDiariaListProducaoDiaria.getProducaoDiariaList().remove(producaoDiariaListProducaoDiaria);
                    oldFuncionarioOfProducaoDiariaListProducaoDiaria = em.merge(oldFuncionarioOfProducaoDiariaListProducaoDiaria);
                }
            }
            for (LancamentoPagfunc lancamentoPagfuncListLancamentoPagfunc : funcionario.getLancamentoPagfuncList()) {
                Funcionario oldCodfuncOfLancamentoPagfuncListLancamentoPagfunc = lancamentoPagfuncListLancamentoPagfunc.getCodfunc();
                lancamentoPagfuncListLancamentoPagfunc.setCodfunc(funcionario);
                lancamentoPagfuncListLancamentoPagfunc = em.merge(lancamentoPagfuncListLancamentoPagfunc);
                if (oldCodfuncOfLancamentoPagfuncListLancamentoPagfunc != null) {
                    oldCodfuncOfLancamentoPagfuncListLancamentoPagfunc.getLancamentoPagfuncList().remove(lancamentoPagfuncListLancamentoPagfunc);
                    oldCodfuncOfLancamentoPagfuncListLancamentoPagfunc = em.merge(oldCodfuncOfLancamentoPagfuncListLancamentoPagfunc);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFuncionario(funcionario.getCodfunc()) != null) {
                throw new PreexistingEntityException("Funcionario " + funcionario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcionario funcionario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario persistentFuncionario = em.find(Funcionario.class, funcionario.getCodfunc());
            Funcao codfuncaoOld = persistentFuncionario.getCodfuncao();
            Funcao codfuncaoNew = funcionario.getCodfuncao();
            Habilidade codhabOld = persistentFuncionario.getCodhab();
            Habilidade codhabNew = funcionario.getCodhab();
            Usuario usuarioOld = persistentFuncionario.getUsuario();
            Usuario usuarioNew = funcionario.getUsuario();
            List<FuncionarioDoLote> funcionarioDoLoteListOld = persistentFuncionario.getFuncionarioDoLoteList();
            List<FuncionarioDoLote> funcionarioDoLoteListNew = funcionario.getFuncionarioDoLoteList();
            List<ProducaoDiaria> producaoDiariaListOld = persistentFuncionario.getProducaoDiariaList();
            List<ProducaoDiaria> producaoDiariaListNew = funcionario.getProducaoDiariaList();
            List<LancamentoPagfunc> lancamentoPagfuncListOld = persistentFuncionario.getLancamentoPagfuncList();
            List<LancamentoPagfunc> lancamentoPagfuncListNew = funcionario.getLancamentoPagfuncList();
            List<String> illegalOrphanMessages = null;
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Usuario " + usuarioOld + " since its funcionario field is not nullable.");
            }
            for (FuncionarioDoLote funcionarioDoLoteListOldFuncionarioDoLote : funcionarioDoLoteListOld) {
                if (!funcionarioDoLoteListNew.contains(funcionarioDoLoteListOldFuncionarioDoLote)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FuncionarioDoLote " + funcionarioDoLoteListOldFuncionarioDoLote + " since its funcionario field is not nullable.");
                }
            }
            for (ProducaoDiaria producaoDiariaListOldProducaoDiaria : producaoDiariaListOld) {
                if (!producaoDiariaListNew.contains(producaoDiariaListOldProducaoDiaria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProducaoDiaria " + producaoDiariaListOldProducaoDiaria + " since its funcionario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codfuncaoNew != null) {
                codfuncaoNew = em.getReference(codfuncaoNew.getClass(), codfuncaoNew.getCodfuncao());
                funcionario.setCodfuncao(codfuncaoNew);
            }
            if (codhabNew != null) {
                codhabNew = em.getReference(codhabNew.getClass(), codhabNew.getCodhab());
                funcionario.setCodhab(codhabNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getCodigo());
                funcionario.setUsuario(usuarioNew);
            }
            List<FuncionarioDoLote> attachedFuncionarioDoLoteListNew = new ArrayList<FuncionarioDoLote>();
            for (FuncionarioDoLote funcionarioDoLoteListNewFuncionarioDoLoteToAttach : funcionarioDoLoteListNew) {
                funcionarioDoLoteListNewFuncionarioDoLoteToAttach = em.getReference(funcionarioDoLoteListNewFuncionarioDoLoteToAttach.getClass(), funcionarioDoLoteListNewFuncionarioDoLoteToAttach.getFuncionarioDoLotePK());
                attachedFuncionarioDoLoteListNew.add(funcionarioDoLoteListNewFuncionarioDoLoteToAttach);
            }
            funcionarioDoLoteListNew = attachedFuncionarioDoLoteListNew;
            funcionario.setFuncionarioDoLoteList(funcionarioDoLoteListNew);
            List<ProducaoDiaria> attachedProducaoDiariaListNew = new ArrayList<ProducaoDiaria>();
            for (ProducaoDiaria producaoDiariaListNewProducaoDiariaToAttach : producaoDiariaListNew) {
                producaoDiariaListNewProducaoDiariaToAttach = em.getReference(producaoDiariaListNewProducaoDiariaToAttach.getClass(), producaoDiariaListNewProducaoDiariaToAttach.getProducaoDiariaPK());
                attachedProducaoDiariaListNew.add(producaoDiariaListNewProducaoDiariaToAttach);
            }
            producaoDiariaListNew = attachedProducaoDiariaListNew;
            funcionario.setProducaoDiariaList(producaoDiariaListNew);
            List<LancamentoPagfunc> attachedLancamentoPagfuncListNew = new ArrayList<LancamentoPagfunc>();
            for (LancamentoPagfunc lancamentoPagfuncListNewLancamentoPagfuncToAttach : lancamentoPagfuncListNew) {
                lancamentoPagfuncListNewLancamentoPagfuncToAttach = em.getReference(lancamentoPagfuncListNewLancamentoPagfuncToAttach.getClass(), lancamentoPagfuncListNewLancamentoPagfuncToAttach.getCodlanc());
                attachedLancamentoPagfuncListNew.add(lancamentoPagfuncListNewLancamentoPagfuncToAttach);
            }
            lancamentoPagfuncListNew = attachedLancamentoPagfuncListNew;
            funcionario.setLancamentoPagfuncList(lancamentoPagfuncListNew);
            funcionario = em.merge(funcionario);
            if (codfuncaoOld != null && !codfuncaoOld.equals(codfuncaoNew)) {
                codfuncaoOld.getFuncionarioList().remove(funcionario);
                codfuncaoOld = em.merge(codfuncaoOld);
            }
            if (codfuncaoNew != null && !codfuncaoNew.equals(codfuncaoOld)) {
                codfuncaoNew.getFuncionarioList().add(funcionario);
                codfuncaoNew = em.merge(codfuncaoNew);
            }
            if (codhabOld != null && !codhabOld.equals(codhabNew)) {
                codhabOld.getFuncionarioList().remove(funcionario);
                codhabOld = em.merge(codhabOld);
            }
            if (codhabNew != null && !codhabNew.equals(codhabOld)) {
                codhabNew.getFuncionarioList().add(funcionario);
                codhabNew = em.merge(codhabNew);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                Funcionario oldFuncionarioOfUsuario = usuarioNew.getFuncionario();
                if (oldFuncionarioOfUsuario != null) {
                    oldFuncionarioOfUsuario.setUsuario(null);
                    oldFuncionarioOfUsuario = em.merge(oldFuncionarioOfUsuario);
                }
                usuarioNew.setFuncionario(funcionario);
                usuarioNew = em.merge(usuarioNew);
            }
            for (FuncionarioDoLote funcionarioDoLoteListNewFuncionarioDoLote : funcionarioDoLoteListNew) {
                if (!funcionarioDoLoteListOld.contains(funcionarioDoLoteListNewFuncionarioDoLote)) {
                    Funcionario oldFuncionarioOfFuncionarioDoLoteListNewFuncionarioDoLote = funcionarioDoLoteListNewFuncionarioDoLote.getFuncionario();
                    funcionarioDoLoteListNewFuncionarioDoLote.setFuncionario(funcionario);
                    funcionarioDoLoteListNewFuncionarioDoLote = em.merge(funcionarioDoLoteListNewFuncionarioDoLote);
                    if (oldFuncionarioOfFuncionarioDoLoteListNewFuncionarioDoLote != null && !oldFuncionarioOfFuncionarioDoLoteListNewFuncionarioDoLote.equals(funcionario)) {
                        oldFuncionarioOfFuncionarioDoLoteListNewFuncionarioDoLote.getFuncionarioDoLoteList().remove(funcionarioDoLoteListNewFuncionarioDoLote);
                        oldFuncionarioOfFuncionarioDoLoteListNewFuncionarioDoLote = em.merge(oldFuncionarioOfFuncionarioDoLoteListNewFuncionarioDoLote);
                    }
                }
            }
            for (ProducaoDiaria producaoDiariaListNewProducaoDiaria : producaoDiariaListNew) {
                if (!producaoDiariaListOld.contains(producaoDiariaListNewProducaoDiaria)) {
                    Funcionario oldFuncionarioOfProducaoDiariaListNewProducaoDiaria = producaoDiariaListNewProducaoDiaria.getFuncionario();
                    producaoDiariaListNewProducaoDiaria.setFuncionario(funcionario);
                    producaoDiariaListNewProducaoDiaria = em.merge(producaoDiariaListNewProducaoDiaria);
                    if (oldFuncionarioOfProducaoDiariaListNewProducaoDiaria != null && !oldFuncionarioOfProducaoDiariaListNewProducaoDiaria.equals(funcionario)) {
                        oldFuncionarioOfProducaoDiariaListNewProducaoDiaria.getProducaoDiariaList().remove(producaoDiariaListNewProducaoDiaria);
                        oldFuncionarioOfProducaoDiariaListNewProducaoDiaria = em.merge(oldFuncionarioOfProducaoDiariaListNewProducaoDiaria);
                    }
                }
            }
            for (LancamentoPagfunc lancamentoPagfuncListOldLancamentoPagfunc : lancamentoPagfuncListOld) {
                if (!lancamentoPagfuncListNew.contains(lancamentoPagfuncListOldLancamentoPagfunc)) {
                    lancamentoPagfuncListOldLancamentoPagfunc.setCodfunc(null);
                    lancamentoPagfuncListOldLancamentoPagfunc = em.merge(lancamentoPagfuncListOldLancamentoPagfunc);
                }
            }
            for (LancamentoPagfunc lancamentoPagfuncListNewLancamentoPagfunc : lancamentoPagfuncListNew) {
                if (!lancamentoPagfuncListOld.contains(lancamentoPagfuncListNewLancamentoPagfunc)) {
                    Funcionario oldCodfuncOfLancamentoPagfuncListNewLancamentoPagfunc = lancamentoPagfuncListNewLancamentoPagfunc.getCodfunc();
                    lancamentoPagfuncListNewLancamentoPagfunc.setCodfunc(funcionario);
                    lancamentoPagfuncListNewLancamentoPagfunc = em.merge(lancamentoPagfuncListNewLancamentoPagfunc);
                    if (oldCodfuncOfLancamentoPagfuncListNewLancamentoPagfunc != null && !oldCodfuncOfLancamentoPagfuncListNewLancamentoPagfunc.equals(funcionario)) {
                        oldCodfuncOfLancamentoPagfuncListNewLancamentoPagfunc.getLancamentoPagfuncList().remove(lancamentoPagfuncListNewLancamentoPagfunc);
                        oldCodfuncOfLancamentoPagfuncListNewLancamentoPagfunc = em.merge(oldCodfuncOfLancamentoPagfuncListNewLancamentoPagfunc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = funcionario.getCodfunc();
                if (findFuncionario(id) == null) {
                    throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.");
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
            Funcionario funcionario;
            try {
                funcionario = em.getReference(Funcionario.class, id);
                funcionario.getCodfunc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Usuario usuarioOrphanCheck = funcionario.getUsuario();
            if (usuarioOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Funcionario (" + funcionario + ") cannot be destroyed since the Usuario " + usuarioOrphanCheck + " in its usuario field has a non-nullable funcionario field.");
            }
            List<FuncionarioDoLote> funcionarioDoLoteListOrphanCheck = funcionario.getFuncionarioDoLoteList();
            for (FuncionarioDoLote funcionarioDoLoteListOrphanCheckFuncionarioDoLote : funcionarioDoLoteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Funcionario (" + funcionario + ") cannot be destroyed since the FuncionarioDoLote " + funcionarioDoLoteListOrphanCheckFuncionarioDoLote + " in its funcionarioDoLoteList field has a non-nullable funcionario field.");
            }
            List<ProducaoDiaria> producaoDiariaListOrphanCheck = funcionario.getProducaoDiariaList();
            for (ProducaoDiaria producaoDiariaListOrphanCheckProducaoDiaria : producaoDiariaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Funcionario (" + funcionario + ") cannot be destroyed since the ProducaoDiaria " + producaoDiariaListOrphanCheckProducaoDiaria + " in its producaoDiariaList field has a non-nullable funcionario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Funcao codfuncao = funcionario.getCodfuncao();
            if (codfuncao != null) {
                codfuncao.getFuncionarioList().remove(funcionario);
                codfuncao = em.merge(codfuncao);
            }
            Habilidade codhab = funcionario.getCodhab();
            if (codhab != null) {
                codhab.getFuncionarioList().remove(funcionario);
                codhab = em.merge(codhab);
            }
            List<LancamentoPagfunc> lancamentoPagfuncList = funcionario.getLancamentoPagfuncList();
            for (LancamentoPagfunc lancamentoPagfuncListLancamentoPagfunc : lancamentoPagfuncList) {
                lancamentoPagfuncListLancamentoPagfunc.setCodfunc(null);
                lancamentoPagfuncListLancamentoPagfunc = em.merge(lancamentoPagfuncListLancamentoPagfunc);
            }
            em.remove(funcionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionario> findFuncionarioEntities() {
        return findFuncionarioEntities(true, -1, -1);
    }

    public List<Funcionario> findFuncionarioEntities(int maxResults, int firstResult) {
        return findFuncionarioEntities(false, maxResults, firstResult);
    }

    private List<Funcionario> findFuncionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionario.class));
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

    public Funcionario findFuncionario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionario.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionario> rt = cq.from(Funcionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
