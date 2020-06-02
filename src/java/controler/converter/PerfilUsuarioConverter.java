/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.converter;


import entidades.PerfilUsuario;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import sessionbean.PerfilUsuarioSessionBean;

/**
 *
 * @author wender
 */
@FacesConverter("perfilUsuarioConverter")
public class PerfilUsuarioConverter implements Converter {

   
    private PerfilUsuarioSessionBean perfilUsuarioSBean;


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {            
            if (value != null && value.trim().length() > 0) {
                Long id = Long.parseLong(value);
                return perfilUsuarioSBean.pesquisar(id);
            }
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            PerfilUsuario perfilUsuario = (PerfilUsuario) value;
            return perfilUsuario.getId().toString();
        }
        return null;
    }

    public PerfilUsuarioSessionBean getPerfilUsuarioSBean() {
        return perfilUsuarioSBean;
    }

    public void setPerfilUsuarioSBean(PerfilUsuarioSessionBean perfilUsuarioSBean) {
        this.perfilUsuarioSBean = perfilUsuarioSBean;
    }
    
    

}
