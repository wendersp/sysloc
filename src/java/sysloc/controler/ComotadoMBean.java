/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.controler;


import sysloc.entidades.Comodato;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sysloc.sessionbean.ComodatoSessionBean;


/**
 *
 * @author wender
 */
@Named(value = "comodatoMBean")
@SessionScoped
public class ComotadoMBean implements Serializable {

    @EJB
    private ComodatoSessionBean comodatoSBean;
    
    private Comodato comodato;
    
    private List<Comodato> listaComodatos;
    
    private String valorPesquisar;
    
    public ComotadoMBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.valorPesquisar = "";              
    }
    
    public String botaoNovo() {
        this.comodato = new Comodato();
        return "cadComodato?faces-redirect=true";
    }
    
    public void botaoSalvar() {
        this.comodatoSBean.salvar(this.comodato);
        this.comodato = new Comodato();        
    }
    
    public void botaoPesquisar() {
        //this.listaComodatos = this.comodatoSBean.pesquisar(empresa, cliente);
    }
    
    public void botaoExcluir() {
        this.comodatoSBean.excluir(this.comodato);
    }
    
    public String botaoEditar() {
        return "cadComodato?faces-redirect=true";
    }

    public Comodato getComodato() {
        return comodato;
    }

    public void setComodato(Comodato comodato) {
        this.comodato = comodato;
    }

    public List<Comodato> getListaComodatos() {
        return listaComodatos;
    }

    public void setListaComodatos(List<Comodato> listaComodatos) {
        this.listaComodatos = listaComodatos;
    }

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }

    
    
    
}
