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
import javax.persistence.FetchType;
import javax.persistence.Id;
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
 * @author Maicon
 */
@Entity
@Table(name = "NFE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nfe.findAll", query = "SELECT n FROM Nfe n"),
    @NamedQuery(name = "Nfe.findByCodnfe", query = "SELECT n FROM Nfe n WHERE n.codnfe = :codnfe"),
    @NamedQuery(name = "Nfe.findByDataemi", query = "SELECT n FROM Nfe n WHERE n.dataemi = :dataemi")})
public class Nfe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODNFE")
    private Integer codnfe;
    @Column(name = "DATAEMI")
    @Temporal(TemporalType.DATE)
    private Date dataemi;
    @JoinColumn(name = "CODFORNEC", referencedColumnName = "CODFORNEC")
    @ManyToOne(fetch = FetchType.EAGER)
    private Fornecedor codfornec;
    @JoinColumn(name = "CODPAGLOTE", referencedColumnName = "CODPAGLOTE")
    @ManyToOne(fetch = FetchType.EAGER)
    private PagamentoLote codpaglote;

    public Nfe() {
    }

    public Nfe(Integer codnfe) {
        this.codnfe = codnfe;
    }

    public Integer getCodnfe() {
        return codnfe;
    }

    public void setCodnfe(Integer codnfe) {
        this.codnfe = codnfe;
    }

    public Date getDataemi() {
        return dataemi;
    }

    public void setDataemi(Date dataemi) {
        this.dataemi = dataemi;
    }

    public Fornecedor getCodfornec() {
        return codfornec;
    }

    public void setCodfornec(Fornecedor codfornec) {
        this.codfornec = codfornec;
    }

    public PagamentoLote getCodpaglote() {
        return codpaglote;
    }

    public void setCodpaglote(PagamentoLote codpaglote) {
        this.codpaglote = codpaglote;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codnfe != null ? codnfe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nfe)) {
            return false;
        }
        Nfe other = (Nfe) object;
        if ((this.codnfe == null && other.codnfe != null) || (this.codnfe != null && !this.codnfe.equals(other.codnfe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ipsum2.Nfe[ codnfe=" + codnfe + " ]";
    }
    
}
