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
 * @author Maicon
 */
@Embeddable
public class ProdutoDoLotePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CODLOTE")
    private int codlote;
    @Basic(optional = false)
    @Column(name = "CODPROD")
    private int codprod;

    public ProdutoDoLotePK() {
    }

    public ProdutoDoLotePK(int codlote, int codprod) {
        this.codlote = codlote;
        this.codprod = codprod;
    }

    public int getCodlote() {
        return codlote;
    }

    public void setCodlote(int codlote) {
        this.codlote = codlote;
    }

    public int getCodprod() {
        return codprod;
    }

    public void setCodprod(int codprod) {
        this.codprod = codprod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codlote;
        hash += (int) codprod;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutoDoLotePK)) {
            return false;
        }
        ProdutoDoLotePK other = (ProdutoDoLotePK) object;
        if (this.codlote != other.codlote) {
            return false;
        }
        if (this.codprod != other.codprod) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ipsum2.ProdutoDoLotePK[ codlote=" + codlote + ", codprod=" + codprod + " ]";
    }
    
}
