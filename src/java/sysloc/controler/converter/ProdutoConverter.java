/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.controler.converter;


import sysloc.controler.uteis.UteisJsf;
import sysloc.entidades.Produto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sysloc.sessionbean.ProdutoSessionBean;

/**
 *
 * @author wender
 */
@FacesConverter("produtoConverter")
public class ProdutoConverter implements Converter {

   
    private ProdutoSessionBean produtoSessionBean;


    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {            
            if (value != null && value.trim().length() > 0) {
                Long id = Long.parseLong(value);
                return produtoSessionBean.pesquisar(id);
            }
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Produto produto = (Produto) value;
            return produto.getId().toString();
        }
        return null;
    }

    public ProdutoSessionBean getProdutoSessionBean() {
        return produtoSessionBean;
    }

    public void setProdutoSessionBean(ProdutoSessionBean produtoSessionBean) {
        this.produtoSessionBean = produtoSessionBean;
    }

    
    
    

}
