/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.controler;

import sysloc.controler.uteis.UteisJsf;
import sysloc.entidades.UnidadeMedida;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sysloc.sessionbean.UnidadeMedidaSessionBean;

/**
 *
 * @author wender
 */
@Named(value = "unidadeMedidaMBean")
@SessionScoped
public class UnidadeMedidaMBean implements Serializable {
    
    @EJB
    private UnidadeMedidaSessionBean unidadeMedidaSBean;
    
    private UnidadeMedida unidadeMedida;
    
    private List<UnidadeMedida> listaUnidadeMedidas;
    
    private String valorPesquisar;
    
    public UnidadeMedidaMBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.valorPesquisar = "";        
        this.listaUnidadeMedidas = new ArrayList<>();
    }
    
    public String botaoNovo() {
        this.unidadeMedida = new UnidadeMedida();
        return "cadUnidadeMedida?faces-redirect=true";
    }
    
    public void botaoSalvar() {
        this.unidadeMedidaSBean.salvar(unidadeMedida);
        this.unidadeMedida = new UnidadeMedida();
        UteisJsf.addMensagemInfo("Unidade de Medida salva com sucesso...");
        //return "consCidade?faces-redirect=true";
    }
    
    public void botaoPesquisar() {
        this.listaUnidadeMedidas = this.unidadeMedidaSBean.pesquisar(valorPesquisar);
    }
    
    public void botaoExcluir() {
        this.unidadeMedidaSBean.excluir(this.unidadeMedida);
    }
    
    public String botaoEditar() {
        return "cadUnidadeMedida?faces-redirect=true";
    }
    
    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }
    
    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
    
    public List<UnidadeMedida> getListaUnidadeMedidas() {
        return listaUnidadeMedidas;
    }
    
    public void setListaUnidadeMedidas(List<UnidadeMedida> listaUnidadeMedidas) {
        this.listaUnidadeMedidas = listaUnidadeMedidas;
    }
    
    public String getValorPesquisar() {
        return valorPesquisar;
    }
    
    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }
    
}
