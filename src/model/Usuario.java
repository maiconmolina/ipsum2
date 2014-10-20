/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Util.Util;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByLogin", query = "SELECT u FROM Usuario u WHERE u.login = :login"),
    @NamedQuery(name = "Usuario.findByCodigo", query = "SELECT u FROM Usuario u WHERE u.codigo = :codigo"),
    @NamedQuery(name = "Usuario.findByTipo", query = "SELECT u FROM Usuario u WHERE u.tipo = :tipo")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LOGIN")
    private String login;

    @Lob
    @Column(name = "SENHA")
    private String senha;

    @Column(name = "CODIGO")
    private Integer codigo;

    @Column(name = "TIPO")
    private Integer tipo;

    private static Usuario usuarioLogado;

    public Usuario() {
        this.tipo = 0;
    }

    public Usuario(Class tipo) throws Exception {
        if (tipo.equals(Funcionario.class)) {
            this.tipo = 0;
        } else if (tipo.equals(Fornecedor.class)) {
            this.tipo = 1;
        } else {
            throw new Exception("Usuários só podem ser Funcionários ou Fornecedores");
        }
    }

    public Usuario(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws Exception {
        if (Util.isNullOrEmpty(login)) {
            throw new Exception("Login não pode ser vazio");
        }
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws Exception {
        if (Util.isNullOrEmpty(senha)) {
            throw new Exception("Senha não pode ser vazio");
        }
        this.senha = senha;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Class getTipo() throws Exception {
        if (tipo.equals(0)) {
            return Funcionario.class;
        } else if (tipo.equals(1)) {
            return Fornecedor.class;
        } else {
            throw new Exception("Falha de dados.");
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Usuario[ login=" + login + " ]";
    }

    public static void setUsuarioLogado(Usuario logado) {
        Usuario.usuarioLogado = logado;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

}
