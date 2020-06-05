/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.controler;

import sysloc.entidades.Cidade;
import sysloc.entidades.Fornecedor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sysloc.controler.converter.CidadeConverter;
import sysloc.controler.uteis.UteisJsf;
import sysloc.sessionbean.CidadeSessionBean;
import sysloc.sessionbean.FornecedorSessionBean;

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
   
    private String valorPesquisar;
  
    
    private CidadeConverter cidadeConverter;

    public FornecedorMBean() {

    }

    @PostConstruct
    public void init() {
        this.valorPesquisar = "";
    }

    private void inicializarFormCadastro() {
          this.cidadeConverter = new CidadeConverter();
          this.cidadeConverter.setCidadeSessionBean(cidadeSBean);
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
    
    public List<Cidade> pesquisarCidadeAutoComplete(String valorPesquisar) {
        try {
        return this.cidadeSBean.pesquisar(valorPesquisar);
        } catch(Exception ex) {
            UteisJsf.addMensagemErro("Erro ao pesquisar Cidade - " + ex.getMessage());
        }
        return null;
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
