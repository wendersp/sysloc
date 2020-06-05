/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author wender
 */
@Entity
@Table(name = "produto", schema = "sysloc")
@NamedQueries({
    @NamedQuery(
            name = "Produto.findByNome",
            query = "SELECT p FROM Produto p WHERE p.nome LIKE :nome"
    )
    ,
    @NamedQuery(
            name = "Produto.findByGrupo",
            query = "SELECT p FROM Produto p WHERE p.grupo = :grupo"
    )
})
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", length = 200, nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;
    @ManyToOne
    @JoinColumn(name = "unidade_medida_id")
    private UnidadeMedida unidadeMedida;
    @Column(name = "estoque")
    private Double estoque;
    @Column(name = "valor")
    private Double valor;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(schema = "sysloc", name = "produto_fornecedor",
            joinColumns = {
                @JoinColumn(name = "produto_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "fornecedor_id")
            }
    )
    private List<Fornecedor> listaFornecedores;

    public Produto() {

    }

    public void addFornecedor(Fornecedor fornecedor) {
        if (this.listaFornecedores == null) {
            this.listaFornecedores = new ArrayList<>();
        }
        this.listaFornecedores.add(fornecedor);
    }

    public void removerFornecedor(Fornecedor fornecedor) {
        if (this.listaFornecedores != null) {
            if (this.listaFornecedores.contains(fornecedor)) {
                this.listaFornecedores.remove(fornecedor);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Double getEstoque() {
        return estoque;
    }

    public void setEstoque(Double estoque) {
        this.estoque = estoque;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Fornecedor> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
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
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Produto[ id=" + id + " ]";
    }

}
