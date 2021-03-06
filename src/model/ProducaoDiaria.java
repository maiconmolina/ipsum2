/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis
 */
@Entity
@Table(name = "PRODUCAO_DIARIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProducaoDiaria.findAll", query = "SELECT p FROM ProducaoDiaria p"),
    @NamedQuery(name = "ProducaoDiaria.findByQtde", query = "SELECT p FROM ProducaoDiaria p WHERE p.qtde = :qtde"),
    @NamedQuery(name = "ProducaoDiaria.findByCodprod", query = "SELECT p FROM ProducaoDiaria p WHERE p.producaoDiariaPK.codprod = :codprod"),
    @NamedQuery(name = "ProducaoDiaria.findByCodfunc", query = "SELECT p FROM ProducaoDiaria p WHERE p.producaoDiariaPK.codfunc = :codfunc"),
    @NamedQuery(name = "ProducaoDiaria.findByCodlote", query = "SELECT p FROM ProducaoDiaria p WHERE p.producaoDiariaPK.codlote = :codlote"),
    @NamedQuery(name = "ProducaoDiaria.findByDataprod", query = "SELECT p FROM ProducaoDiaria p WHERE p.producaoDiariaPK.dataprod = :dataprod")})
public class ProducaoDiaria implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProducaoDiariaPK producaoDiariaPK;
    @Lob
    @Column(name = "OBS")
    private String obs;
    @Column(name = "QTDE")
    private Integer qtde;
    @JoinColumn(name = "CODFUNC", referencedColumnName = "CODFUNC", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Funcionario funcionario;
    @JoinColumn(name = "CODLOTE", referencedColumnName = "CODLOTE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Lote lote;
    @JoinColumn(name = "CODPROD", referencedColumnName = "CODPROD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Produto produto;

    public ProducaoDiaria() {
    }

    public ProducaoDiaria(ProducaoDiariaPK producaoDiariaPK) {
        this.producaoDiariaPK = producaoDiariaPK;
    }

    public ProducaoDiaria(int codprod, int codfunc, int codlote, Date dataprod) {
        this.producaoDiariaPK = new ProducaoDiariaPK(codprod, codfunc, codlote, dataprod);
    }

    public ProducaoDiariaPK getProducaoDiariaPK() {
        return producaoDiariaPK;
    }

    public void setProducaoDiariaPK(ProducaoDiariaPK producaoDiariaPK) {
        this.producaoDiariaPK = producaoDiariaPK;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (producaoDiariaPK != null ? producaoDiariaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProducaoDiaria)) {
            return false;
        }
        ProducaoDiaria other = (ProducaoDiaria) object;
        if ((this.producaoDiariaPK == null && other.producaoDiariaPK != null) || (this.producaoDiariaPK != null && !this.producaoDiariaPK.equals(other.producaoDiariaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.ProducaoDiaria[ producaoDiariaPK=" + producaoDiariaPK + " ]";
    }
    
}
