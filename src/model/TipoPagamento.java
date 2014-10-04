/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maicon
 */
@Entity
@Table(name = "TIPO_PAGAMENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPagamento.findAll", query = "SELECT t FROM TipoPagamento t"),
    @NamedQuery(name = "TipoPagamento.findByTipopag", query = "SELECT t FROM TipoPagamento t WHERE t.tipopag = :tipopag"),
    @NamedQuery(name = "TipoPagamento.findByAtivo", query = "SELECT t FROM TipoPagamento t WHERE t.ativo = :ativo")})
public class TipoPagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TIPOPAG")
    private Integer tipopag;
    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "ATIVO")
    private Boolean ativo;
    @OneToMany(mappedBy = "tipopag", fetch = FetchType.EAGER)
    private List<PagamentoLote> pagamentoLoteList;

    public TipoPagamento() {
    }

    public TipoPagamento(Integer tipopag) {
        this.tipopag = tipopag;
    }

    public Integer getTipopag() {
        return tipopag;
    }

    public void setTipopag(Integer tipopag) {
        this.tipopag = tipopag;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public List<PagamentoLote> getPagamentoLoteList() {
        return pagamentoLoteList;
    }

    public void setPagamentoLoteList(List<PagamentoLote> pagamentoLoteList) {
        this.pagamentoLoteList = pagamentoLoteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipopag != null ? tipopag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPagamento)) {
            return false;
        }
        TipoPagamento other = (TipoPagamento) object;
        if ((this.tipopag == null && other.tipopag != null) || (this.tipopag != null && !this.tipopag.equals(other.tipopag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ipsum2.TipoPagamento[ tipopag=" + tipopag + " ]";
    }
    
}
