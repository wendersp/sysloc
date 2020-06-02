/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import controler.converter.PerfilUsuarioConverter;
import entidades.PerfilUsuario;
import entidades.Usuario;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import sessionbean.PerfilUsuarioSessionBean;
import sessionbean.UsuarioSessionBean;

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
    private UsuarioSessionBean usuarioSBean;
    @EJB
    private PerfilUsuarioSessionBean perfilUsuarioSBean;
    
    private PerfilUsuarioConverter perfilUsuarioConverter;

    public UsuarioMBean() {

    }

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        listaUsuario = new ArrayList<>();
        
    }
    
    public List<PerfilUsuario> pesquisarPerfilAutoComplete(String valorPesquisar) {
        return this.perfilUsuarioSBean.pesquisar(valorPesquisar);
    }

    public String botaoNovo() {
        this.perfilUsuarioConverter = new PerfilUsuarioConverter();
        this.perfilUsuarioConverter.setPerfilUsuarioSBean(perfilUsuarioSBean);
        usuario = new Usuario();
        return "cadUsuario";
    }
    
    public String botaoEditar() {    
        this.perfilUsuarioConverter = new PerfilUsuarioConverter();
        this.perfilUsuarioConverter.setPerfilUsuarioSBean(perfilUsuarioSBean);
        return "cadUsuario";
    }

    public String salvar() {
        usuarioSBean.salvar(usuario);
        System.out.println("Perfil " + usuario.getPerfil().getNome());
        usuario = new Usuario();
        return "consUsuario";
    }

    public void botaoPesquisar() {
       listaUsuario = usuarioSBean.pesquisar(dadosPesquisa);        
    }
    
    public void botaoExcluir() {
        usuarioSBean.excluir(usuario);
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

    public PerfilUsuarioConverter getPerfilUsuarioConverter() {
        return perfilUsuarioConverter;
    }

    public void setPerfilUsuarioConverter(PerfilUsuarioConverter perfilUsuarioConverter) {
        this.perfilUsuarioConverter = perfilUsuarioConverter;
    }

    
}
