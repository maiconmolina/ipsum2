/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Util.Util;
import controller.UsuarioJpaController;
import enuns.Permissoes;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
 * @author Luis
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

    @Column(name = "ATIVO")
    private Short ativo;

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
    @Column(name = "NOME")
    private String nome;

    @Lob
    @Column(name = "RG")
    private String rg;

    @Column(name = "SALARIO")
    private Double salario;

    @Lob
    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "TEMPORARIO")
    private Short temporario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionario")
    private List<FuncionarioDoLote> funcionarioDoLoteList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionario")
    private List<ProducaoDiaria> producaoDiariaList;

    @JoinColumn(name = "CODFUNCAO", referencedColumnName = "CODFUNCAO")
    @ManyToOne
    private Funcao codfuncao;

    @JoinColumn(name = "CODHAB", referencedColumnName = "CODHAB")
    @ManyToOne
    private Habilidade codhab;

    @OneToMany(mappedBy = "codfunc")
    private List<LancamentoPagfunc> lancamentoPagfuncList;

    @Column(name = "usuario")
    private Integer codUsuario;

    private static List<Permissoes> permissoesFuncionarioLogado = null;

    public Funcionario() {
        this.ativo = 1;
        this.codUsuario = null;
    }

    public Funcionario(Integer codfunc) {
        this.codfunc = codfunc;
    }

    public Funcionario(Funcionario c) {
        this.ativo = c.ativo;
        this.codUsuario = c.codUsuario;
        this.codfunc = c.codfunc;
        this.codfuncao = c.codfuncao;
        this.codhab = c.codhab;
        this.cpf = c.cpf;
        this.datanasc = c.datanasc;
        this.endereco = c.endereco;
        this.funcionarioDoLoteList = c.funcionarioDoLoteList;
        this.lancamentoPagfuncList = c.lancamentoPagfuncList;
        this.nome = c.nome;
        this.producaoDiariaList = c.producaoDiariaList;
        this.rg = c.rg;
        this.salario = c.salario;
        this.telefone = c.telefone;
        this.temporario = c.temporario;
    }

    public Integer getCodfunc() {
        return codfunc;
    }

    public void setCodfunc(Integer codfunc) {
        this.codfunc = codfunc;
    }

    public Short getAtivo() {
        return ativo;
    }

    public void setAtivo(Short ativo) {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception {
        if (Util.isNullOrEmpty(nome)) {
            throw new Exception("Nome não pode ser vazio");
        }
        this.nome = nome;
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

    public void setSalario(String salario) throws Exception {
        try {
            this.salario = Double.parseDouble(salario.replace(",", "."));
        } catch (NumberFormatException ex) {
            throw new Exception("Salário inválido.");
        }
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Short getTemporario() {
        return temporario;
    }

    public void setTemporario(Short temporario) {
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
        return (this.codfunc != null || other.codfunc == null) && (this.codfunc == null || this.codfunc.equals(other.codfunc));
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public boolean isAtivo() {
        return ativo != 0;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo ? (short) 1 : 0;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setUsuario(Usuario usuario) throws Exception {
        if (usuario == null || usuario.getCodigo() == null) {
            throw new Exception("Não foi possível salvar o usuário.");
        }
        this.codUsuario = usuario.getCodigo();
    }

    public Usuario getUsuario() throws Exception {
        if (this.codUsuario == null) {
            throw new Exception("Usuário não cadastrado");
        }
        UsuarioJpaController ctr = new UsuarioJpaController();
        return (Usuario) ctr.getEntityManager()
                .createNamedQuery("Usuario.findByCodigo")
                .setParameter("codigo", this.codUsuario)
                .getSingleResult();
    }

    public static void atualizaPermissoes() {
        Funcionario.permissoesFuncionarioLogado = new UsuarioJpaController().getPermissoesUsuario();
    }

    public static boolean permite(Permissoes p) {
        return Funcionario.permissoesFuncionarioLogado.contains(p);
    }
}
