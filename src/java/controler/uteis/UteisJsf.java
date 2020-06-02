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

    private static HttpSession getSession() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        return request.getSession();
    }

    public static void setObjectSession(String nomeObjeto, Object obj) {
        HttpSession session = UteisJsf.getSession();
        session.setAttribute(nomeObjeto, obj);
    }

    public static Object getObjectSession(String nomeObjeto) {
        HttpSession session = UteisJsf.getSession();
        return session.getAttribute(nomeObjeto);
    }

    public static void removeObjectSession(String nomeObjeto) {
        HttpSession session = UteisJsf.getSession();
        session.removeAttribute(nomeObjeto);
    }
}
