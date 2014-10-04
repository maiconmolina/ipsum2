/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maicon
 */
@Entity
@Table(name = "FUNCIONARIO_DO_LOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FuncionarioDoLote.findAll", query = "SELECT f FROM FuncionarioDoLote f"),
    @NamedQuery(name = "FuncionarioDoLote.findByCodfunc", query = "SELECT f FROM FuncionarioDoLote f WHERE f.funcionarioDoLotePK.codfunc = :codfunc"),
    @NamedQuery(name = "FuncionarioDoLote.findByCodlote", query = "SELECT f FROM FuncionarioDoLote f WHERE f.funcionarioDoLotePK.codlote = :codlote"),
    @NamedQuery(name = "FuncionarioDoLote.findByAtivo", query = "SELECT f FROM FuncionarioDoLote f WHERE f.ativo = :ativo")})
public class FuncionarioDoLote implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FuncionarioDoLotePK funcionarioDoLotePK;
    @Column(name = "ATIVO")
    private Boolean ativo;
    @JoinColumn(name = "CODFUNC", referencedColumnName = "CODFUNC", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Funcionario funcionario;
    @JoinColumn(name = "CODLOTE", referencedColumnName = "CODLOTE", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lote lote;

    public FuncionarioDoLote() {
    }

    public FuncionarioDoLote(FuncionarioDoLotePK funcionarioDoLotePK) {
        this.funcionarioDoLotePK = funcionarioDoLotePK;
    }

    public FuncionarioDoLote(int codfunc, int codlote) {
        this.funcionarioDoLotePK = new FuncionarioDoLotePK(codfunc, codlote);
    }

    public FuncionarioDoLotePK getFuncionarioDoLotePK() {
        return funcionarioDoLotePK;
    }

    public void setFuncionarioDoLotePK(FuncionarioDoLotePK funcionarioDoLotePK) {
        this.funcionarioDoLotePK = funcionarioDoLotePK;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funcionarioDoLotePK != null ? funcionarioDoLotePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuncionarioDoLote)) {
            return false;
        }
        FuncionarioDoLote other = (FuncionarioDoLote) object;
        if ((this.funcionarioDoLotePK == null && other.funcionarioDoLotePK != null) || (this.funcionarioDoLotePK != null && !this.funcionarioDoLotePK.equals(other.funcionarioDoLotePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ipsum2.FuncionarioDoLote[ funcionarioDoLotePK=" + funcionarioDoLotePK + " ]";
    }
    
}
