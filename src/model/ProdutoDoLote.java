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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "PRODUTO_DO_LOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProdutoDoLote.findAll", query = "SELECT p FROM ProdutoDoLote p"),
    @NamedQuery(name = "ProdutoDoLote.findByQtde", query = "SELECT p FROM ProdutoDoLote p WHERE p.qtde = :qtde"),
    @NamedQuery(name = "ProdutoDoLote.findByCodprod", query = "SELECT p FROM ProdutoDoLote p WHERE p.produtoDoLotePK.codprod = :codprod"),
    @NamedQuery(name = "ProdutoDoLote.findByCodlote", query = "SELECT p FROM ProdutoDoLote p WHERE p.produtoDoLotePK.codlote = :codlote")})
public class ProdutoDoLote implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProdutoDoLotePK produtoDoLotePK;
    @Column(name = "QTDE")
    private Integer qtde;
    @JoinColumn(name = "CODLOTE", referencedColumnName = "CODLOTE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Lote lote;
    @JoinColumn(name = "CODPROD", referencedColumnName = "CODPROD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public ProdutoDoLote() {
    }

    public ProdutoDoLote(ProdutoDoLotePK produtoDoLotePK) {
        this.produtoDoLotePK = produtoDoLotePK;
    }

    public ProdutoDoLote(int codprod, int codlote) {
        this.produtoDoLotePK = new ProdutoDoLotePK(codprod, codlote);
    }

    public ProdutoDoLotePK getProdutoDoLotePK() {
        return produtoDoLotePK;
    }

    public void setProdutoDoLotePK(ProdutoDoLotePK produtoDoLotePK) {
        this.produtoDoLotePK = produtoDoLotePK;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
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
        hash += (produtoDoLotePK != null ? produtoDoLotePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoDoLote)) {
            return false;
        }
        ProdutoDoLote other = (ProdutoDoLote) object;
        if ((this.produtoDoLotePK == null && other.produtoDoLotePK != null) || (this.produtoDoLotePK != null && !this.produtoDoLotePK.equals(other.produtoDoLotePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ProdutoDoLote[ produtoDoLotePK=" + produtoDoLotePK + " ]";
    }
    
}
