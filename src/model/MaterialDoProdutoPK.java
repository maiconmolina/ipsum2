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
public class MaterialDoProdutoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CODMAT")
    private int codmat;
    @Basic(optional = false)
    @Column(name = "CODPROD")
    private int codprod;

    public MaterialDoProdutoPK() {
    }

    public MaterialDoProdutoPK(int codmat, int codprod) {
        this.codmat = codmat;
        this.codprod = codprod;
    }

    public int getCodmat() {
        return codmat;
    }

    public void setCodmat(int codmat) {
        this.codmat = codmat;
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
        hash += (int) codmat;
        hash += (int) codprod;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialDoProdutoPK)) {
            return false;
        }
        MaterialDoProdutoPK other = (MaterialDoProdutoPK) object;
        if (this.codmat != other.codmat) {
            return false;
        }
        if (this.codprod != other.codprod) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MaterialDoProdutoPK[ codmat=" + codmat + ", codprod=" + codprod + " ]";
    }
    
}
