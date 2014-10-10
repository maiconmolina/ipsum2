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
 * @author Luis
 */
@Entity
@Table(name = "HABILIDADE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habilidade.findAll", query = "SELECT h FROM Habilidade h"),
    @NamedQuery(name = "Habilidade.findByCodhab", query = "SELECT h FROM Habilidade h WHERE h.codhab = :codhab")})
public class Habilidade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODHAB")
    private Integer codhab;
    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;
    @OneToMany(mappedBy = "codhab")
    private List<Funcionario> funcionarioList;

    public Habilidade() {
    }

    public Habilidade(Integer codhab) {
        this.codhab = codhab;
    }

    public Integer getCodhab() {
        return codhab;
    }

    public void setCodhab(Integer codhab) {
        this.codhab = codhab;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<Funcionario> getFuncionarioList() {
        return funcionarioList;
    }

    public void setFuncionarioList(List<Funcionario> funcionarioList) {
        this.funcionarioList = funcionarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codhab != null ? codhab.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habilidade)) {
            return false;
        }
        Habilidade other = (Habilidade) object;
        if ((this.codhab == null && other.codhab != null) || (this.codhab != null && !this.codhab.equals(other.codhab))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Habilidade[ codhab=" + codhab + " ]";
    }
    
}
