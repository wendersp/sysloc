/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.controler;


import sysloc.entidades.Cidade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sysloc.controler.uteis.UteisJsf;
import sysloc.sessionbean.CidadeSessionBean;


/**
 *
 * @author wender
 */
@Named(value = "cidadeMBean")
@SessionScoped
public class CidadeMBean implements Serializable {

    @EJB
    private CidadeSessionBean cidadeSBean;
    
    private Cidade cidade;
    
    private List<Cidade> listaCidade;
    
    private String valorPesquisar;
    
    public CidadeMBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.valorPesquisar = "";        
        this.cidade = new Cidade();
        this.listaCidade = new ArrayList<>();
    }
    
    public String botaoNovo() {
        this.cidade = new Cidade();
        return "cadCidade?faces-redirect=true";
    }
    
    public String botaoSalvar() {
        cidadeSBean.salvar(cidade);
        cidade = new Cidade();
        return "consCidade?faces-redirect=true";
    }
    
    public void botaoPesquisar() {
        try {
        listaCidade = cidadeSBean.pesquisar(valorPesquisar);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao listar cidade. " + ex.getMessage());
        }
    }
    
    public void botaoExcluir() {
        cidadeSBean.excluir(cidade);
    }
    
    public String botaoEditar() {
        return "cadCidade?faces-redirect=true";
    }
        
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getListaCidade() {
        return listaCidade;
    }   

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }
    
    
}
