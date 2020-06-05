/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.controler.converter;


import sysloc.entidades.UnidadeMedida;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sysloc.sessionbean.UnidadeMedidaSessionBean;

/**
 *
 * @author wender
 */
@FacesConverter("unidadeMedidaConverter")
public class UnidadeMedidaConverter implements Converter {

   
    private UnidadeMedidaSessionBean unidadeMedidaSessionBean;


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {            
            if (value != null && value.trim().length() > 0) {
                Long id = Long.parseLong(value);
                return unidadeMedidaSessionBean.pesquisar(id);
            }
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            UnidadeMedida unidadeMedida = (UnidadeMedida) value;
            return unidadeMedida.getId().toString();
        }
        return null;
    }

    public UnidadeMedidaSessionBean getUnidadeMedidaSessionBean() {
        return unidadeMedidaSessionBean;
    }

    public void setUnidadeMedidaSessionBean(UnidadeMedidaSessionBean unidadeMedidaSessionBean) {
        this.unidadeMedidaSessionBean = unidadeMedidaSessionBean;
    }

    
    
    

}
