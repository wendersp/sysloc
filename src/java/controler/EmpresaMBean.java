/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import entidades.Cidade;
import entidades.Empresa;
import entidades.Fornecedor;
import entidades.Grupo;
import entidades.Produto;
import entidades.UnidadeMedida;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessionbean.CidadeSessionBean;
import sessionbean.EmpresaSessionBean;
import sessionbean.FornecedorSessionBean;
import sessionbean.GrupoSessionBean;
import sessionbean.ProdutoSessionBean;
import sessionbean.UnidadeMedidaSessionBean;

/**
 *
 * @author wender
 */
@Named(value = "empresaMBean")
@SessionScoped
public class EmpresaMBean implements Serializable {

    @EJB
    private EmpresaSessionBean empresaSBean;
    @EJB
    private CidadeSessionBean cidadeSBean;

    private Empresa empresa;

    private List<Empresa> listaEmpresas;

    private List<Cidade> listaCidades;   
    
    private String valorPesquisar;

    public EmpresaMBean() {

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
        this.empresa = new Empresa();
        return "cadEmpresa?faces-redirect=true";
    }

    public void botaoSalvar() {
        this.empresaSBean.salvar(this.empresa);
        this.empresa = new Empresa();
    }

    public void botaoPesquisar() {
        this.listaEmpresas = this.empresaSBean.pesquisar(valorPesquisar);
    }

    public void botaoExcluir() {
        this.empresaSBean.excluir(this.empresa);
    }

    public String botaoEditar() {
        this.inicializarFormCadastro();
        return "cadEmpresa?faces-redirect=true";
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
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
