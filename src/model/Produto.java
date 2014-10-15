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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "PRODUTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findByCodprod", query = "SELECT p FROM Produto p WHERE p.codprod = :codprod"),
    @NamedQuery(name = "Produto.findByAtivo", query = "SELECT p FROM Produto p WHERE p.ativo = :ativo"),
    @NamedQuery(name = "Produto.findByPreco", query = "SELECT p FROM Produto p WHERE p.preco = :preco")})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODPROD")
    private Integer codprod;
    @Column(name = "ATIVO")
    private Short ativo;
    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECO")
    private Double preco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private List<ProdutoDoLote> produtoDoLoteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private List<ProducaoDiaria> producaoDiariaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private List<MaterialDoProduto> materialDoProdutoList;

    public Produto() {
        this.produtoDoLoteList = null;
        this.producaoDiariaList = null;
        this.materialDoProdutoList = null;
    }

    public Produto(Integer codprod) {
        this.codprod = codprod;
    }

    public Integer getCodprod() {
        return codprod;
    }

    public void setCodprod(Integer codprod) {
        this.codprod = codprod;
    }

    public Short getAtivo() {
        return ativo;
    }

    public void setAtivo(Short ativo) {
        this.ativo = ativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @XmlTransient
    public List<ProdutoDoLote> getProdutoDoLoteList() {
        return produtoDoLoteList;
    }

    public void setProdutoDoLoteList(List<ProdutoDoLote> produtoDoLoteList) {
        this.produtoDoLoteList = produtoDoLoteList;
    }

    @XmlTransient
    public List<ProducaoDiaria> getProducaoDiariaList() {
        return producaoDiariaList;
    }

    public void setProducaoDiariaList(List<ProducaoDiaria> producaoDiariaList) {
        this.producaoDiariaList = producaoDiariaList;
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
        hash += (codprod != null ? codprod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.codprod == null && other.codprod != null) || (this.codprod != null && !this.codprod.equals(other.codprod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

}
