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
@Table(name = "UF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uf.findAll", query = "SELECT u FROM Uf u"),
    @NamedQuery(name = "Uf.findByCoduf", query = "SELECT u FROM Uf u WHERE u.coduf = :coduf")})
public class Uf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODUF")
    private Integer coduf;
    @Lob
    @Column(name = "NOME")
    private String nome;
    @OneToMany(mappedBy = "coduf", fetch = FetchType.EAGER)
    private List<Fornecedor> fornecedorList;

    public Uf() {
    }

    public Uf(Integer coduf) {
        this.coduf = coduf;
    }

    public Integer getCoduf() {
        return coduf;
    }

    public void setCoduf(Integer coduf) {
        this.coduf = coduf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Fornecedor> getFornecedorList() {
        return fornecedorList;
    }

    public void setFornecedorList(List<Fornecedor> fornecedorList) {
        this.fornecedorList = fornecedorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coduf != null ? coduf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uf)) {
            return false;
        }
        Uf other = (Uf) object;
        if ((this.coduf == null && other.coduf != null) || (this.coduf != null && !this.coduf.equals(other.coduf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ipsum2.Uf[ coduf=" + coduf + " ]";
    }
    
}
