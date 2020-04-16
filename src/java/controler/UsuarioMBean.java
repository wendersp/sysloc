/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import entidades.Usuario;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import sessionbean.UsuairoSessionBean;

/**
 *
 * @author wender
 */
@Named(value = "usuarioMBean")
@SessionScoped
public class UsuarioMBean implements Serializable {

    private Usuario usuario;

    private List<Usuario> listaUsuario;

    private String dadosPesquisa;

    @EJB
    private UsuairoSessionBean usuarioSBean;

    public UsuarioMBean() {

    }

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        listaUsuario = new ArrayList<>();
    }

    public String botaoNovo() {
        usuario = new Usuario();
        return "cadUsuario";
    }
    
    public String botaoEditar() {        
        return "cadUsuario";
    }

    public String salvar() {
        usuarioSBean.salvar(usuario);
        usuario = new Usuario();
        return "consUsuario";
    }

    public void botaoPesquisar() {
       listaUsuario = usuarioSBean.pesquisar(dadosPesquisa);        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public void setDadosPesquisa(String dadosPesquisa) {
        this.dadosPesquisa = dadosPesquisa;
    }

    public String getDadosPesquisa() {
        return dadosPesquisa;
    }

}
