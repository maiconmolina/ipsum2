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
public class FuncionarioDoLotePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CODFUNC")
    private int codfunc;
    @Basic(optional = false)
    @Column(name = "CODLOTE")
    private int codlote;

    public FuncionarioDoLotePK() {
    }

    public FuncionarioDoLotePK(int codfunc, int codlote) {
        this.codfunc = codfunc;
        this.codlote = codlote;
    }

    public int getCodfunc() {
        return codfunc;
    }

    public void setCodfunc(int codfunc) {
        this.codfunc = codfunc;
    }

    public int getCodlote() {
        return codlote;
    }

    public void setCodlote(int codlote) {
        this.codlote = codlote;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codfunc;
        hash += (int) codlote;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuncionarioDoLotePK)) {
            return false;
        }
        FuncionarioDoLotePK other = (FuncionarioDoLotePK) object;
        if (this.codfunc != other.codfunc) {
            return false;
        }
        if (this.codlote != other.codlote) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.FuncionarioDoLotePK[ codfunc=" + codfunc + ", codlote=" + codlote + " ]";
    }
    
}
