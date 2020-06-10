/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.controler.converter;


import sysloc.controler.uteis.UteisJsf;
import sysloc.entidades.Cliente;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sysloc.sessionbean.ClienteSessionBean;

/**
 *
 * @author wender
 */
@FacesConverter("clienteConverter")
public class ClienteConverter implements Converter {

   
    private ClienteSessionBean clienteSessionBean;


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {            
            if (value != null && value.trim().length() > 0) {
                Long id = Long.parseLong(value);
                return clienteSessionBean.pesquisar(id);
            }
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Cliente cliente = (Cliente) value;
            return cliente.getId().toString();
        }
        return null;
    }

    public ClienteSessionBean getClienteSessionBean() {
        return clienteSessionBean;
    }

    public void setClienteSessionBean(ClienteSessionBean clienteSessionBean) {
        this.clienteSessionBean = clienteSessionBean;
    }

    
    
    

}
