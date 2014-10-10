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
 * @author Luis
 */
@Embeddable
public class ProducaoDiariaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CODPROD")
    private int codprod;
    @Basic(optional = false)
    @Column(name = "CODFUNC")
    private int codfunc;
    @Basic(optional = false)
    @Column(name = "CODLOTE")
    private int codlote;
    @Basic(optional = false)
    @Column(name = "DATAPROD")
    @Temporal(TemporalType.DATE)
    private Date dataprod;

    public ProducaoDiariaPK() {
    }

    public ProducaoDiariaPK(int codprod, int codfunc, int codlote, Date dataprod) {
        this.codprod = codprod;
        this.codfunc = codfunc;
        this.codlote = codlote;
        this.dataprod = dataprod;
    }

    public int getCodprod() {
        return codprod;
    }

    public void setCodprod(int codprod) {
        this.codprod = codprod;
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

    public Date getDataprod() {
        return dataprod;
    }

    public void setDataprod(Date dataprod) {
        this.dataprod = dataprod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codprod;
        hash += (int) codfunc;
        hash += (int) codlote;
        hash += (dataprod != null ? dataprod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProducaoDiariaPK)) {
            return false;
        }
        ProducaoDiariaPK other = (ProducaoDiariaPK) object;
        if (this.codprod != other.codprod) {
            return false;
        }
        if (this.codfunc != other.codfunc) {
            return false;
        }
        if (this.codlote != other.codlote) {
            return false;
        }
        if ((this.dataprod == null && other.dataprod != null) || (this.dataprod != null && !this.dataprod.equals(other.dataprod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ProducaoDiariaPK[ codprod=" + codprod + ", codfunc=" + codfunc + ", codlote=" + codlote + ", dataprod=" + dataprod + " ]";
    }
    
}
