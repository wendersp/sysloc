/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import controler.uteis.UteisJsf;
import entidades.Usuario;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author wender
 */
@Named(value = "homeMBean")
@RequestScoped
public class HomeMBean {

    private Usuario usuarioLogado;
    
    public HomeMBean() {
        
    }
    
    @PostConstruct
    public void init() {
       this.usuarioLogado =  (Usuario)UteisJsf.getObjectSession("usuarioLogado");
    }
    
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    
    
}
