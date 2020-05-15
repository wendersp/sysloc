package controler.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wender
 */
public class UteisJsf {

    public static void addMensagemErro(String sumario, String mensagem) {
        FacesContext facesContext = FacesContext.getCurrentInstance();        
        facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, sumario, mensagem));
    }

    public static void addMensagemInfo(String sumario, String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        sumario, mensagem));
    }

    public static void setObjectSession(String nomeObjeto, Object obj) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(nomeObjeto, obj);
    }

    public static Object getObjectSession(String nomeObjeto) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpSession session = request.getSession();
        return session.getAttribute(nomeObjeto);
    }
}
