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
public class MaterialDoProdutoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CODPROD")
    private int codprod;
    @Basic(optional = false)
    @Column(name = "CODMAT")
    private int codmat;

    public MaterialDoProdutoPK() {
    }

    public MaterialDoProdutoPK(int codprod, int codmat) {
        this.codprod = codprod;
        this.codmat = codmat;
    }

    public int getCodprod() {
        return codprod;
    }

    public void setCodprod(int codprod) {
        this.codprod = codprod;
    }

    public int getCodmat() {
        return codmat;
    }

    public void setCodmat(int codmat) {
        this.codmat = codmat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codprod;
        hash += (int) codmat;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialDoProdutoPK)) {
            return false;
        }
        MaterialDoProdutoPK other = (MaterialDoProdutoPK) object;
        if (this.codprod != other.codprod) {
            return false;
        }
        if (this.codmat != other.codmat) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ipsum2.MaterialDoProdutoPK[ codprod=" + codprod + ", codmat=" + codmat + " ]";
    }
    
}
