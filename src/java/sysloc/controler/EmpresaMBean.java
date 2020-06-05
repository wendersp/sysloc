/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.controler;

import sysloc.entidades.Cidade;
import sysloc.entidades.Empresa;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sysloc.controler.uteis.UteisJsf;
import sysloc.sessionbean.CidadeSessionBean;
import sysloc.sessionbean.EmpresaSessionBean;

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
        try {        
            this.listaCidades = this.cidadeSBean.pesquisar("");
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao listar cidade. " + ex.getMessage());
        }
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
