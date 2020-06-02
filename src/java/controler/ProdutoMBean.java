/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;


import entidades.Grupo;
import entidades.Produto;
import entidades.UnidadeMedida;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessionbean.GrupoSessionBean;
import sessionbean.ProdutoSessionBean;
import sessionbean.UnidadeMedidaSessionBean;


/**
 *
 * @author wender
 */
@Named(value = "produtoMBean")
@SessionScoped
public class ProdutoMBean implements Serializable {

    @EJB
    private ProdutoSessionBean produtoSBean;
    @EJB
    private GrupoSessionBean grupoSBean;
    @EJB
    private UnidadeMedidaSessionBean unidadeMedidaSBean;
    
    private Produto produto;
    
    private List<Produto> listaProdutos;    
    
    private List<Grupo> listaGrupos;
    
    private List<UnidadeMedida> listaUnidadeMedidas;
    
    private String valorPesquisar;
    
    public ProdutoMBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.valorPesquisar = "";               
    }
    
    private void inicializarFormCadastro() {
        this.listaGrupos = this.grupoSBean.pesquisar("");
        this.listaUnidadeMedidas = this.unidadeMedidaSBean.pesquisar("");
    }
    
    public String botaoNovo() {
        this.inicializarFormCadastro();
        this.produto = new Produto();
        return "cadProduto?faces-redirect=true";
    }
    
    public void botaoSalvar() {
        this.produtoSBean.salvar(this.produto);
        this.produto = new Produto();        
    }
    
    public void botaoPesquisar() {
        this.listaProdutos = this.produtoSBean.pesquisar(valorPesquisar);
    }
    
    public void botaoExcluir() {
        this.produtoSBean.excluir(produto);
    }
    
    public String botaoEditar() {
        this.inicializarFormCadastro();
        return "cadProduto?faces-redirect=true";
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public List<Grupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<Grupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<UnidadeMedida> getListaUnidadeMedidas() {
        return listaUnidadeMedidas;
    }

    public void setListaUnidadeMedidas(List<UnidadeMedida> listaUnidadeMedidas) {
        this.listaUnidadeMedidas = listaUnidadeMedidas;
    }

    public String getValorPesquisar() {
        return valorPesquisar;
    }

    public void setValorPesquisar(String valorPesquisar) {
        this.valorPesquisar = valorPesquisar;
    }

    
    
}
