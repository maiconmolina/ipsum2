/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "SITUACAO_LANCAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SituacaoLancamento.findAll", query = "SELECT s FROM SituacaoLancamento s"),
    @NamedQuery(name = "SituacaoLancamento.findByData", query = "SELECT s FROM SituacaoLancamento s WHERE s.data = :data"),
    @NamedQuery(name = "SituacaoLancamento.findByCodlanc", query = "SELECT s FROM SituacaoLancamento s WHERE s.situacaoLancamentoPK.codlanc = :codlanc"),
    @NamedQuery(name = "SituacaoLancamento.findByCodsit", query = "SELECT s FROM SituacaoLancamento s WHERE s.situacaoLancamentoPK.codsit = :codsit")})
public class SituacaoLancamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SituacaoLancamentoPK situacaoLancamentoPK;
    @Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "CODLANC", referencedColumnName = "CODLANC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Lancamento lancamento;

    public SituacaoLancamento() {
    }

    public SituacaoLancamento(SituacaoLancamentoPK situacaoLancamentoPK) {
        this.situacaoLancamentoPK = situacaoLancamentoPK;
    }

    public SituacaoLancamento(int codlanc, int codsit) {
        this.situacaoLancamentoPK = new SituacaoLancamentoPK(codlanc, codsit);
    }

    public SituacaoLancamentoPK getSituacaoLancamentoPK() {
        return situacaoLancamentoPK;
    }

    public void setSituacaoLancamentoPK(SituacaoLancamentoPK situacaoLancamentoPK) {
        this.situacaoLancamentoPK = situacaoLancamentoPK;
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
        hash += (situacaoLancamentoPK != null ? situacaoLancamentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SituacaoLancamento)) {
            return false;
        }
        SituacaoLancamento other = (SituacaoLancamento) object;
        if ((this.situacaoLancamentoPK == null && other.situacaoLancamentoPK != null) || (this.situacaoLancamentoPK != null && !this.situacaoLancamentoPK.equals(other.situacaoLancamentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SituacaoLancamento[ situacaoLancamentoPK=" + situacaoLancamentoPK + " ]";
    }
    
}
