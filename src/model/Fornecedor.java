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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "FORNECEDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f"),
    @NamedQuery(name = "Fornecedor.findByCodfornec", query = "SELECT f FROM Fornecedor f WHERE f.codfornec = :codfornec"),
    @NamedQuery(name = "Fornecedor.findByNumero", query = "SELECT f FROM Fornecedor f WHERE f.numero = :numero")})
public class Fornecedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODFORNEC")
    private Integer codfornec;
    @Lob
    @Column(name = "CEP")
    private String cep;
    @Lob
    @Column(name = "CIDADE")
    private String cidade;
    @Lob
    @Column(name = "CNPJ")
    private String cnpj;
    @Lob
    @Column(name = "EMAIL")
    private String email;
    @Lob
    @Column(name = "ENDERECO")
    private String endereco;
    @Lob
    @Column(name = "FANTASIA")
    private String fantasia;
    @Column(name = "NUMERO")
    private Integer numero;
    @Lob
    @Column(name = "RAZAO")
    private String razao;
    @Lob
    @Column(name = "TELEFONE")
    private String telefone;
    @OneToMany(mappedBy = "codfornec")
    private List<Nfe> nfeList;
    @OneToMany(mappedBy = "codfornec")
    private List<Lote> loteList;
    @OneToMany(mappedBy = "codfornec")
    private List<LancamentoRecforn> lancamentoRecfornList;
    @OneToMany(mappedBy = "codfornec")
    private List<Material> materialList;
    @JoinColumn(name = "CODUF", referencedColumnName = "CODUF")
    @ManyToOne
    private Uf coduf;

    public Fornecedor() {
    }

    public Fornecedor(Integer codfornec) {
        this.codfornec = codfornec;
    }

    public Integer getCodfornec() {
        return codfornec;
    }

    public void setCodfornec(Integer codfornec) {
        this.codfornec = codfornec;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @XmlTransient
    public List<Nfe> getNfeList() {
        return nfeList;
    }

    public void setNfeList(List<Nfe> nfeList) {
        this.nfeList = nfeList;
    }

    @XmlTransient
    public List<Lote> getLoteList() {
        return loteList;
    }

    public void setLoteList(List<Lote> loteList) {
        this.loteList = loteList;
    }

    @XmlTransient
    public List<LancamentoRecforn> getLancamentoRecfornList() {
        return lancamentoRecfornList;
    }

    public void setLancamentoRecfornList(List<LancamentoRecforn> lancamentoRecfornList) {
        this.lancamentoRecfornList = lancamentoRecfornList;
    }

    @XmlTransient
    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }

    public Uf getCoduf() {
        return coduf;
    }

    public void setCoduf(Uf coduf) {
        this.coduf = coduf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codfornec != null ? codfornec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.codfornec == null && other.codfornec != null) || (this.codfornec != null && !this.codfornec.equals(other.codfornec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codfornec.toString() + " " + razao;
    }
    
}
