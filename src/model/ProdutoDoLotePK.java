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
public class ProdutoDoLotePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CODPROD")
    private int codprod;
    @Basic(optional = false)
    @Column(name = "CODLOTE")
    private int codlote;

    public ProdutoDoLotePK() {
    }

    public ProdutoDoLotePK(int codprod, int codlote) {
        this.codprod = codprod;
        this.codlote = codlote;
    }

    public int getCodprod() {
        return codprod;
    }

    public void setCodprod(int codprod) {
        this.codprod = codprod;
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
        hash += (int) codprod;
        hash += (int) codlote;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoDoLotePK)) {
            return false;
        }
        ProdutoDoLotePK other = (ProdutoDoLotePK) object;
        if (this.codprod != other.codprod) {
            return false;
        }
        if (this.codlote != other.codlote) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ProdutoDoLotePK[ codprod=" + codprod + ", codlote=" + codlote + " ]";
    }
    
}
