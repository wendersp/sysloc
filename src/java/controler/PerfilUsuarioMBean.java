/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;


import controler.uteis.UteisJsf;
import entidades.PerfilUsuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessionbean.PerfilUsuarioSessionBean;


/**
 *
 * @author wender
 */
@Named(value = "perfilUsuarioMBean")
@SessionScoped
public class PerfilUsuarioMBean implements Serializable {

    @EJB
    private PerfilUsuarioSessionBean perfilUsuarioSBean;
    
    private PerfilUsuario perfilUsuario;
    
    private List<PerfilUsuario> listaPerfilUsuarios;
    
    private String valorPesquisar;
    
    public PerfilUsuarioMBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.valorPesquisar = "";        
        this.perfilUsuario = new PerfilUsuario();
        this.listaPerfilUsuarios = new ArrayList<>();
    }
    
    public String botaoNovo() {
        this.perfilUsuario = new PerfilUsuario();
        return "cadPerfilUsuario?faces-redirect=true";
    }
    
    public void botaoSalvar() {
        perfilUsuarioSBean.salvar(perfilUsuario);
        this.perfilUsuario = new PerfilUsuario();
        UteisJsf.addMensagemInfo("Perfil salvo com sucesso....", "");
        //return "consCidade?faces-redirect=true";
    }
    
    public void botaoPesquisar() {
        this.listaPerfilUsuarios = perfilUsuarioSBean.pesquisar(valorPesquisar);
    }
    
    public void botaoExcluir() {
        perfilUsuarioSBean.excluir(perfilUsuario);
        this.listaPerfilUsuarios.remove(perfilUsuario);
         UteisJsf.addMensagemInfo("Perfil removido com sucesso....", "");
    }
    
    public String botaoEditar() {
        return "cadPerfilUsuario?faces-redirect=true";
    }

    public PerfilUsuario getPerfilUsuario() {
        return perfilUsuario;
    }

    public void setPerfilUsuario(PerfilUsuario perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
    }

    public List<PerfilUsuario> getListaPerfilUsuarios() {
        return listaPerfilUsuarios;
    }

    public void setListaPerfilUsuarios(List<PerfilUsuario> listaPerfilUsuarios) {
        this.listaPerfilUsuarios = listaPerfilUsuarios;
    }

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }
        
    
    
}
