/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maicon
 */
@Entity
@Table(name = "MATERIAL_DO_PRODUTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialDoProduto.findAll", query = "SELECT m FROM MaterialDoProduto m"),
    @NamedQuery(name = "MaterialDoProduto.findByCodprod", query = "SELECT m FROM MaterialDoProduto m WHERE m.materialDoProdutoPK.codprod = :codprod"),
    @NamedQuery(name = "MaterialDoProduto.findByCodmat", query = "SELECT m FROM MaterialDoProduto m WHERE m.materialDoProdutoPK.codmat = :codmat"),
    @NamedQuery(name = "MaterialDoProduto.findByQtde", query = "SELECT m FROM MaterialDoProduto m WHERE m.qtde = :qtde")})
public class MaterialDoProduto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MaterialDoProdutoPK materialDoProdutoPK;
    @Column(name = "QTDE")
    private Integer qtde;
    @JoinColumn(name = "CODMAT", referencedColumnName = "CODMAT", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Material material;
    @JoinColumn(name = "CODPROD", referencedColumnName = "CODPROD", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Produto produto;

    public MaterialDoProduto() {
    }

    public MaterialDoProduto(MaterialDoProdutoPK materialDoProdutoPK) {
        this.materialDoProdutoPK = materialDoProdutoPK;
    }

    public MaterialDoProduto(int codprod, int codmat) {
        this.materialDoProdutoPK = new MaterialDoProdutoPK(codprod, codmat);
    }

    public MaterialDoProdutoPK getMaterialDoProdutoPK() {
        return materialDoProdutoPK;
    }

    public void setMaterialDoProdutoPK(MaterialDoProdutoPK materialDoProdutoPK) {
        this.materialDoProdutoPK = materialDoProdutoPK;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialDoProdutoPK != null ? materialDoProdutoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialDoProduto)) {
            return false;
        }
        MaterialDoProduto other = (MaterialDoProduto) object;
        if ((this.materialDoProdutoPK == null && other.materialDoProdutoPK != null) || (this.materialDoProdutoPK != null && !this.materialDoProdutoPK.equals(other.materialDoProdutoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ipsum2.MaterialDoProduto[ materialDoProdutoPK=" + materialDoProdutoPK + " ]";
    }
    
}
