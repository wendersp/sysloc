/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.controler.converter;


import sysloc.controler.uteis.UteisJsf;
import sysloc.entidades.Grupo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sysloc.sessionbean.GrupoSessionBean;

/**
 *
 * @author wender
 */
@FacesConverter("grupoConverter")
public class GrupoConverter implements Converter {

   
    private GrupoSessionBean grupoSessionBean;


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {            
            if (value != null && value.trim().length() > 0) {
                Long id = Long.parseLong(value);
                return grupoSessionBean.pesquisar(id);
            }
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Grupo grupo = (Grupo) value;
            return grupo.getId().toString();
        }
        return null;
    }

    public GrupoSessionBean getGrupoSessionBean() {
        return grupoSessionBean;
    }

    public void setGrupoSessionBean(GrupoSessionBean grupoSessionBean) {
        this.grupoSessionBean = grupoSessionBean;
    }

    
    
    

}
