/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import entidades.Cidade;
import entidades.Cliente;
import entidades.Fornecedor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessionbean.CidadeSessionBean;
import sessionbean.ClienteSessionBean;
import sessionbean.FornecedorSessionBean;

/**
 *
 * @author wender
 */
@Named(value = "clienteMBean")
@SessionScoped
public class ClienteMBean implements Serializable {

    @EJB
    private ClienteSessionBean clienteSBean;
    @EJB
    private CidadeSessionBean cidadeSBean;

    private Cliente cliente;

    private List<Cliente> listaClientes;

    private List<Cidade> listaCidades;   
    
    private String valorPesquisar;

    public ClienteMBean() {

    }

    @PostConstruct
    public void init() {
        this.valorPesquisar = "";
    }

    private void inicializarFormCadastro() {
        this.listaCidades = this.cidadeSBean.pesquisar("");        
    }

    public String botaoNovo() {
        this.inicializarFormCadastro();
        this.cliente = new Cliente();
        return "cadCliente?faces-redirect=true";
    }

    public void botaoSalvar() {
        this.clienteSBean.salvar(this.cliente);
        this.cliente = new Cliente();
    }

    public void botaoPesquisar() {
        this.listaClientes = this.clienteSBean.pesquisar(valorPesquisar);
    }

    public void botaoExcluir() {
        this.clienteSBean.excluir(this.cliente);
    }

    public String botaoEditar() {
        this.inicializarFormCadastro();
        return "cadCliente?faces-redirect=true";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Cidade> getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(List<Cidade> listaCidades) {
        this.listaCidades = listaCidades;
    }

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }



   

}
