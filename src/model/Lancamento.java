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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maicon
 */
@Entity
@Table(name = "LANCAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lancamento.findAll", query = "SELECT l FROM Lancamento l"),
    @NamedQuery(name = "Lancamento.findByCodlanc", query = "SELECT l FROM Lancamento l WHERE l.codlanc = :codlanc"),
    @NamedQuery(name = "Lancamento.findByAtivo", query = "SELECT l FROM Lancamento l WHERE l.ativo = :ativo"),
    @NamedQuery(name = "Lancamento.findByValor", query = "SELECT l FROM Lancamento l WHERE l.valor = :valor"),
    @NamedQuery(name = "Lancamento.findMaxId", query = "SELECT l FROM Lancamento l ORDER by l.codlanc DESC"),
    @NamedQuery(name = "Lancamento.findByEstorno", query = "SELECT l FROM Lancamento l WHERE l.estorno = :estorno")})
public class Lancamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODLANC")
    private Integer codlanc;
    @Column(name = "ATIVO")
    private Boolean ativo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private Double valor;
    @Column(name = "ESTORNO")
    private Boolean estorno;
    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lancamento", fetch = FetchType.EAGER)
    private LancamentoSaida lancamentoSaida;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lancamento", fetch = FetchType.EAGER)
    private LancamentoRecforn lancamentoRecforn;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lancamento", fetch = FetchType.EAGER)
    private List<SituacaoLancamento> situacaoLancamentoList;
    @JoinColumn(name = "CODCAIXA", referencedColumnName = "CODCAIXA")
    @ManyToOne(fetch = FetchType.EAGER)
    private Caixa codcaixa;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lancamento", fetch = FetchType.EAGER)
    private LancamentoPagfunc lancamentoPagfunc;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lancamento", fetch = FetchType.EAGER)
    private LancamentoEntrada lancamentoEntrada;
    
    public Lancamento() {
        this.setEstorno(false);
        this.setLancamentoEntrada(null);
        this.setLancamentoSaida(null);
        this.setLancamentoPagfunc(null);
        this.setLancamentoRecforn(null);
        this.setSituacaoLancamentoList(null);
    }
    
    public Lancamento(Integer codlanc) {
        this.codlanc = codlanc;
    }
    
    public Integer getCodlanc() {
        return codlanc;
    }
    
    public void setCodlanc(Integer codlanc) {
        this.codlanc = codlanc;
    }
    
    public Boolean getAtivo() {
        return ativo;
    }
    
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    public Double getValor() {
        return valor;
    }
    
    public void setValor(Double valor) {
        this.valor = valor;
    }
    
    public Boolean getEstorno() {
        return estorno;
    }
    
    public void setEstorno(Boolean estorno) {
        this.estorno = estorno;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public LancamentoSaida getLancamentoSaida() {
        return lancamentoSaida;
    }
    
    public void setLancamentoSaida(LancamentoSaida lancamentoSaida) {
        this.lancamentoSaida = lancamentoSaida;
    }
    
    public LancamentoRecforn getLancamentoRecforn() {
        return lancamentoRecforn;
    }
    
    public void setLancamentoRecforn(LancamentoRecforn lancamentoRecforn) {
        this.lancamentoRecforn = lancamentoRecforn;
    }
    
    @XmlTransient
    public List<SituacaoLancamento> getSituacaoLancamentoList() {
        return situacaoLancamentoList;
    }
    
    public void setSituacaoLancamentoList(List<SituacaoLancamento> situacaoLancamentoList) {
        this.situacaoLancamentoList = situacaoLancamentoList;
    }
    
    public Caixa getCodcaixa() {
        return codcaixa;
    }
    
    public void setCodcaixa(Caixa codcaixa) {
        this.codcaixa = codcaixa;
    }
    
    public LancamentoPagfunc getLancamentoPagfunc() {
        return lancamentoPagfunc;
    }
    
    public void setLancamentoPagfunc(LancamentoPagfunc lancamentoPagfunc) {
        this.lancamentoPagfunc = lancamentoPagfunc;
    }
    
    public LancamentoEntrada getLancamentoEntrada() {
        return lancamentoEntrada;
    }
    
    public void setLancamentoEntrada(LancamentoEntrada lancamentoEntrada) {
        this.lancamentoEntrada = lancamentoEntrada;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codlanc != null ? codlanc.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lancamento)) {
            return false;
        }
        Lancamento other = (Lancamento) object;
        if ((this.codlanc == null && other.codlanc != null) || (this.codlanc != null && !this.codlanc.equals(other.codlanc))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return this.getDescricao();
    }
    
}
