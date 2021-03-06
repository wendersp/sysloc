/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.controler;

import sysloc.entidades.Cidade;
import sysloc.entidades.Cliente;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sysloc.controler.converter.CidadeConverter;
import sysloc.controler.uteis.Pagina;
import sysloc.controler.uteis.UteisJsf;
import sysloc.sessionbean.CidadeSessionBean;
import sysloc.sessionbean.ClienteSessionBean;

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

    private CidadeConverter cidadeConverter;

    public ClienteMBean() {

    }

    @PostConstruct
    public void init() {
        this.valorPesquisar = "";
    }

    private void inicializarFormCadastro() {
        try {
            this.cidadeConverter = new CidadeConverter();
            this.cidadeConverter.setCidadeSessionBean(cidadeSBean);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
    }

    public List<Cidade> pesquisarCidadeAutoComplete(String nomeCidade) {
        try {
            return this.cidadeSBean.pesquisar(nomeCidade);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
        return null;
    }

    public String botaoNovo() {
        this.inicializarFormCadastro();
        this.cliente = new Cliente();
        return Pagina.CLIENTE_FRM;
    }
    
    public String botaoNavPesquisar() {
        return Pagina.CLIENTE_CONS;
    }

    public void botaoSalvar() {
        this.clienteSBean.salvar(this.cliente);
        this.cliente = new Cliente();
    }

    public void botaoPesquisar() {
        try {
        this.listaClientes = this.clienteSBean.pesquisar(valorPesquisar);
        } catch(Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
    }

    public void botaoExcluir() {
        this.clienteSBean.excluir(this.cliente);
    }

    public String botaoEditar() {
        this.inicializarFormCadastro();
        return Pagina.CLIENTE_FRM;
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

    public CidadeConverter getCidadeConverter() {
        return cidadeConverter;
    }

    public void setCidadeConverter(CidadeConverter cidadeConverter) {
        this.cidadeConverter = cidadeConverter;
    }

    
}
