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
import model.Uf;
import model.Usuario;
import model.Nfe;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import model.Fornecedor;
import model.Lote;
import model.LancamentoRecforn;
import model.Material;

/**
 *
 * @author Maicon
 */
public class FornecedorJpaController implements Serializable {

    public FornecedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fornecedor fornecedor) throws PreexistingEntityException, Exception {
        if (fornecedor.getNfeList() == null) {
            fornecedor.setNfeList(new ArrayList<Nfe>());
        }
        if (fornecedor.getLoteList() == null) {
            fornecedor.setLoteList(new ArrayList<Lote>());
        }
        if (fornecedor.getLancamentoRecfornList() == null) {
            fornecedor.setLancamentoRecfornList(new ArrayList<LancamentoRecforn>());
        }
        if (fornecedor.getMaterialList() == null) {
            fornecedor.setMaterialList(new ArrayList<Material>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Uf coduf = fornecedor.getCoduf();
            if (coduf != null) {
                coduf = em.getReference(coduf.getClass(), coduf.getCoduf());
                fornecedor.setCoduf(coduf);
            }
            Usuario usuario = fornecedor.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getCodigo());
                fornecedor.setUsuario(usuario);
            }
            List<Nfe> attachedNfeList = new ArrayList<Nfe>();
            for (Nfe nfeListNfeToAttach : fornecedor.getNfeList()) {
                nfeListNfeToAttach = em.getReference(nfeListNfeToAttach.getClass(), nfeListNfeToAttach.getCodnfe());
                attachedNfeList.add(nfeListNfeToAttach);
            }
            fornecedor.setNfeList(attachedNfeList);
            List<Lote> attachedLoteList = new ArrayList<Lote>();
            for (Lote loteListLoteToAttach : fornecedor.getLoteList()) {
                loteListLoteToAttach = em.getReference(loteListLoteToAttach.getClass(), loteListLoteToAttach.getCodlote());
                attachedLoteList.add(loteListLoteToAttach);
            }
            fornecedor.setLoteList(attachedLoteList);
            List<LancamentoRecforn> attachedLancamentoRecfornList = new ArrayList<LancamentoRecforn>();
            for (LancamentoRecforn lancamentoRecfornListLancamentoRecfornToAttach : fornecedor.getLancamentoRecfornList()) {
                lancamentoRecfornListLancamentoRecfornToAttach = em.getReference(lancamentoRecfornListLancamentoRecfornToAttach.getClass(), lancamentoRecfornListLancamentoRecfornToAttach.getCodlanc());
                attachedLancamentoRecfornList.add(lancamentoRecfornListLancamentoRecfornToAttach);
            }
            fornecedor.setLancamentoRecfornList(attachedLancamentoRecfornList);
            List<Material> attachedMaterialList = new ArrayList<Material>();
            for (Material materialListMaterialToAttach : fornecedor.getMaterialList()) {
                materialListMaterialToAttach = em.getReference(materialListMaterialToAttach.getClass(), materialListMaterialToAttach.getCodmat());
                attachedMaterialList.add(materialListMaterialToAttach);
            }
            fornecedor.setMaterialList(attachedMaterialList);
            em.persist(fornecedor);
            if (coduf != null) {
                coduf.getFornecedorList().add(fornecedor);
                coduf = em.merge(coduf);
            }
            if (usuario != null) {
                Fornecedor oldFornecedorOfUsuario = usuario.getFornecedor();
                if (oldFornecedorOfUsuario != null) {
                    oldFornecedorOfUsuario.setUsuario(null);
                    oldFornecedorOfUsuario = em.merge(oldFornecedorOfUsuario);
                }
                usuario.setFornecedor(fornecedor);
                usuario = em.merge(usuario);
            }
            for (Nfe nfeListNfe : fornecedor.getNfeList()) {
                Fornecedor oldCodfornecOfNfeListNfe = nfeListNfe.getCodfornec();
                nfeListNfe.setCodfornec(fornecedor);
                nfeListNfe = em.merge(nfeListNfe);
                if (oldCodfornecOfNfeListNfe != null) {
                    oldCodfornecOfNfeListNfe.getNfeList().remove(nfeListNfe);
                    oldCodfornecOfNfeListNfe = em.merge(oldCodfornecOfNfeListNfe);
                }
            }
            for (Lote loteListLote : fornecedor.getLoteList()) {
                Fornecedor oldCodfornecOfLoteListLote = loteListLote.getCodfornec();
                loteListLote.setCodfornec(fornecedor);
                loteListLote = em.merge(loteListLote);
                if (oldCodfornecOfLoteListLote != null) {
                    oldCodfornecOfLoteListLote.getLoteList().remove(loteListLote);
                    oldCodfornecOfLoteListLote = em.merge(oldCodfornecOfLoteListLote);
                }
            }
            for (LancamentoRecforn lancamentoRecfornListLancamentoRecforn : fornecedor.getLancamentoRecfornList()) {
                Fornecedor oldCodfornecOfLancamentoRecfornListLancamentoRecforn = lancamentoRecfornListLancamentoRecforn.getCodfornec();
                lancamentoRecfornListLancamentoRecforn.setCodfornec(fornecedor);
                lancamentoRecfornListLancamentoRecforn = em.merge(lancamentoRecfornListLancamentoRecforn);
                if (oldCodfornecOfLancamentoRecfornListLancamentoRecforn != null) {
                    oldCodfornecOfLancamentoRecfornListLancamentoRecforn.getLancamentoRecfornList().remove(lancamentoRecfornListLancamentoRecforn);
                    oldCodfornecOfLancamentoRecfornListLancamentoRecforn = em.merge(oldCodfornecOfLancamentoRecfornListLancamentoRecforn);
                }
            }
            for (Material materialListMaterial : fornecedor.getMaterialList()) {
                Fornecedor oldCodfornecOfMaterialListMaterial = materialListMaterial.getCodfornec();
                materialListMaterial.setCodfornec(fornecedor);
                materialListMaterial = em.merge(materialListMaterial);
                if (oldCodfornecOfMaterialListMaterial != null) {
                    oldCodfornecOfMaterialListMaterial.getMaterialList().remove(materialListMaterial);
                    oldCodfornecOfMaterialListMaterial = em.merge(oldCodfornecOfMaterialListMaterial);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFornecedor(fornecedor.getCodfornec()) != null) {
                throw new PreexistingEntityException("Fornecedor " + fornecedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fornecedor fornecedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor persistentFornecedor = em.find(Fornecedor.class, fornecedor.getCodfornec());
            Uf codufOld = persistentFornecedor.getCoduf();
            Uf codufNew = fornecedor.getCoduf();
            Usuario usuarioOld = persistentFornecedor.getUsuario();
            Usuario usuarioNew = fornecedor.getUsuario();
            List<Nfe> nfeListOld = persistentFornecedor.getNfeList();
            List<Nfe> nfeListNew = fornecedor.getNfeList();
            List<Lote> loteListOld = persistentFornecedor.getLoteList();
            List<Lote> loteListNew = fornecedor.getLoteList();
            List<LancamentoRecforn> lancamentoRecfornListOld = persistentFornecedor.getLancamentoRecfornList();
            List<LancamentoRecforn> lancamentoRecfornListNew = fornecedor.getLancamentoRecfornList();
            List<Material> materialListOld = persistentFornecedor.getMaterialList();
            List<Material> materialListNew = fornecedor.getMaterialList();
            List<String> illegalOrphanMessages = null;
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Usuario " + usuarioOld + " since its fornecedor field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codufNew != null) {
                codufNew = em.getReference(codufNew.getClass(), codufNew.getCoduf());
                fornecedor.setCoduf(codufNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getCodigo());
                fornecedor.setUsuario(usuarioNew);
            }
            List<Nfe> attachedNfeListNew = new ArrayList<Nfe>();
            for (Nfe nfeListNewNfeToAttach : nfeListNew) {
                nfeListNewNfeToAttach = em.getReference(nfeListNewNfeToAttach.getClass(), nfeListNewNfeToAttach.getCodnfe());
                attachedNfeListNew.add(nfeListNewNfeToAttach);
            }
            nfeListNew = attachedNfeListNew;
            fornecedor.setNfeList(nfeListNew);
            List<Lote> attachedLoteListNew = new ArrayList<Lote>();
            for (Lote loteListNewLoteToAttach : loteListNew) {
                loteListNewLoteToAttach = em.getReference(loteListNewLoteToAttach.getClass(), loteListNewLoteToAttach.getCodlote());
                attachedLoteListNew.add(loteListNewLoteToAttach);
            }
            loteListNew = attachedLoteListNew;
            fornecedor.setLoteList(loteListNew);
            List<LancamentoRecforn> attachedLancamentoRecfornListNew = new ArrayList<LancamentoRecforn>();
            for (LancamentoRecforn lancamentoRecfornListNewLancamentoRecfornToAttach : lancamentoRecfornListNew) {
                lancamentoRecfornListNewLancamentoRecfornToAttach = em.getReference(lancamentoRecfornListNewLancamentoRecfornToAttach.getClass(), lancamentoRecfornListNewLancamentoRecfornToAttach.getCodlanc());
                attachedLancamentoRecfornListNew.add(lancamentoRecfornListNewLancamentoRecfornToAttach);
            }
            lancamentoRecfornListNew = attachedLancamentoRecfornListNew;
            fornecedor.setLancamentoRecfornList(lancamentoRecfornListNew);
            List<Material> attachedMaterialListNew = new ArrayList<Material>();
            for (Material materialListNewMaterialToAttach : materialListNew) {
                materialListNewMaterialToAttach = em.getReference(materialListNewMaterialToAttach.getClass(), materialListNewMaterialToAttach.getCodmat());
                attachedMaterialListNew.add(materialListNewMaterialToAttach);
            }
            materialListNew = attachedMaterialListNew;
            fornecedor.setMaterialList(materialListNew);
            fornecedor = em.merge(fornecedor);
            if (codufOld != null && !codufOld.equals(codufNew)) {
                codufOld.getFornecedorList().remove(fornecedor);
                codufOld = em.merge(codufOld);
            }
            if (codufNew != null && !codufNew.equals(codufOld)) {
                codufNew.getFornecedorList().add(fornecedor);
                codufNew = em.merge(codufNew);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                Fornecedor oldFornecedorOfUsuario = usuarioNew.getFornecedor();
                if (oldFornecedorOfUsuario != null) {
                    oldFornecedorOfUsuario.setUsuario(null);
                    oldFornecedorOfUsuario = em.merge(oldFornecedorOfUsuario);
                }
                usuarioNew.setFornecedor(fornecedor);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Nfe nfeListOldNfe : nfeListOld) {
                if (!nfeListNew.contains(nfeListOldNfe)) {
                    nfeListOldNfe.setCodfornec(null);
                    nfeListOldNfe = em.merge(nfeListOldNfe);
                }
            }
            for (Nfe nfeListNewNfe : nfeListNew) {
                if (!nfeListOld.contains(nfeListNewNfe)) {
                    Fornecedor oldCodfornecOfNfeListNewNfe = nfeListNewNfe.getCodfornec();
                    nfeListNewNfe.setCodfornec(fornecedor);
                    nfeListNewNfe = em.merge(nfeListNewNfe);
                    if (oldCodfornecOfNfeListNewNfe != null && !oldCodfornecOfNfeListNewNfe.equals(fornecedor)) {
                        oldCodfornecOfNfeListNewNfe.getNfeList().remove(nfeListNewNfe);
                        oldCodfornecOfNfeListNewNfe = em.merge(oldCodfornecOfNfeListNewNfe);
                    }
                }
            }
            for (Lote loteListOldLote : loteListOld) {
                if (!loteListNew.contains(loteListOldLote)) {
                    loteListOldLote.setCodfornec(null);
                    loteListOldLote = em.merge(loteListOldLote);
                }
            }
            for (Lote loteListNewLote : loteListNew) {
                if (!loteListOld.contains(loteListNewLote)) {
                    Fornecedor oldCodfornecOfLoteListNewLote = loteListNewLote.getCodfornec();
                    loteListNewLote.setCodfornec(fornecedor);
                    loteListNewLote = em.merge(loteListNewLote);
                    if (oldCodfornecOfLoteListNewLote != null && !oldCodfornecOfLoteListNewLote.equals(fornecedor)) {
                        oldCodfornecOfLoteListNewLote.getLoteList().remove(loteListNewLote);
                        oldCodfornecOfLoteListNewLote = em.merge(oldCodfornecOfLoteListNewLote);
                    }
                }
            }
            for (LancamentoRecforn lancamentoRecfornListOldLancamentoRecforn : lancamentoRecfornListOld) {
                if (!lancamentoRecfornListNew.contains(lancamentoRecfornListOldLancamentoRecforn)) {
                    lancamentoRecfornListOldLancamentoRecforn.setCodfornec(null);
                    lancamentoRecfornListOldLancamentoRecforn = em.merge(lancamentoRecfornListOldLancamentoRecforn);
                }
            }
            for (LancamentoRecforn lancamentoRecfornListNewLancamentoRecforn : lancamentoRecfornListNew) {
                if (!lancamentoRecfornListOld.contains(lancamentoRecfornListNewLancamentoRecforn)) {
                    Fornecedor oldCodfornecOfLancamentoRecfornListNewLancamentoRecforn = lancamentoRecfornListNewLancamentoRecforn.getCodfornec();
                    lancamentoRecfornListNewLancamentoRecforn.setCodfornec(fornecedor);
                    lancamentoRecfornListNewLancamentoRecforn = em.merge(lancamentoRecfornListNewLancamentoRecforn);
                    if (oldCodfornecOfLancamentoRecfornListNewLancamentoRecforn != null && !oldCodfornecOfLancamentoRecfornListNewLancamentoRecforn.equals(fornecedor)) {
                        oldCodfornecOfLancamentoRecfornListNewLancamentoRecforn.getLancamentoRecfornList().remove(lancamentoRecfornListNewLancamentoRecforn);
                        oldCodfornecOfLancamentoRecfornListNewLancamentoRecforn = em.merge(oldCodfornecOfLancamentoRecfornListNewLancamentoRecforn);
                    }
                }
            }
            for (Material materialListOldMaterial : materialListOld) {
                if (!materialListNew.contains(materialListOldMaterial)) {
                    materialListOldMaterial.setCodfornec(null);
                    materialListOldMaterial = em.merge(materialListOldMaterial);
                }
            }
            for (Material materialListNewMaterial : materialListNew) {
                if (!materialListOld.contains(materialListNewMaterial)) {
                    Fornecedor oldCodfornecOfMaterialListNewMaterial = materialListNewMaterial.getCodfornec();
                    materialListNewMaterial.setCodfornec(fornecedor);
                    materialListNewMaterial = em.merge(materialListNewMaterial);
                    if (oldCodfornecOfMaterialListNewMaterial != null && !oldCodfornecOfMaterialListNewMaterial.equals(fornecedor)) {
                        oldCodfornecOfMaterialListNewMaterial.getMaterialList().remove(materialListNewMaterial);
                        oldCodfornecOfMaterialListNewMaterial = em.merge(oldCodfornecOfMaterialListNewMaterial);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fornecedor.getCodfornec();
                if (findFornecedor(id) == null) {
                    throw new NonexistentEntityException("The fornecedor with id " + id + " no longer exists.");
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
            Fornecedor fornecedor;
            try {
                fornecedor = em.getReference(Fornecedor.class, id);
                fornecedor.getCodfornec();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fornecedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Usuario usuarioOrphanCheck = fornecedor.getUsuario();
            if (usuarioOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fornecedor (" + fornecedor + ") cannot be destroyed since the Usuario " + usuarioOrphanCheck + " in its usuario field has a non-nullable fornecedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Uf coduf = fornecedor.getCoduf();
            if (coduf != null) {
                coduf.getFornecedorList().remove(fornecedor);
                coduf = em.merge(coduf);
            }
            List<Nfe> nfeList = fornecedor.getNfeList();
            for (Nfe nfeListNfe : nfeList) {
                nfeListNfe.setCodfornec(null);
                nfeListNfe = em.merge(nfeListNfe);
            }
            List<Lote> loteList = fornecedor.getLoteList();
            for (Lote loteListLote : loteList) {
                loteListLote.setCodfornec(null);
                loteListLote = em.merge(loteListLote);
            }
            List<LancamentoRecforn> lancamentoRecfornList = fornecedor.getLancamentoRecfornList();
            for (LancamentoRecforn lancamentoRecfornListLancamentoRecforn : lancamentoRecfornList) {
                lancamentoRecfornListLancamentoRecforn.setCodfornec(null);
                lancamentoRecfornListLancamentoRecforn = em.merge(lancamentoRecfornListLancamentoRecforn);
            }
            List<Material> materialList = fornecedor.getMaterialList();
            for (Material materialListMaterial : materialList) {
                materialListMaterial.setCodfornec(null);
                materialListMaterial = em.merge(materialListMaterial);
            }
            em.remove(fornecedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fornecedor> findFornecedorEntities() {
        return findFornecedorEntities(true, -1, -1);
    }

    public List<Fornecedor> findFornecedorEntities(int maxResults, int firstResult) {
        return findFornecedorEntities(false, maxResults, firstResult);
    }

    private List<Fornecedor> findFornecedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fornecedor.class));
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

    public Fornecedor findFornecedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fornecedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getFornecedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fornecedor> rt = cq.from(Fornecedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
