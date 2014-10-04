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
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "CONTAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contas.findAll", query = "SELECT c FROM Contas c"),
    @NamedQuery(name = "Contas.findByCodconta", query = "SELECT c FROM Contas c WHERE c.codconta = :codconta"),
    @NamedQuery(name = "Contas.findByTipo", query = "SELECT c FROM Contas c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Contas.findByData", query = "SELECT c FROM Contas c WHERE c.data = :data")})
public class Contas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODCONTA")
    private Integer codconta;
    @Column(name = "TIPO")
    private Boolean tipo;
    @Column(name = "DATA")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;

    public Contas() {
    }

    public Contas(Integer codconta) {
        this.codconta = codconta;
    }

    public Integer getCodconta() {
        return codconta;
    }

    public void setCodconta(Integer codconta) {
        this.codconta = codconta;
    }

    public Boolean getTipo() {
        return tipo;
    }

    public void setTipo(Boolean tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codconta != null ? codconta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contas)) {
            return false;
        }
        Contas other = (Contas) object;
        if ((this.codconta == null && other.codconta != null) || (this.codconta != null && !this.codconta.equals(other.codconta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ipsum2.Contas[ codconta=" + codconta + " ]";
    }
    
}
