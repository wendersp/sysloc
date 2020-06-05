/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.controler.converter;


import sysloc.controler.uteis.UteisJsf;
import sysloc.entidades.Cidade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sysloc.sessionbean.CidadeSessionBean;

/**
 *
 * @author wender
 */
@FacesConverter("cidadeConverter")
public class CidadeConverter implements Converter {

   
    private CidadeSessionBean cidadeSessionBean;


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {            
            if (value != null && value.trim().length() > 0) {
                Long id = Long.parseLong(value);
                return cidadeSessionBean.pesquisar(id);
            }
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Cidade cidade = (Cidade) value;
            return cidade.getId().toString();
        }
        return null;
    }

    public CidadeSessionBean getCidadeSessionBean() {
        return cidadeSessionBean;
    }

    public void setCidadeSessionBean(CidadeSessionBean cidadeSessionBean) {
        this.cidadeSessionBean = cidadeSessionBean;
    }

    
    
    

}
