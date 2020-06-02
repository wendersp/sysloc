/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(
            name = "Comodato.findByCliente",
            query = "SELECT c FROM Comodato c WHERE c.empresa = :empresa AND c.cliente = :cliente"
    ),
    @NamedQuery(
            name = "Comodato.findByDataContrato",
            query = "SELECT c FROM Comodato c WHERE c.empresa = :empresa AND c.dataContrato = :dataContrato"
    ),
    @NamedQuery(
            name = "Comodato.findByDataVencimento",
            query = "SELECT c FROM Comodato c WHERE c.empresa = :empresa AND c.dataVencimento = :dataVencimento"
    ),
    @NamedQuery(
            name = "Comodato.findByEmpresa",
            query = "SELECT c FROM Comodato c WHERE c.empresa = :empresa"
    )
})
public class Comodato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
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
    @OneToMany
    private List<ItensComodato> itens;

    public Comodato() {
        this.itens = new ArrayList<>();
    }
    
    public void adicionarItens(ItensComodato itensComodato) {
        ItensComodato item = itensComodato;
        item.setComodato(this);
        this.itens.add(item);
    }
    
    public void removerItens(ItensComodato itensComodato) {
        this.itens.remove(itensComodato);
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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

    public List<ItensComodato> getItens() {
        return itens;
    }

    public void setItens(List<ItensComodato> itens) {
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
