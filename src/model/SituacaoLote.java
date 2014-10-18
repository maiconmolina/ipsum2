/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "SITUACAO_LOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SituacaoLote.findAll", query = "SELECT s FROM SituacaoLote s"),
    @NamedQuery(name = "SituacaoLote.findBySitlote", query = "SELECT s FROM SituacaoLote s WHERE s.sitlote = :sitlote"),
    @NamedQuery(name = "SituacaoLote.findByAtivo", query = "SELECT s FROM SituacaoLote s WHERE s.ativo = :ativo")})
public class SituacaoLote implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SITLOTE")
    private Integer sitlote;
    @Column(name = "ATIVO")
    private Short ativo;
    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;
    @OneToMany(mappedBy = "sitlote")
    private List<Lote> loteList;

    public SituacaoLote() {
    }

    public SituacaoLote(Integer sitlote) {
        this.sitlote = sitlote;
    }

    public Integer getSitlote() {
        return sitlote;
    }

    public void setSitlote(Integer sitlote) {
        Integer oldSitlote = this.sitlote;
        this.sitlote = sitlote;
        changeSupport.firePropertyChange("sitlote", oldSitlote, sitlote);
    }

    public Short getAtivo() {
        return ativo;
    }

    public void setAtivo(Short ativo) {
        Short oldAtivo = this.ativo;
        this.ativo = ativo;
        changeSupport.firePropertyChange("ativo", oldAtivo, ativo);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    @XmlTransient
    public List<Lote> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<Lote> loteList) {
        this.loteList = loteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sitlote != null ? sitlote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SituacaoLote)) {
            return false;
        }
        SituacaoLote other = (SituacaoLote) object;
        if ((this.sitlote == null && other.sitlote != null) || (this.sitlote != null && !this.sitlote.equals(other.sitlote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
