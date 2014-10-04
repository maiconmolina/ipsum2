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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Maicon
 */
@Embeddable
public class ProducaoDiariaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CODLOTE")
    private int codlote;
    @Basic(optional = false)
    @Column(name = "CODPROD")
    private int codprod;
    @Basic(optional = false)
    @Column(name = "DATAPROD")
    @Temporal(TemporalType.DATE)
    private Date dataprod;
    @Basic(optional = false)
    @Column(name = "CODFUNC")
    private int codfunc;

    public ProducaoDiariaPK() {
    }

    public ProducaoDiariaPK(int codlote, int codprod, Date dataprod, int codfunc) {
        this.codlote = codlote;
        this.codprod = codprod;
        this.dataprod = dataprod;
        this.codfunc = codfunc;
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

    public Date getDataprod() {
        return dataprod;
    }

    public void setDataprod(Date dataprod) {
        this.dataprod = dataprod;
    }

    public int getCodfunc() {
        return codfunc;
    }

    public void setCodfunc(int codfunc) {
        this.codfunc = codfunc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codlote;
        hash += (int) codprod;
        hash += (dataprod != null ? dataprod.hashCode() : 0);
        hash += (int) codfunc;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProducaoDiariaPK)) {
            return false;
        }
        ProducaoDiariaPK other = (ProducaoDiariaPK) object;
        if (this.codlote != other.codlote) {
            return false;
        }
        if (this.codprod != other.codprod) {
            return false;
        }
        if ((this.dataprod == null && other.dataprod != null) || (this.dataprod != null && !this.dataprod.equals(other.dataprod))) {
            return false;
        }
        if (this.codfunc != other.codfunc) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ipsum2.ProducaoDiariaPK[ codlote=" + codlote + ", codprod=" + codprod + ", dataprod=" + dataprod + ", codfunc=" + codfunc + " ]";
    }
    
}
