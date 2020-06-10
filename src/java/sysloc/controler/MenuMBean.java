package sysloc.controler;

import sysloc.controler.uteis.UteisJsf;
import sysloc.entidades.Usuario;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import sysloc.controler.uteis.Pagina;

/**
 *
 * @author wender
 */
@Named(value = "menuMBean")
@RequestScoped
public class MenuMBean {

    private Usuario usuarioLogado;
    
    private Boolean isMenuUsuario;

    public MenuMBean() {

    }

   @PostConstruct
   public void init() {
       this.usuarioLogado = (Usuario)UteisJsf.getObjectSession("usuarioLogado");
       isMenuUsuario = false;
   }
   
   public String menuHome() {
       return Pagina.HOME;
   }
   
   public String menuUsuario() {      
       return "consUsuario?faces-redirect=true";      
   }
   
   public String menuCidade() {
       return "consCidade?faces-redirect=true";       
   }
   
   public String menuCliente() {
       return Pagina.CLIENTE_CONS;       
   }
   
   
   public String menuGrupo() {
       return "consGrupo?faces-redirect=true";       
   }
   
    public String menuUnidadeMedida() {
       return "consUnidadeMedida?faces-redirect=true";       
   }
   
   public String menuFornecedor() {
       return "consFornecedor?faces-redirect=true";       
   }
   
   public String menuProduto() {
       return "consProduto?faces-redirect=true";       
   }
   
    public String menuContratoComodato() {
       return Pagina.COMODATO_CONS;       
   }
   
    public String menuPerfilUsuario() {
       return "consPerfilUsuario?faces-redirect=true";       
   }

    public String fechar() {
        UteisJsf.removeObjectSession("usuarioLogado");
        UteisJsf.addMensagemInfo("Logoff realizado com sucesso.","");
        return "login?faces-redirect=true";
    }

    public Boolean getIsMenuUsuario() {
        return isMenuUsuario;
    }

    public void setIsMenuUsuario(Boolean isMenuUsuario) {
        this.isMenuUsuario = isMenuUsuario;
    }
    
   
}
