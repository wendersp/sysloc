/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author wender
 */
@Entity
@Table(name = "comodato", schema = "sysloc")
public class Comodato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    @Column(name = "data_contrato", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataContrato;
    @Column(name = "data_vencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Column(name = "valor_total")
    private Double valorTotal;
    @Column(name = "osbservacao", length = 255)
    private String observacao;
    @OneToMany(mappedBy = "comodato", cascade = CascadeType.ALL )
    private List<ItemComodato> itens;

    public Comodato() {

    }

    public void adicionarItem(ItemComodato itemComodato) {
        if (this.itens == null) {
            this.itens = new ArrayList<>();
        }
        if (itemComodato != null) {
            itemComodato.setComodato(this);
            this.itens.add(itemComodato);
        }
    }

    public void removerItens(ItemComodato itemComodato) {
        if (itemComodato != null && this.itens != null && this.itens.contains(itemComodato)) {
            this.itens.remove(itemComodato);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   

    public Date getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(Date dataContrato) {
        this.dataContrato = dataContrato;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<ItemComodato> getItens() {
        return itens;
    }

    public void setItens(List<ItemComodato> itens) {
        this.itens = itens;
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
        if (!(object instanceof Comodato)) {
            return false;
        }
        Comodato other = (Comodato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Comodato[ id=" + id + " ]";
    }

}
