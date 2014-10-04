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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "FUNCIONARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f"),
    @NamedQuery(name = "Funcionario.findByCodfunc", query = "SELECT f FROM Funcionario f WHERE f.codfunc = :codfunc"),
    @NamedQuery(name = "Funcionario.findByAtivo", query = "SELECT f FROM Funcionario f WHERE f.ativo = :ativo"),
    @NamedQuery(name = "Funcionario.findByDatanasc", query = "SELECT f FROM Funcionario f WHERE f.datanasc = :datanasc"),
    @NamedQuery(name = "Funcionario.findBySalario", query = "SELECT f FROM Funcionario f WHERE f.salario = :salario"),
    @NamedQuery(name = "Funcionario.findByTemporario", query = "SELECT f FROM Funcionario f WHERE f.temporario = :temporario")})
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODFUNC")
    private Integer codfunc;
    @Lob
    @Column(name = "NOME")
    private String nome;
    @Column(name = "ATIVO")
    private Boolean ativo;
    @Lob
    @Column(name = "CPF")
    private String cpf;
    @Column(name = "DATANASC")
    @Temporal(TemporalType.DATE)
    private Date datanasc;
    @Lob
    @Column(name = "ENDERECO")
    private String endereco;
    @Lob
    @Column(name = "RG")
    private String rg;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "SALARIO")
    private Double salario;
    @Lob
    @Column(name = "TELEFONE")
    private String telefone;
    @Column(name = "TEMPORARIO")
    private Boolean temporario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionario", fetch = FetchType.EAGER)
    private List<FuncionarioDoLote> funcionarioDoLoteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionario", fetch = FetchType.EAGER)
    private List<ProducaoDiaria> producaoDiariaList;
    @JoinColumn(name = "CODFUNCAO", referencedColumnName = "CODFUNCAO")
    @ManyToOne(fetch = FetchType.EAGER)
    private Funcao codfuncao;
    @JoinColumn(name = "CODHAB", referencedColumnName = "CODHAB")
    @ManyToOne(fetch = FetchType.EAGER)
    private Habilidade codhab;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "funcionario", fetch = FetchType.EAGER)
    private Usuario usuario;
    @OneToMany(mappedBy = "codfunc", fetch = FetchType.EAGER)
    private List<LancamentoPagfunc> lancamentoPagfuncList;

    public Funcionario() {
    }

    public Funcionario(Integer codfunc) {
        this.codfunc = codfunc;
    }

    public Integer getCodfunc() {
        return codfunc;
    }

    public void setCodfunc(Integer codfunc) {
        this.codfunc = codfunc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getTemporario() {
        return temporario;
    }

    public void setTemporario(Boolean temporario) {
        this.temporario = temporario;
    }

    @XmlTransient
    public List<FuncionarioDoLote> getFuncionarioDoLoteList() {
        return funcionarioDoLoteList;
    }

    public void setFuncionarioDoLoteList(List<FuncionarioDoLote> funcionarioDoLoteList) {
        this.funcionarioDoLoteList = funcionarioDoLoteList;
    }

    @XmlTransient
    public List<ProducaoDiaria> getProducaoDiariaList() {
        return producaoDiariaList;
    }

    public void setProducaoDiariaList(List<ProducaoDiaria> producaoDiariaList) {
        this.producaoDiariaList = producaoDiariaList;
    }

    public Funcao getCodfuncao() {
        return codfuncao;
    }

    public void setCodfuncao(Funcao codfuncao) {
        this.codfuncao = codfuncao;
    }

    public Habilidade getCodhab() {
        return codhab;
    }

    public void setCodhab(Habilidade codhab) {
        this.codhab = codhab;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<LancamentoPagfunc> getLancamentoPagfuncList() {
        return lancamentoPagfuncList;
    }

    public void setLancamentoPagfuncList(List<LancamentoPagfunc> lancamentoPagfuncList) {
        this.lancamentoPagfuncList = lancamentoPagfuncList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codfunc != null ? codfunc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.codfunc == null && other.codfunc != null) || (this.codfunc != null && !this.codfunc.equals(other.codfunc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ipsum2.Funcionario[ codfunc=" + codfunc + " ]";
    }
    
}
