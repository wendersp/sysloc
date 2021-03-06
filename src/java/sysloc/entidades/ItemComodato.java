/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author wender
 */
@Entity
@Table(name = "itens_comodato", schema = "sysloc")
public class ItemComodato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @ManyToOne
    @JoinColumn(name = "comodato_id")
    private Comodato comodato;
    @Id
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @Column(name = "quantidade")
    private Double quantidade = 1.0;
    @Column(name = "valor")
    private Double valor;
    @Column(name = "valor_total")
    private Double valorTotal;
    @Column(name = "numero_serie", length = 20)
    private String numeroSerie;
    @Column(name = "patrimonio", length = 20)
    private String patrimonio;
    @Column(name = "observacao", length = 255)
    private String observacao;

    public ItemComodato() {

    }

    public void calcularValotTotal() {
        if (this.quantidade != null && this.valor != null && this.quantidade > 0 && this.valor > 0) {
            this.valorTotal = this.quantidade * this.valor;
        }
    }

    public Comodato getComodato() {
        return comodato;
    }

    public void setComodato(Comodato comodato) {
        this.comodato = comodato;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        this.valor = produto.getValor();
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao.toUpperCase();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.comodato);
        hash = 59 * hash + Objects.hashCode(this.produto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemComodato other = (ItemComodato) obj;
        if (!Objects.equals(this.comodato, other.comodato)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return true;
    }

}
