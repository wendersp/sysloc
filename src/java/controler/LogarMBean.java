package controler;

import controler.uteis.UteisJsf;
import entidades.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sessionbean.UsuarioSessionBean;

/**
 *
 * @author wender
 */
@Named(value = "logarMBean")
@RequestScoped
public class LogarMBean {

    private String userName;
    private String senha;

    @EJB
    private UsuarioSessionBean usuairoSessionBean;

    private Usuario usuarioLogado;

    public LogarMBean() {

    }

    public String logar() {
        try {
            this.usuarioLogado = usuairoSessionBean.logar(userName, senha);
            if (this.usuarioLogado != null) {
                UteisJsf.setObjectSession("usuarioLogado", this.usuarioLogado);
                return "home";
            }
            UteisJsf.addMensagemErro("Usuario ou Senha invalido...", "");            
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage(), "");
        }        
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
