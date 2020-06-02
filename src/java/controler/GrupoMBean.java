/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;


import entidades.Grupo;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessionbean.GrupoSessionBean;


/**
 *
 * @author wender
 */
@Named(value = "grupoMBean")
@SessionScoped
public class GrupoMBean implements Serializable {

    @EJB
    private GrupoSessionBean grupoSBean;
    
    private Grupo grupo;
    
    private List<Grupo> listaGrupos;
    
    private String valorPesquisar;
    
    public GrupoMBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.valorPesquisar = "";        
        this.grupo = new Grupo();
        this.listaGrupos = new ArrayList<>();
    }
    
    public String botaoNovo() {
        this.grupo = new Grupo();
        return "cadGrupo?faces-redirect=true";
    }
    
    public void botaoSalvar() {
        grupoSBean.salvar(grupo);
        this.grupo = new Grupo();
        //return "consCidade?faces-redirect=true";
    }
    
    public void botaoPesquisar() {
        this.listaGrupos = grupoSBean.pesquisar(valorPesquisar);
    }
    
    public void botaoExcluir() {
        grupoSBean.excluir(grupo);
    }
    
    public String botaoEditar() {
        return "cadGrupo?faces-redirect=true";
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }
        
    
    
}
