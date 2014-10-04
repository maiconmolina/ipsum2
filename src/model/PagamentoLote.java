/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maicon
 */
@Entity
@Table(name = "PAGAMENTO_LOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagamentoLote.findAll", query = "SELECT p FROM PagamentoLote p"),
    @NamedQuery(name = "PagamentoLote.findByCodpaglote", query = "SELECT p FROM PagamentoLote p WHERE p.codpaglote = :codpaglote"),
    @NamedQuery(name = "PagamentoLote.findByDatpag", query = "SELECT p FROM PagamentoLote p WHERE p.datpag = :datpag")})
public class PagamentoLote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODPAGLOTE")
    private Integer codpaglote;
    @Column(name = "DATPAG")
    @Temporal(TemporalType.DATE)
    private Date datpag;
    @JoinColumn(name = "CODLOTE", referencedColumnName = "CODLOTE")
    @ManyToOne(fetch = FetchType.EAGER)
    private Lote codlote;
    @JoinColumn(name = "TIPOPAG", referencedColumnName = "TIPOPAG")
    @ManyToOne(fetch = FetchType.EAGER)
    private TipoPagamento tipopag;
    @OneToMany(mappedBy = "codpaglote", fetch = FetchType.EAGER)
    private List<Nfe> nfeList;

    public PagamentoLote() {
    }

    public PagamentoLote(Integer codpaglote) {
        this.codpaglote = codpaglote;
    }

    public Integer getCodpaglote() {
        return codpaglote;
    }

    public void setCodpaglote(Integer codpaglote) {
        this.codpaglote = codpaglote;
    }

    public Date getDatpag() {
        return datpag;
    }

    public void setDatpag(Date datpag) {
        this.datpag = datpag;
    }

    public Lote getCodlote() {
        return codlote;
    }

    public void setCodlote(Lote codlote) {
        this.codlote = codlote;
    }

    public TipoPagamento getTipopag() {
        return tipopag;
    }

    public void setTipopag(TipoPagamento tipopag) {
        this.tipopag = tipopag;
    }

    @XmlTransient
    public List<Nfe> getNfeList() {
        return nfeList;
    }

    public void setNfeList(List<Nfe> nfeList) {
        this.nfeList = nfeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codpaglote != null ? codpaglote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagamentoLote)) {
            return false;
        }
        PagamentoLote other = (PagamentoLote) object;
        if ((this.codpaglote == null && other.codpaglote != null) || (this.codpaglote != null && !this.codpaglote.equals(other.codpaglote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ipsum2.PagamentoLote[ codpaglote=" + codpaglote + " ]";
    }
    
}
