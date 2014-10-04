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
import model.SituacaoLote;
import model.ProdutoDoLote;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.PagamentoLote;
import model.FuncionarioDoLote;
import model.Lote;
import model.ProducaoDiaria;

/**
 *
 * @author Maicon
 */
public class LoteJpaController implements Serializable {

    public LoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lote lote) throws PreexistingEntityException, Exception {
        if (lote.getProdutoDoLoteList() == null) {
            lote.setProdutoDoLoteList(new ArrayList<ProdutoDoLote>());
        }
        if (lote.getPagamentoLoteList() == null) {
            lote.setPagamentoLoteList(new ArrayList<PagamentoLote>());
        }
        if (lote.getFuncionarioDoLoteList() == null) {
            lote.setFuncionarioDoLoteList(new ArrayList<FuncionarioDoLote>());
        }
        if (lote.getProducaoDiariaList() == null) {
            lote.setProducaoDiariaList(new ArrayList<ProducaoDiaria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor codfornec = lote.getCodfornec();
            if (codfornec != null) {
                codfornec = em.getReference(codfornec.getClass(), codfornec.getCodfornec());
                lote.setCodfornec(codfornec);
            }
            SituacaoLote sitlote = lote.getSitlote();
            if (sitlote != null) {
                sitlote = em.getReference(sitlote.getClass(), sitlote.getSitlote());
                lote.setSitlote(sitlote);
            }
            List<ProdutoDoLote> attachedProdutoDoLoteList = new ArrayList<ProdutoDoLote>();
            for (ProdutoDoLote produtoDoLoteListProdutoDoLoteToAttach : lote.getProdutoDoLoteList()) {
                produtoDoLoteListProdutoDoLoteToAttach = em.getReference(produtoDoLoteListProdutoDoLoteToAttach.getClass(), produtoDoLoteListProdutoDoLoteToAttach.getProdutoDoLotePK());
                attachedProdutoDoLoteList.add(produtoDoLoteListProdutoDoLoteToAttach);
            }
            lote.setProdutoDoLoteList(attachedProdutoDoLoteList);
            List<PagamentoLote> attachedPagamentoLoteList = new ArrayList<PagamentoLote>();
            for (PagamentoLote pagamentoLoteListPagamentoLoteToAttach : lote.getPagamentoLoteList()) {
                pagamentoLoteListPagamentoLoteToAttach = em.getReference(pagamentoLoteListPagamentoLoteToAttach.getClass(), pagamentoLoteListPagamentoLoteToAttach.getCodpaglote());
                attachedPagamentoLoteList.add(pagamentoLoteListPagamentoLoteToAttach);
            }
            lote.setPagamentoLoteList(attachedPagamentoLoteList);
            List<FuncionarioDoLote> attachedFuncionarioDoLoteList = new ArrayList<FuncionarioDoLote>();
            for (FuncionarioDoLote funcionarioDoLoteListFuncionarioDoLoteToAttach : lote.getFuncionarioDoLoteList()) {
                funcionarioDoLoteListFuncionarioDoLoteToAttach = em.getReference(funcionarioDoLoteListFuncionarioDoLoteToAttach.getClass(), funcionarioDoLoteListFuncionarioDoLoteToAttach.getFuncionarioDoLotePK());
                attachedFuncionarioDoLoteList.add(funcionarioDoLoteListFuncionarioDoLoteToAttach);
            }
            lote.setFuncionarioDoLoteList(attachedFuncionarioDoLoteList);
            List<ProducaoDiaria> attachedProducaoDiariaList = new ArrayList<ProducaoDiaria>();
            for (ProducaoDiaria producaoDiariaListProducaoDiariaToAttach : lote.getProducaoDiariaList()) {
                producaoDiariaListProducaoDiariaToAttach = em.getReference(producaoDiariaListProducaoDiariaToAttach.getClass(), producaoDiariaListProducaoDiariaToAttach.getProducaoDiariaPK());
                attachedProducaoDiariaList.add(producaoDiariaListProducaoDiariaToAttach);
            }
            lote.setProducaoDiariaList(attachedProducaoDiariaList);
            em.persist(lote);
            if (codfornec != null) {
                codfornec.getLoteList().add(lote);
                codfornec = em.merge(codfornec);
            }
            if (sitlote != null) {
                sitlote.getLoteList().add(lote);
                sitlote = em.merge(sitlote);
            }
            for (ProdutoDoLote produtoDoLoteListProdutoDoLote : lote.getProdutoDoLoteList()) {
                Lote oldLoteOfProdutoDoLoteListProdutoDoLote = produtoDoLoteListProdutoDoLote.getLote();
                produtoDoLoteListProdutoDoLote.setLote(lote);
                produtoDoLoteListProdutoDoLote = em.merge(produtoDoLoteListProdutoDoLote);
                if (oldLoteOfProdutoDoLoteListProdutoDoLote != null) {
                    oldLoteOfProdutoDoLoteListProdutoDoLote.getProdutoDoLoteList().remove(produtoDoLoteListProdutoDoLote);
                    oldLoteOfProdutoDoLoteListProdutoDoLote = em.merge(oldLoteOfProdutoDoLoteListProdutoDoLote);
                }
            }
            for (PagamentoLote pagamentoLoteListPagamentoLote : lote.getPagamentoLoteList()) {
                Lote oldCodloteOfPagamentoLoteListPagamentoLote = pagamentoLoteListPagamentoLote.getCodlote();
                pagamentoLoteListPagamentoLote.setCodlote(lote);
                pagamentoLoteListPagamentoLote = em.merge(pagamentoLoteListPagamentoLote);
                if (oldCodloteOfPagamentoLoteListPagamentoLote != null) {
                    oldCodloteOfPagamentoLoteListPagamentoLote.getPagamentoLoteList().remove(pagamentoLoteListPagamentoLote);
                    oldCodloteOfPagamentoLoteListPagamentoLote = em.merge(oldCodloteOfPagamentoLoteListPagamentoLote);
                }
            }
            for (FuncionarioDoLote funcionarioDoLoteListFuncionarioDoLote : lote.getFuncionarioDoLoteList()) {
                Lote oldLoteOfFuncionarioDoLoteListFuncionarioDoLote = funcionarioDoLoteListFuncionarioDoLote.getLote();
                funcionarioDoLoteListFuncionarioDoLote.setLote(lote);
                funcionarioDoLoteListFuncionarioDoLote = em.merge(funcionarioDoLoteListFuncionarioDoLote);
                if (oldLoteOfFuncionarioDoLoteListFuncionarioDoLote != null) {
                    oldLoteOfFuncionarioDoLoteListFuncionarioDoLote.getFuncionarioDoLoteList().remove(funcionarioDoLoteListFuncionarioDoLote);
                    oldLoteOfFuncionarioDoLoteListFuncionarioDoLote = em.merge(oldLoteOfFuncionarioDoLoteListFuncionarioDoLote);
                }
            }
            for (ProducaoDiaria producaoDiariaListProducaoDiaria : lote.getProducaoDiariaList()) {
                Lote oldLoteOfProducaoDiariaListProducaoDiaria = producaoDiariaListProducaoDiaria.getLote();
                producaoDiariaListProducaoDiaria.setLote(lote);
                producaoDiariaListProducaoDiaria = em.merge(producaoDiariaListProducaoDiaria);
                if (oldLoteOfProducaoDiariaListProducaoDiaria != null) {
                    oldLoteOfProducaoDiariaListProducaoDiaria.getProducaoDiariaList().remove(producaoDiariaListProducaoDiaria);
                    oldLoteOfProducaoDiariaListProducaoDiaria = em.merge(oldLoteOfProducaoDiariaListProducaoDiaria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLote(lote.getCodlote()) != null) {
                throw new PreexistingEntityException("Lote " + lote + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lote lote) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lote persistentLote = em.find(Lote.class, lote.getCodlote());
            Fornecedor codfornecOld = persistentLote.getCodfornec();
            Fornecedor codfornecNew = lote.getCodfornec();
            SituacaoLote sitloteOld = persistentLote.getSitlote();
            SituacaoLote sitloteNew = lote.getSitlote();
            List<ProdutoDoLote> produtoDoLoteListOld = persistentLote.getProdutoDoLoteList();
            List<ProdutoDoLote> produtoDoLoteListNew = lote.getProdutoDoLoteList();
            List<PagamentoLote> pagamentoLoteListOld = persistentLote.getPagamentoLoteList();
            List<PagamentoLote> pagamentoLoteListNew = lote.getPagamentoLoteList();
            List<FuncionarioDoLote> funcionarioDoLoteListOld = persistentLote.getFuncionarioDoLoteList();
            List<FuncionarioDoLote> funcionarioDoLoteListNew = lote.getFuncionarioDoLoteList();
            List<ProducaoDiaria> producaoDiariaListOld = persistentLote.getProducaoDiariaList();
            List<ProducaoDiaria> producaoDiariaListNew = lote.getProducaoDiariaList();
            List<String> illegalOrphanMessages = null;
            for (ProdutoDoLote produtoDoLoteListOldProdutoDoLote : produtoDoLoteListOld) {
                if (!produtoDoLoteListNew.contains(produtoDoLoteListOldProdutoDoLote)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProdutoDoLote " + produtoDoLoteListOldProdutoDoLote + " since its lote field is not nullable.");
                }
            }
            for (FuncionarioDoLote funcionarioDoLoteListOldFuncionarioDoLote : funcionarioDoLoteListOld) {
                if (!funcionarioDoLoteListNew.contains(funcionarioDoLoteListOldFuncionarioDoLote)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FuncionarioDoLote " + funcionarioDoLoteListOldFuncionarioDoLote + " since its lote field is not nullable.");
                }
            }
            for (ProducaoDiaria producaoDiariaListOldProducaoDiaria : producaoDiariaListOld) {
                if (!producaoDiariaListNew.contains(producaoDiariaListOldProducaoDiaria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProducaoDiaria " + producaoDiariaListOldProducaoDiaria + " since its lote field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codfornecNew != null) {
                codfornecNew = em.getReference(codfornecNew.getClass(), codfornecNew.getCodfornec());
                lote.setCodfornec(codfornecNew);
            }
            if (sitloteNew != null) {
                sitloteNew = em.getReference(sitloteNew.getClass(), sitloteNew.getSitlote());
                lote.setSitlote(sitloteNew);
            }
            List<ProdutoDoLote> attachedProdutoDoLoteListNew = new ArrayList<ProdutoDoLote>();
            for (ProdutoDoLote produtoDoLoteListNewProdutoDoLoteToAttach : produtoDoLoteListNew) {
                produtoDoLoteListNewProdutoDoLoteToAttach = em.getReference(produtoDoLoteListNewProdutoDoLoteToAttach.getClass(), produtoDoLoteListNewProdutoDoLoteToAttach.getProdutoDoLotePK());
                attachedProdutoDoLoteListNew.add(produtoDoLoteListNewProdutoDoLoteToAttach);
            }
            produtoDoLoteListNew = attachedProdutoDoLoteListNew;
            lote.setProdutoDoLoteList(produtoDoLoteListNew);
            List<PagamentoLote> attachedPagamentoLoteListNew = new ArrayList<PagamentoLote>();
            for (PagamentoLote pagamentoLoteListNewPagamentoLoteToAttach : pagamentoLoteListNew) {
                pagamentoLoteListNewPagamentoLoteToAttach = em.getReference(pagamentoLoteListNewPagamentoLoteToAttach.getClass(), pagamentoLoteListNewPagamentoLoteToAttach.getCodpaglote());
                attachedPagamentoLoteListNew.add(pagamentoLoteListNewPagamentoLoteToAttach);
            }
            pagamentoLoteListNew = attachedPagamentoLoteListNew;
            lote.setPagamentoLoteList(pagamentoLoteListNew);
            List<FuncionarioDoLote> attachedFuncionarioDoLoteListNew = new ArrayList<FuncionarioDoLote>();
            for (FuncionarioDoLote funcionarioDoLoteListNewFuncionarioDoLoteToAttach : funcionarioDoLoteListNew) {
                funcionarioDoLoteListNewFuncionarioDoLoteToAttach = em.getReference(funcionarioDoLoteListNewFuncionarioDoLoteToAttach.getClass(), funcionarioDoLoteListNewFuncionarioDoLoteToAttach.getFuncionarioDoLotePK());
                attachedFuncionarioDoLoteListNew.add(funcionarioDoLoteListNewFuncionarioDoLoteToAttach);
            }
            funcionarioDoLoteListNew = attachedFuncionarioDoLoteListNew;
            lote.setFuncionarioDoLoteList(funcionarioDoLoteListNew);
            List<ProducaoDiaria> attachedProducaoDiariaListNew = new ArrayList<ProducaoDiaria>();
            for (ProducaoDiaria producaoDiariaListNewProducaoDiariaToAttach : producaoDiariaListNew) {
                producaoDiariaListNewProducaoDiariaToAttach = em.getReference(producaoDiariaListNewProducaoDiariaToAttach.getClass(), producaoDiariaListNewProducaoDiariaToAttach.getProducaoDiariaPK());
                attachedProducaoDiariaListNew.add(producaoDiariaListNewProducaoDiariaToAttach);
            }
            producaoDiariaListNew = attachedProducaoDiariaListNew;
            lote.setProducaoDiariaList(producaoDiariaListNew);
            lote = em.merge(lote);
            if (codfornecOld != null && !codfornecOld.equals(codfornecNew)) {
                codfornecOld.getLoteList().remove(lote);
                codfornecOld = em.merge(codfornecOld);
            }
            if (codfornecNew != null && !codfornecNew.equals(codfornecOld)) {
                codfornecNew.getLoteList().add(lote);
                codfornecNew = em.merge(codfornecNew);
            }
            if (sitloteOld != null && !sitloteOld.equals(sitloteNew)) {
                sitloteOld.getLoteList().remove(lote);
                sitloteOld = em.merge(sitloteOld);
            }
            if (sitloteNew != null && !sitloteNew.equals(sitloteOld)) {
                sitloteNew.getLoteList().add(lote);
                sitloteNew = em.merge(sitloteNew);
            }
            for (ProdutoDoLote produtoDoLoteListNewProdutoDoLote : produtoDoLoteListNew) {
                if (!produtoDoLoteListOld.contains(produtoDoLoteListNewProdutoDoLote)) {
                    Lote oldLoteOfProdutoDoLoteListNewProdutoDoLote = produtoDoLoteListNewProdutoDoLote.getLote();
                    produtoDoLoteListNewProdutoDoLote.setLote(lote);
                    produtoDoLoteListNewProdutoDoLote = em.merge(produtoDoLoteListNewProdutoDoLote);
                    if (oldLoteOfProdutoDoLoteListNewProdutoDoLote != null && !oldLoteOfProdutoDoLoteListNewProdutoDoLote.equals(lote)) {
                        oldLoteOfProdutoDoLoteListNewProdutoDoLote.getProdutoDoLoteList().remove(produtoDoLoteListNewProdutoDoLote);
                        oldLoteOfProdutoDoLoteListNewProdutoDoLote = em.merge(oldLoteOfProdutoDoLoteListNewProdutoDoLote);
                    }
                }
            }
            for (PagamentoLote pagamentoLoteListOldPagamentoLote : pagamentoLoteListOld) {
                if (!pagamentoLoteListNew.contains(pagamentoLoteListOldPagamentoLote)) {
                    pagamentoLoteListOldPagamentoLote.setCodlote(null);
                    pagamentoLoteListOldPagamentoLote = em.merge(pagamentoLoteListOldPagamentoLote);
                }
            }
            for (PagamentoLote pagamentoLoteListNewPagamentoLote : pagamentoLoteListNew) {
                if (!pagamentoLoteListOld.contains(pagamentoLoteListNewPagamentoLote)) {
                    Lote oldCodloteOfPagamentoLoteListNewPagamentoLote = pagamentoLoteListNewPagamentoLote.getCodlote();
                    pagamentoLoteListNewPagamentoLote.setCodlote(lote);
                    pagamentoLoteListNewPagamentoLote = em.merge(pagamentoLoteListNewPagamentoLote);
                    if (oldCodloteOfPagamentoLoteListNewPagamentoLote != null && !oldCodloteOfPagamentoLoteListNewPagamentoLote.equals(lote)) {
                        oldCodloteOfPagamentoLoteListNewPagamentoLote.getPagamentoLoteList().remove(pagamentoLoteListNewPagamentoLote);
                        oldCodloteOfPagamentoLoteListNewPagamentoLote = em.merge(oldCodloteOfPagamentoLoteListNewPagamentoLote);
                    }
                }
            }
            for (FuncionarioDoLote funcionarioDoLoteListNewFuncionarioDoLote : funcionarioDoLoteListNew) {
                if (!funcionarioDoLoteListOld.contains(funcionarioDoLoteListNewFuncionarioDoLote)) {
                    Lote oldLoteOfFuncionarioDoLoteListNewFuncionarioDoLote = funcionarioDoLoteListNewFuncionarioDoLote.getLote();
                    funcionarioDoLoteListNewFuncionarioDoLote.setLote(lote);
                    funcionarioDoLoteListNewFuncionarioDoLote = em.merge(funcionarioDoLoteListNewFuncionarioDoLote);
                    if (oldLoteOfFuncionarioDoLoteListNewFuncionarioDoLote != null && !oldLoteOfFuncionarioDoLoteListNewFuncionarioDoLote.equals(lote)) {
                        oldLoteOfFuncionarioDoLoteListNewFuncionarioDoLote.getFuncionarioDoLoteList().remove(funcionarioDoLoteListNewFuncionarioDoLote);
                        oldLoteOfFuncionarioDoLoteListNewFuncionarioDoLote = em.merge(oldLoteOfFuncionarioDoLoteListNewFuncionarioDoLote);
                    }
                }
            }
            for (ProducaoDiaria producaoDiariaListNewProducaoDiaria : producaoDiariaListNew) {
                if (!producaoDiariaListOld.contains(producaoDiariaListNewProducaoDiaria)) {
                    Lote oldLoteOfProducaoDiariaListNewProducaoDiaria = producaoDiariaListNewProducaoDiaria.getLote();
                    producaoDiariaListNewProducaoDiaria.setLote(lote);
                    producaoDiariaListNewProducaoDiaria = em.merge(producaoDiariaListNewProducaoDiaria);
                    if (oldLoteOfProducaoDiariaListNewProducaoDiaria != null && !oldLoteOfProducaoDiariaListNewProducaoDiaria.equals(lote)) {
                        oldLoteOfProducaoDiariaListNewProducaoDiaria.getProducaoDiariaList().remove(producaoDiariaListNewProducaoDiaria);
                        oldLoteOfProducaoDiariaListNewProducaoDiaria = em.merge(oldLoteOfProducaoDiariaListNewProducaoDiaria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lote.getCodlote();
                if (findLote(id) == null) {
                    throw new NonexistentEntityException("The lote with id " + id + " no longer exists.");
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
            Lote lote;
            try {
                lote = em.getReference(Lote.class, id);
                lote.getCodlote();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lote with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProdutoDoLote> produtoDoLoteListOrphanCheck = lote.getProdutoDoLoteList();
            for (ProdutoDoLote produtoDoLoteListOrphanCheckProdutoDoLote : produtoDoLoteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Lote (" + lote + ") cannot be destroyed since the ProdutoDoLote " + produtoDoLoteListOrphanCheckProdutoDoLote + " in its produtoDoLoteList field has a non-nullable lote field.");
            }
            List<FuncionarioDoLote> funcionarioDoLoteListOrphanCheck = lote.getFuncionarioDoLoteList();
            for (FuncionarioDoLote funcionarioDoLoteListOrphanCheckFuncionarioDoLote : funcionarioDoLoteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Lote (" + lote + ") cannot be destroyed since the FuncionarioDoLote " + funcionarioDoLoteListOrphanCheckFuncionarioDoLote + " in its funcionarioDoLoteList field has a non-nullable lote field.");
            }
            List<ProducaoDiaria> producaoDiariaListOrphanCheck = lote.getProducaoDiariaList();
            for (ProducaoDiaria producaoDiariaListOrphanCheckProducaoDiaria : producaoDiariaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Lote (" + lote + ") cannot be destroyed since the ProducaoDiaria " + producaoDiariaListOrphanCheckProducaoDiaria + " in its producaoDiariaList field has a non-nullable lote field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Fornecedor codfornec = lote.getCodfornec();
            if (codfornec != null) {
                codfornec.getLoteList().remove(lote);
                codfornec = em.merge(codfornec);
            }
            SituacaoLote sitlote = lote.getSitlote();
            if (sitlote != null) {
                sitlote.getLoteList().remove(lote);
                sitlote = em.merge(sitlote);
            }
            List<PagamentoLote> pagamentoLoteList = lote.getPagamentoLoteList();
            for (PagamentoLote pagamentoLoteListPagamentoLote : pagamentoLoteList) {
                pagamentoLoteListPagamentoLote.setCodlote(null);
                pagamentoLoteListPagamentoLote = em.merge(pagamentoLoteListPagamentoLote);
            }
            em.remove(lote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lote> findLoteEntities() {
        return findLoteEntities(true, -1, -1);
    }

    public List<Lote> findLoteEntities(int maxResults, int firstResult) {
        return findLoteEntities(false, maxResults, firstResult);
    }

    private List<Lote> findLoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lote.class));
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

    public Lote findLote(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lote.class, id);
        } finally {
            em.close();
        }
    }

    public int getLoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lote> rt = cq.from(Lote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
