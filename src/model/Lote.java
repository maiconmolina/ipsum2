/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "LOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lote.findAll", query = "SELECT l FROM Lote l"),
    @NamedQuery(name = "Lote.findByCodlote", query = "SELECT l FROM Lote l WHERE l.codlote = :codlote"),
    @NamedQuery(name = "Lote.findByCancelado", query = "SELECT l FROM Lote l WHERE l.cancelado = :cancelado"),
    @NamedQuery(name = "Lote.findByDataent", query = "SELECT l FROM Lote l WHERE l.dataent = :dataent"),
    @NamedQuery(name = "Lote.findByDatasai", query = "SELECT l FROM Lote l WHERE l.datasai = :datasai")})
public class Lote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODLOTE")
    private Integer codlote;
    @Column(name = "CANCELADO")
    private Short cancelado;
    @Column(name = "DATAENT")
    @Temporal(TemporalType.DATE)
    private Date dataent;
    @Column(name = "DATASAI")
    @Temporal(TemporalType.DATE)
    private Date datasai;
    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lote")
    private List<ProdutoDoLote> produtoDoLoteList;
    @OneToMany(mappedBy = "codlote")
    private List<PagamentoLote> pagamentoLoteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lote")
    private List<FuncionarioDoLote> funcionarioDoLoteList;
    @JoinColumn(name = "CODFORNEC", referencedColumnName = "CODFORNEC")
    @ManyToOne
    private Fornecedor codfornec;
    @JoinColumn(name = "SITLOTE", referencedColumnName = "SITLOTE")
    @ManyToOne
    private SituacaoLote sitlote;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lote")
    private List<ProducaoDiaria> producaoDiariaList;

    public Lote() {
        this.pagamentoLoteList = null;
        this.funcionarioDoLoteList = null;
        this.produtoDoLoteList = null;
        this.codfornec = null;
    }

    public Lote(Integer codlote) {
        this.codlote = codlote;
    }

    public Integer getCodlote() {
        return codlote;
    }

    public void setCodlote(Integer codlote) {
        this.codlote = codlote;
    }

    public Short getCancelado() {
        return cancelado;
    }

    public void setCancelado(Short cancelado) {
        this.cancelado = cancelado;
    }

    public Date getDataent() {
        return dataent;
    }

    public void setDataent(Date dataent) {
        this.dataent = dataent;
    }

    public Date getDatasai() {
        return datasai;
    }

    public void setDatasai(Date datasai) {
        this.datasai = datasai;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<ProdutoDoLote> getProdutoDoLoteList() {
        return produtoDoLoteList;
    }

    public void setProdutoDoLoteList(List<ProdutoDoLote> produtoDoLoteList) {
        this.produtoDoLoteList = produtoDoLoteList;
    }

    @XmlTransient
    public List<PagamentoLote> getPagamentoLoteList() {
        return pagamentoLoteList;
    }

    public void setPagamentoLoteList(List<PagamentoLote> pagamentoLoteList) {
        this.pagamentoLoteList = pagamentoLoteList;
    }

    @XmlTransient
    public List<FuncionarioDoLote> getFuncionarioDoLoteList() {
        return funcionarioDoLoteList;
    }

    public void setFuncionarioDoLoteList(List<FuncionarioDoLote> funcionarioDoLoteList) {
        this.funcionarioDoLoteList = funcionarioDoLoteList;
    }

    public Fornecedor getCodfornec() {
        return codfornec;
    }

    public void setCodfornec(Fornecedor codfornec) {
        this.codfornec = codfornec;
    }

    public SituacaoLote getSitlote() {
        return sitlote;
    }

    public void setSitlote(SituacaoLote sitlote) {
        this.sitlote = sitlote;
    }

    @XmlTransient
    public List<ProducaoDiaria> getProducaoDiariaList() {
        return producaoDiariaList;
    }

    public void setProducaoDiariaList(List<ProducaoDiaria> producaoDiariaList) {
        this.producaoDiariaList = producaoDiariaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codlote != null ? codlote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lote)) {
            return false;
        }
        Lote other = (Lote) object;
        if ((this.codlote == null && other.codlote != null) || (this.codlote != null && !this.codlote.equals(other.codlote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Lote[ codlote=" + codlote + " ]";
    }
    
}
