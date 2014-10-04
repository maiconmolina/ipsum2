/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maicon
 */
@Entity
@Table(name = "MATERIAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m"),
    @NamedQuery(name = "Material.findByCodmat", query = "SELECT m FROM Material m WHERE m.codmat = :codmat"),
    @NamedQuery(name = "Material.findByQtde", query = "SELECT m FROM Material m WHERE m.qtde = :qtde"),
    @NamedQuery(name = "Material.findByAtivo", query = "SELECT m FROM Material m WHERE m.ativo = :ativo")})
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODMAT")
    private Integer codmat;
    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "QTDE")
    private Integer qtde;
    @Column(name = "ATIVO")
    private Boolean ativo;
    @JoinColumn(name = "CODFORNEC", referencedColumnName = "CODFORNEC")
    @ManyToOne(fetch = FetchType.EAGER)
    private Fornecedor codfornec;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "material", fetch = FetchType.EAGER)
    private List<MaterialDoProduto> materialDoProdutoList;

    public Material() {
    }

    public Material(Integer codmat) {
        this.codmat = codmat;
    }

    public Integer getCodmat() {
        return codmat;
    }

    public void setCodmat(Integer codmat) {
        this.codmat = codmat;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Fornecedor getCodfornec() {
        return codfornec;
    }

    public void setCodfornec(Fornecedor codfornec) {
        this.codfornec = codfornec;
    }

    @XmlTransient
    public List<MaterialDoProduto> getMaterialDoProdutoList() {
        return materialDoProdutoList;
    }

    public void setMaterialDoProdutoList(List<MaterialDoProduto> materialDoProdutoList) {
        this.materialDoProdutoList = materialDoProdutoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codmat != null ? codmat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.codmat == null && other.codmat != null) || (this.codmat != null && !this.codmat.equals(other.codmat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codmat.toString() + " " + descricao;
    }
    
}
