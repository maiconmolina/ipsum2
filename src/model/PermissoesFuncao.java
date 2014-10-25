/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maicon
 */
@Entity
@Table(name = "PERMISSOES_FUNCAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PermissoesFuncao.findAll", query = "SELECT p FROM PermissoesFuncao p"),
    @NamedQuery(name = "PermissoesFuncao.findByCodperm", query = "SELECT p FROM PermissoesFuncao p WHERE p.codperm = :codperm"),
    @NamedQuery(name = "PermissoesFuncao.findByPermissao", query = "SELECT p FROM PermissoesFuncao p WHERE p.permissao = :permissao"),
    @NamedQuery(name = "PermissoesFuncao.findByCodfuncao", query = "SELECT p FROM PermissoesFuncao p WHERE p.codfuncao = :codfuncao")})
public class PermissoesFuncao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "CODPERM")
    private Integer codperm;

    @Column(name = "PERMISSAO")
    private Integer permissao;

    @Column(name = "CODFUNCAO")
    private Integer codfuncao;

    public PermissoesFuncao() {
    }

    public PermissoesFuncao(Integer codperm) {
        this.codperm = codperm;
    }

    public Integer getCodperm() {
        return codperm;
    }

    public void setCodperm(Integer codperm) {
        this.codperm = codperm;
    }

    public Integer getPermissao() {
        return permissao;
    }

    public void setPermissao(Integer permissao) {
        this.permissao = permissao;
    }

    public Integer getCodfuncao() {
        return codfuncao;
    }

    public void setCodfuncao(Integer codfuncao) {
        this.codfuncao = codfuncao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codperm != null ? codperm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermissoesFuncao)) {
            return false;
        }
        PermissoesFuncao other = (PermissoesFuncao) object;
        if ((this.codperm == null && other.codperm != null) || (this.codperm != null && !this.codperm.equals(other.codperm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PermissoesFuncao[ codperm=" + codperm + " ]";
    }

}
