/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import entidades.Cidade;
import entidades.Fornecedor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessionbean.CidadeSessionBean;
import sessionbean.FornecedorSessionBean;

/**
 *
 * @author wender
 */
@Named(value = "fornecedorMBean")
@SessionScoped
public class FornecedorMBean implements Serializable {

    @EJB
    private FornecedorSessionBean fornecedorSBean;
    @EJB
    private CidadeSessionBean cidadeSBean;

    private Fornecedor fornecedor;

    private List<Fornecedor> listaFornecedores;

    private List<Cidade> listaCidades;   
    
    private String valorPesquisar;

    public FornecedorMBean() {

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
        this.fornecedor = new Fornecedor();
        return "cadFornecedor?faces-redirect=true";
    }

    public void botaoSalvar() {
        this.fornecedorSBean.salvar(this.fornecedor);
        this.fornecedor = new Fornecedor();
    }

    public void botaoPesquisar() {
        this.listaFornecedores = this.fornecedorSBean.pesquisarPorRazaoSocial(valorPesquisar);
    }

    public void botaoExcluir() {
        this.fornecedorSBean.excluir(this.fornecedor);
    }

    public String botaoEditar() {
        this.inicializarFormCadastro();
        return "cadFornecedor?faces-redirect=true";
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
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
