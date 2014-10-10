/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Luis
 */
@Embeddable
public class SituacaoLancamentoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CODLANC")
    private int codlanc;
    @Basic(optional = false)
    @Column(name = "CODSIT")
    private int codsit;

    public SituacaoLancamentoPK() {
    }

    public SituacaoLancamentoPK(int codlanc, int codsit) {
        this.codlanc = codlanc;
        this.codsit = codsit;
    }

    public int getCodlanc() {
        return codlanc;
    }

    public void setCodlanc(int codlanc) {
        this.codlanc = codlanc;
    }

    public int getCodsit() {
        return codsit;
    }

    public void setCodsit(int codsit) {
        this.codsit = codsit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codlanc;
        hash += (int) codsit;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SituacaoLancamentoPK)) {
            return false;
        }
        SituacaoLancamentoPK other = (SituacaoLancamentoPK) object;
        if (this.codlanc != other.codlanc) {
            return false;
        }
        if (this.codsit != other.codsit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SituacaoLancamentoPK[ codlanc=" + codlanc + ", codsit=" + codsit + " ]";
    }
    
}
