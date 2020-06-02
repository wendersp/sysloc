/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author wender
 */
@Entity
@Table(name = "fornecedor", schema = "sysloc")
@NamedQueries({
    @NamedQuery(
            name = "Fornecedor.findByNomeFantasia",
            query = "SELECT f FROM Fornecedor f WHERE f.nomeFantasia LIKE :nomeFantasia"
    ),
    @NamedQuery(
            name = "Fornecedor.findByRazaoSocial",
            query = "SELECT f FROM Fornecedor f WHERE f.razaoSocial LIKE :razaoSocial"
    ),
    @NamedQuery(
            name = "Fornecedor.findByCnpj",
            query = "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj"
    ),
    @NamedQuery(
            name = "Fornecedor.findByCidade",
            query = "SELECT f FROM Fornecedor f WHERE f.cidade = :cidade"
    )
})
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_fantasia", length = 150, nullable = false)
    private String nomeFantasia;
    @Column(name = "razao_social", length = 150, nullable = false)
    private String razaoSocial;
    @Column(name = "cnpj", length = 18, nullable = false, unique = true)
    private String cnpj;
    @Column(name = "telefone", length = 15)
    private String telefone;
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
    @Column(name = "bairro", length = 100)
    private String bairro;
    @Column(name = "endereco", length = 200)
    private String endereco;
    @Column(name = "cep", length = 10)
    private String cep;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

   

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Fornecedor[ id=" + id + " ]";
    }

}
