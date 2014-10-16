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
import model.LancamentoSaida;
import model.LancamentoRecforn;
import model.Caixa;
import model.LancamentoPagfunc;
import model.LancamentoEntrada;
import model.SituacaoLancamento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Lancamento;

/**
 *
 * @author Luis
 */
public class LancamentoJpaController implements Serializable {

    public LancamentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lancamento lancamento) throws PreexistingEntityException, Exception {
        if (lancamento.getSituacaoLancamentoList() == null) {
            lancamento.setSituacaoLancamentoList(new ArrayList<SituacaoLancamento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LancamentoSaida lancamentoSaida = lancamento.getLancamentoSaida();
            if (lancamentoSaida != null) {
                lancamentoSaida = em.getReference(lancamentoSaida.getClass(), lancamentoSaida.getCodlanc());
                lancamento.setLancamentoSaida(lancamentoSaida);
            }
            LancamentoRecforn lancamentoRecforn = lancamento.getLancamentoRecforn();
            if (lancamentoRecforn != null) {
                lancamentoRecforn = em.getReference(lancamentoRecforn.getClass(), lancamentoRecforn.getCodlanc());
                lancamento.setLancamentoRecforn(lancamentoRecforn);
            }
            Caixa codcaixa = lancamento.getCodcaixa();
            if (codcaixa != null) {
                codcaixa = em.getReference(codcaixa.getClass(), codcaixa.getCodcaixa());
                lancamento.setCodcaixa(codcaixa);
            }
            LancamentoPagfunc lancamentoPagfunc = lancamento.getLancamentoPagfunc();
            if (lancamentoPagfunc != null) {
                lancamentoPagfunc = em.getReference(lancamentoPagfunc.getClass(), lancamentoPagfunc.getCodlanc());
                lancamento.setLancamentoPagfunc(lancamentoPagfunc);
            }
            LancamentoEntrada lancamentoEntrada = lancamento.getLancamentoEntrada();
            if (lancamentoEntrada != null) {
                lancamentoEntrada = em.getReference(lancamentoEntrada.getClass(), lancamentoEntrada.getCodlanc());
                lancamento.setLancamentoEntrada(lancamentoEntrada);
            }
            List<SituacaoLancamento> attachedSituacaoLancamentoList = new ArrayList<SituacaoLancamento>();
            for (SituacaoLancamento situacaoLancamentoListSituacaoLancamentoToAttach : lancamento.getSituacaoLancamentoList()) {
                situacaoLancamentoListSituacaoLancamentoToAttach = em.getReference(situacaoLancamentoListSituacaoLancamentoToAttach.getClass(), situacaoLancamentoListSituacaoLancamentoToAttach.getSituacaoLancamentoPK());
                attachedSituacaoLancamentoList.add(situacaoLancamentoListSituacaoLancamentoToAttach);
            }
            lancamento.setSituacaoLancamentoList(attachedSituacaoLancamentoList);
            em.persist(lancamento);
            if (lancamentoSaida != null) {
                Lancamento oldLancamentoOfLancamentoSaida = lancamentoSaida.getLancamento();
                if (oldLancamentoOfLancamentoSaida != null) {
                    oldLancamentoOfLancamentoSaida.setLancamentoSaida(null);
                    oldLancamentoOfLancamentoSaida = em.merge(oldLancamentoOfLancamentoSaida);
                }
                lancamentoSaida.setLancamento(lancamento);
                lancamentoSaida = em.merge(lancamentoSaida);
            }
            if (lancamentoRecforn != null) {
                Lancamento oldLancamentoOfLancamentoRecforn = lancamentoRecforn.getLancamento();
                if (oldLancamentoOfLancamentoRecforn != null) {
                    oldLancamentoOfLancamentoRecforn.setLancamentoRecforn(null);
                    oldLancamentoOfLancamentoRecforn = em.merge(oldLancamentoOfLancamentoRecforn);
                }
                lancamentoRecforn.setLancamento(lancamento);
                lancamentoRecforn = em.merge(lancamentoRecforn);
            }
            if (codcaixa != null) {
                codcaixa.getLancamentoList().add(lancamento);
                codcaixa = em.merge(codcaixa);
            }
            if (lancamentoPagfunc != null) {
                Lancamento oldLancamentoOfLancamentoPagfunc = lancamentoPagfunc.getLancamento();
                if (oldLancamentoOfLancamentoPagfunc != null) {
                    oldLancamentoOfLancamentoPagfunc.setLancamentoPagfunc(null);
                    oldLancamentoOfLancamentoPagfunc = em.merge(oldLancamentoOfLancamentoPagfunc);
                }
                lancamentoPagfunc.setLancamento(lancamento);
                lancamentoPagfunc = em.merge(lancamentoPagfunc);
            }
            if (lancamentoEntrada != null) {
                Lancamento oldLancamentoOfLancamentoEntrada = lancamentoEntrada.getLancamento();
                if (oldLancamentoOfLancamentoEntrada != null) {
                    oldLancamentoOfLancamentoEntrada.setLancamentoEntrada(null);
                    oldLancamentoOfLancamentoEntrada = em.merge(oldLancamentoOfLancamentoEntrada);
                }
                lancamentoEntrada.setLancamento(lancamento);
                lancamentoEntrada = em.merge(lancamentoEntrada);
            }
            for (SituacaoLancamento situacaoLancamentoListSituacaoLancamento : lancamento.getSituacaoLancamentoList()) {
                Lancamento oldLancamentoOfSituacaoLancamentoListSituacaoLancamento = situacaoLancamentoListSituacaoLancamento.getLancamento();
                situacaoLancamentoListSituacaoLancamento.setLancamento(lancamento);
                situacaoLancamentoListSituacaoLancamento = em.merge(situacaoLancamentoListSituacaoLancamento);
                if (oldLancamentoOfSituacaoLancamentoListSituacaoLancamento != null) {
                    oldLancamentoOfSituacaoLancamentoListSituacaoLancamento.getSituacaoLancamentoList().remove(situacaoLancamentoListSituacaoLancamento);
                    oldLancamentoOfSituacaoLancamentoListSituacaoLancamento = em.merge(oldLancamentoOfSituacaoLancamentoListSituacaoLancamento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLancamento(lancamento.getCodlanc()) != null) {
                throw new PreexistingEntityException("Lancamento " + lancamento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lancamento lancamento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lancamento persistentLancamento = em.find(Lancamento.class, lancamento.getCodlanc());
            LancamentoSaida lancamentoSaidaOld = persistentLancamento.getLancamentoSaida();
            LancamentoSaida lancamentoSaidaNew = lancamento.getLancamentoSaida();
            LancamentoRecforn lancamentoRecfornOld = persistentLancamento.getLancamentoRecforn();
            LancamentoRecforn lancamentoRecfornNew = lancamento.getLancamentoRecforn();
            Caixa codcaixaOld = persistentLancamento.getCodcaixa();
            Caixa codcaixaNew = lancamento.getCodcaixa();
            LancamentoPagfunc lancamentoPagfuncOld = persistentLancamento.getLancamentoPagfunc();
            LancamentoPagfunc lancamentoPagfuncNew = lancamento.getLancamentoPagfunc();
            LancamentoEntrada lancamentoEntradaOld = persistentLancamento.getLancamentoEntrada();
            LancamentoEntrada lancamentoEntradaNew = lancamento.getLancamentoEntrada();
            List<SituacaoLancamento> situacaoLancamentoListOld = persistentLancamento.getSituacaoLancamentoList();
            List<SituacaoLancamento> situacaoLancamentoListNew = lancamento.getSituacaoLancamentoList();
            List<String> illegalOrphanMessages = null;
            if (lancamentoSaidaOld != null && !lancamentoSaidaOld.equals(lancamentoSaidaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain LancamentoSaida " + lancamentoSaidaOld + " since its lancamento field is not nullable.");
            }
            if (lancamentoRecfornOld != null && !lancamentoRecfornOld.equals(lancamentoRecfornNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain LancamentoRecforn " + lancamentoRecfornOld + " since its lancamento field is not nullable.");
            }
            if (lancamentoPagfuncOld != null && !lancamentoPagfuncOld.equals(lancamentoPagfuncNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain LancamentoPagfunc " + lancamentoPagfuncOld + " since its lancamento field is not nullable.");
            }
            if (lancamentoEntradaOld != null && !lancamentoEntradaOld.equals(lancamentoEntradaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain LancamentoEntrada " + lancamentoEntradaOld + " since its lancamento field is not nullable.");
            }
            for (SituacaoLancamento situacaoLancamentoListOldSituacaoLancamento : situacaoLancamentoListOld) {
                if (!situacaoLancamentoListNew.contains(situacaoLancamentoListOldSituacaoLancamento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SituacaoLancamento " + situacaoLancamentoListOldSituacaoLancamento + " since its lancamento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (lancamentoSaidaNew != null) {
                lancamentoSaidaNew = em.getReference(lancamentoSaidaNew.getClass(), lancamentoSaidaNew.getCodlanc());
                lancamento.setLancamentoSaida(lancamentoSaidaNew);
            }
            if (lancamentoRecfornNew != null) {
                lancamentoRecfornNew = em.getReference(lancamentoRecfornNew.getClass(), lancamentoRecfornNew.getCodlanc());
                lancamento.setLancamentoRecforn(lancamentoRecfornNew);
            }
            if (codcaixaNew != null) {
                codcaixaNew = em.getReference(codcaixaNew.getClass(), codcaixaNew.getCodcaixa());
                lancamento.setCodcaixa(codcaixaNew);
            }
            if (lancamentoPagfuncNew != null) {
                lancamentoPagfuncNew = em.getReference(lancamentoPagfuncNew.getClass(), lancamentoPagfuncNew.getCodlanc());
                lancamento.setLancamentoPagfunc(lancamentoPagfuncNew);
            }
            if (lancamentoEntradaNew != null) {
                lancamentoEntradaNew = em.getReference(lancamentoEntradaNew.getClass(), lancamentoEntradaNew.getCodlanc());
                lancamento.setLancamentoEntrada(lancamentoEntradaNew);
            }
            List<SituacaoLancamento> attachedSituacaoLancamentoListNew = new ArrayList<SituacaoLancamento>();
            for (SituacaoLancamento situacaoLancamentoListNewSituacaoLancamentoToAttach : situacaoLancamentoListNew) {
                situacaoLancamentoListNewSituacaoLancamentoToAttach = em.getReference(situacaoLancamentoListNewSituacaoLancamentoToAttach.getClass(), situacaoLancamentoListNewSituacaoLancamentoToAttach.getSituacaoLancamentoPK());
                attachedSituacaoLancamentoListNew.add(situacaoLancamentoListNewSituacaoLancamentoToAttach);
            }
            situacaoLancamentoListNew = attachedSituacaoLancamentoListNew;
            lancamento.setSituacaoLancamentoList(situacaoLancamentoListNew);
            lancamento = em.merge(lancamento);
            if (lancamentoSaidaNew != null && !lancamentoSaidaNew.equals(lancamentoSaidaOld)) {
                Lancamento oldLancamentoOfLancamentoSaida = lancamentoSaidaNew.getLancamento();
                if (oldLancamentoOfLancamentoSaida != null) {
                    oldLancamentoOfLancamentoSaida.setLancamentoSaida(null);
                    oldLancamentoOfLancamentoSaida = em.merge(oldLancamentoOfLancamentoSaida);
                }
                lancamentoSaidaNew.setLancamento(lancamento);
                lancamentoSaidaNew = em.merge(lancamentoSaidaNew);
            }
            if (lancamentoRecfornNew != null && !lancamentoRecfornNew.equals(lancamentoRecfornOld)) {
                Lancamento oldLancamentoOfLancamentoRecforn = lancamentoRecfornNew.getLancamento();
                if (oldLancamentoOfLancamentoRecforn != null) {
                    oldLancamentoOfLancamentoRecforn.setLancamentoRecforn(null);
                    oldLancamentoOfLancamentoRecforn = em.merge(oldLancamentoOfLancamentoRecforn);
                }
                lancamentoRecfornNew.setLancamento(lancamento);
                lancamentoRecfornNew = em.merge(lancamentoRecfornNew);
            }
            if (codcaixaOld != null && !codcaixaOld.equals(codcaixaNew)) {
                codcaixaOld.getLancamentoList().remove(lancamento);
                codcaixaOld = em.merge(codcaixaOld);
            }
            if (codcaixaNew != null && !codcaixaNew.equals(codcaixaOld)) {
                codcaixaNew.getLancamentoList().add(lancamento);
                codcaixaNew = em.merge(codcaixaNew);
            }
            if (lancamentoPagfuncNew != null && !lancamentoPagfuncNew.equals(lancamentoPagfuncOld)) {
                Lancamento oldLancamentoOfLancamentoPagfunc = lancamentoPagfuncNew.getLancamento();
                if (oldLancamentoOfLancamentoPagfunc != null) {
                    oldLancamentoOfLancamentoPagfunc.setLancamentoPagfunc(null);
                    oldLancamentoOfLancamentoPagfunc = em.merge(oldLancamentoOfLancamentoPagfunc);
                }
                lancamentoPagfuncNew.setLancamento(lancamento);
                lancamentoPagfuncNew = em.merge(lancamentoPagfuncNew);
            }
            if (lancamentoEntradaNew != null && !lancamentoEntradaNew.equals(lancamentoEntradaOld)) {
                Lancamento oldLancamentoOfLancamentoEntrada = lancamentoEntradaNew.getLancamento();
                if (oldLancamentoOfLancamentoEntrada != null) {
                    oldLancamentoOfLancamentoEntrada.setLancamentoEntrada(null);
                    oldLancamentoOfLancamentoEntrada = em.merge(oldLancamentoOfLancamentoEntrada);
                }
                lancamentoEntradaNew.setLancamento(lancamento);
                lancamentoEntradaNew = em.merge(lancamentoEntradaNew);
            }
            for (SituacaoLancamento situacaoLancamentoListNewSituacaoLancamento : situacaoLancamentoListNew) {
                if (!situacaoLancamentoListOld.contains(situacaoLancamentoListNewSituacaoLancamento)) {
                    Lancamento oldLancamentoOfSituacaoLancamentoListNewSituacaoLancamento = situacaoLancamentoListNewSituacaoLancamento.getLancamento();
                    situacaoLancamentoListNewSituacaoLancamento.setLancamento(lancamento);
                    situacaoLancamentoListNewSituacaoLancamento = em.merge(situacaoLancamentoListNewSituacaoLancamento);
                    if (oldLancamentoOfSituacaoLancamentoListNewSituacaoLancamento != null && !oldLancamentoOfSituacaoLancamentoListNewSituacaoLancamento.equals(lancamento)) {
                        oldLancamentoOfSituacaoLancamentoListNewSituacaoLancamento.getSituacaoLancamentoList().remove(situacaoLancamentoListNewSituacaoLancamento);
                        oldLancamentoOfSituacaoLancamentoListNewSituacaoLancamento = em.merge(oldLancamentoOfSituacaoLancamentoListNewSituacaoLancamento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lancamento.getCodlanc();
                if (findLancamento(id) == null) {
                    throw new NonexistentEntityException("The lancamento with id " + id + " no longer exists.");
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
            Lancamento lancamento;
            try {
                lancamento = em.getReference(Lancamento.class, id);
                lancamento.getCodlanc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lancamento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            LancamentoSaida lancamentoSaidaOrphanCheck = lancamento.getLancamentoSaida();
            if (lancamentoSaidaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Lancamento (" + lancamento + ") cannot be destroyed since the LancamentoSaida " + lancamentoSaidaOrphanCheck + " in its lancamentoSaida field has a non-nullable lancamento field.");
            }
            LancamentoRecforn lancamentoRecfornOrphanCheck = lancamento.getLancamentoRecforn();
            if (lancamentoRecfornOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Lancamento (" + lancamento + ") cannot be destroyed since the LancamentoRecforn " + lancamentoRecfornOrphanCheck + " in its lancamentoRecforn field has a non-nullable lancamento field.");
            }
            LancamentoPagfunc lancamentoPagfuncOrphanCheck = lancamento.getLancamentoPagfunc();
            if (lancamentoPagfuncOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Lancamento (" + lancamento + ") cannot be destroyed since the LancamentoPagfunc " + lancamentoPagfuncOrphanCheck + " in its lancamentoPagfunc field has a non-nullable lancamento field.");
            }
            LancamentoEntrada lancamentoEntradaOrphanCheck = lancamento.getLancamentoEntrada();
            if (lancamentoEntradaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Lancamento (" + lancamento + ") cannot be destroyed since the LancamentoEntrada " + lancamentoEntradaOrphanCheck + " in its lancamentoEntrada field has a non-nullable lancamento field.");
            }
            List<SituacaoLancamento> situacaoLancamentoListOrphanCheck = lancamento.getSituacaoLancamentoList();
            for (SituacaoLancamento situacaoLancamentoListOrphanCheckSituacaoLancamento : situacaoLancamentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Lancamento (" + lancamento + ") cannot be destroyed since the SituacaoLancamento " + situacaoLancamentoListOrphanCheckSituacaoLancamento + " in its situacaoLancamentoList field has a non-nullable lancamento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Caixa codcaixa = lancamento.getCodcaixa();
            if (codcaixa != null) {
                codcaixa.getLancamentoList().remove(lancamento);
                codcaixa = em.merge(codcaixa);
            }
            em.remove(lancamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lancamento> findLancamentoEntities() {
        return findLancamentoEntities(true, -1, -1);
    }

    public List<Lancamento> findLancamentoEntities(int maxResults, int firstResult) {
        return findLancamentoEntities(false, maxResults, firstResult);
    }

    private List<Lancamento> findLancamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lancamento.class));
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

    public Lancamento findLancamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lancamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getLancamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lancamento> rt = cq.from(Lancamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
//     public int getLastId() {
//        List<MaterialDoProduto> list;
//        MaterialDoProduto obj;
//        MaterialDoProdutoJpaController c = new MaterialDoProdutoJpaController(ipsum2.Ipsum2.getFactory());
//
//        list = c.getEntityManager().createNamedQuery("MaterialDoProduto.findAll").getResultList();
//        if (!list.isEmpty()) {
//            obj = list.get(list.size() - 1);
//            if (obj.get() > 0) {
//                return obj.getCodprod() + 1;
//            }
//        }
//        return 1;
//    }

}
