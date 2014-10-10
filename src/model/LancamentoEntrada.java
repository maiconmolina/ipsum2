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
@Table(name = "LANCAMENTO_ENTRADA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LancamentoEntrada.findAll", query = "SELECT l FROM LancamentoEntrada l"),
    @NamedQuery(name = "LancamentoEntrada.findByCodlanc", query = "SELECT l FROM LancamentoEntrada l WHERE l.codlanc = :codlanc"),
    @NamedQuery(name = "LancamentoEntrada.findByData", query = "SELECT l FROM LancamentoEntrada l WHERE l.data = :data")})
public class LancamentoEntrada implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODLANC")
    private Integer codlanc;
    @Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "CODLANC", referencedColumnName = "CODLANC", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Lancamento lancamento;

    public LancamentoEntrada() {
    }

    public LancamentoEntrada(Integer codlanc) {
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
        if (!(object instanceof LancamentoEntrada)) {
            return false;
        }
        LancamentoEntrada other = (LancamentoEntrada) object;
        if ((this.codlanc == null && other.codlanc != null) || (this.codlanc != null && !this.codlanc.equals(other.codlanc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.LancamentoEntrada[ codlanc=" + codlanc + " ]";
    }
    
}
