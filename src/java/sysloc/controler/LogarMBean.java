package sysloc.controler;

import sysloc.controler.uteis.UteisJsf;
import sysloc.entidades.Usuario;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sysloc.sessionbean.UsuarioSessionBean;

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
//            this.userName = "antonio";
//            this.senha = "";
                    
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

    public String fechar() {
        UteisJsf.removeObjectSession("usuarioLogado");
        UteisJsf.addMensagemInfo("Logoff realizado com sucesso.","");
        return "login?faces-redirect=true";
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
