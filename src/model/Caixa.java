/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@Table(name = "CAIXA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caixa.findAll", query = "SELECT c FROM Caixa c"),
    @NamedQuery(name = "Caixa.findByCodcaixa", query = "SELECT c FROM Caixa c WHERE c.codcaixa = :codcaixa"),
    @NamedQuery(name = "Caixa.findBySaldo", query = "SELECT c FROM Caixa c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Caixa.findByStatus", query = "SELECT c FROM Caixa c WHERE c.status = :status")})
public class Caixa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODCAIXA")
    private Integer codcaixa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SALDO")
    private Double saldo;
    @Column(name = "STATUS")
    private Boolean status;
    @OneToMany(mappedBy = "codcaixa", fetch = FetchType.EAGER)
    private List<Lancamento> lancamentoList;

    public Caixa() {
    }

    public Caixa(Integer codcaixa) {
        this.codcaixa = codcaixa;
    }

    public Integer getCodcaixa() {
        return codcaixa;
    }

    public void setCodcaixa(Integer codcaixa) {
        this.codcaixa = codcaixa;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @XmlTransient
    public List<Lancamento> getLancamentoList() {
        return lancamentoList;
    }

    public void setLancamentoList(List<Lancamento> lancamentoList) {
        this.lancamentoList = lancamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcaixa != null ? codcaixa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caixa)) {
            return false;
        }
        Caixa other = (Caixa) object;
        if ((this.codcaixa == null && other.codcaixa != null) || (this.codcaixa != null && !this.codcaixa.equals(other.codcaixa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ipsum2.Caixa[ codcaixa=" + codcaixa + " ]";
    }
    
}
