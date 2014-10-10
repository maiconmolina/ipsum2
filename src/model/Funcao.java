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
@Table(name = "FUNCAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcao.findAll", query = "SELECT f FROM Funcao f"),
    @NamedQuery(name = "Funcao.findByCodfuncao", query = "SELECT f FROM Funcao f WHERE f.codfuncao = :codfuncao"),
    @NamedQuery(name = "Funcao.findByAtivo", query = "SELECT f FROM Funcao f WHERE f.ativo = :ativo")})
public class Funcao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODFUNCAO")
    private Integer codfuncao;
    @Column(name = "ATIVO")
    private Short ativo;
    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;
    @OneToMany(mappedBy = "codfuncao")
    private List<Funcionario> funcionarioList;

    public Funcao() {
    }

    public Funcao(Integer codfuncao) {
        this.codfuncao = codfuncao;
    }

    public Integer getCodfuncao() {
        return codfuncao;
    }

    public void setCodfuncao(Integer codfuncao) {
        this.codfuncao = codfuncao;
    }

    public Short getAtivo() {
        return ativo;
    }

    public void setAtivo(Short ativo) {
        this.ativo = ativo;
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
        hash += (codfuncao != null ? codfuncao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcao)) {
            return false;
        }
        Funcao other = (Funcao) object;
        if ((this.codfuncao == null && other.codfuncao != null) || (this.codfuncao != null && !this.codfuncao.equals(other.codfuncao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Funcao[ codfuncao=" + codfuncao + " ]";
    }
    
}
