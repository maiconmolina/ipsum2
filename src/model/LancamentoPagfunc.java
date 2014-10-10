/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "LANCAMENTO_PAGFUNC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LancamentoPagfunc.findAll", query = "SELECT l FROM LancamentoPagfunc l"),
    @NamedQuery(name = "LancamentoPagfunc.findByCodlanc", query = "SELECT l FROM LancamentoPagfunc l WHERE l.codlanc = :codlanc"),
    @NamedQuery(name = "LancamentoPagfunc.findByData", query = "SELECT l FROM LancamentoPagfunc l WHERE l.data = :data"),
    @NamedQuery(name = "LancamentoPagfunc.findByBonif", query = "SELECT l FROM LancamentoPagfunc l WHERE l.bonif = :bonif")})
public class LancamentoPagfunc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODLANC")
    private Integer codlanc;
    @Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BONIF")
    private Double bonif;
    @JoinColumn(name = "CODFUNC", referencedColumnName = "CODFUNC")
    @ManyToOne
    private Funcionario codfunc;
    @JoinColumn(name = "CODLANC", referencedColumnName = "CODLANC", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Lancamento lancamento;

    public LancamentoPagfunc() {
    }

    public LancamentoPagfunc(Integer codlanc) {
        this.codlanc = codlanc;
    }

    public Integer getCodlanc() {
        return codlanc;
    }

    public void setCodlanc(Integer codlanc) {
        this.codlanc = codlanc;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getBonif() {
        return bonif;
    }

    public void setBonif(Double bonif) {
        this.bonif = bonif;
    }

    public Funcionario getCodfunc() {
        return codfunc;
    }

    public void setCodfunc(Funcionario codfunc) {
        this.codfunc = codfunc;
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
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
        if (!(object instanceof LancamentoPagfunc)) {
            return false;
        }
        LancamentoPagfunc other = (LancamentoPagfunc) object;
        if ((this.codlanc == null && other.codlanc != null) || (this.codlanc != null && !this.codlanc.equals(other.codlanc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LancamentoPagfunc[ codlanc=" + codlanc + " ]";
    }
    
}
