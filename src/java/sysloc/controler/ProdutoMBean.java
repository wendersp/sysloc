/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.controler;

import sysloc.controler.converter.GrupoConverter;
import sysloc.controler.converter.UnidadeMedidaConverter;
import sysloc.controler.uteis.UteisJsf;
import sysloc.entidades.Fornecedor;
import sysloc.entidades.Grupo;
import sysloc.entidades.Produto;
import sysloc.entidades.UnidadeMedida;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sysloc.sessionbean.FornecedorSessionBean;
import sysloc.sessionbean.GrupoSessionBean;
import sysloc.sessionbean.ProdutoSessionBean;
import sysloc.sessionbean.UnidadeMedidaSessionBean;

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
    @EJB
    private FornecedorSessionBean fornecedorSBean;
    
    private Produto produto;
    
    private Fornecedor fornecedorSelecionado;
    
    private List<Produto> listaProdutos;
    
    private List<Grupo> listaGrupos;
    
    private GrupoConverter grupoConverter;
    private UnidadeMedidaConverter unidadeMedidaConverter;
    
    private List<UnidadeMedida> listaUnidadeMedidas;
    
    private List<Fornecedor> listaFornecedores;
    
    private String valorPesquisar;
    
    private String nomeFornecedor;
    
    public ProdutoMBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.valorPesquisar = "";
    }
    
    private void inicializarFormCadastro() {
        this.grupoConverter = new GrupoConverter();
        this.grupoConverter.setGrupoSessionBean(grupoSBean);
        this.unidadeMedidaConverter = new UnidadeMedidaConverter();
        this.unidadeMedidaConverter.setUnidadeMedidaSessionBean(unidadeMedidaSBean);
    }
    
    public String botaoNovo() {
        this.inicializarFormCadastro();
        this.produto = new Produto();
        return "cadProduto?faces-redirect=true";
    }
    
    public void botaoSalvar() {
        try {
            this.produtoSBean.salvar(this.produto);
            this.produto = new Produto();
            UteisJsf.addMensagemInfo("Produto salvo com sucesso...");
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao Salvar Produto - " + ex.getMessage());
        }
    }

    /*---[FORMULARIO ADD FORMECEDOR---------------------------*/
    public String botaoAbrirPesquisaFornecedor() {
        return "consFornecedorAddProduto?faces-redirect=true";
    }
    
    public void botaoPesquisarFornecedor() {
        try {
            this.listaFornecedores = this.fornecedorSBean.pesquisarPorNomeFantasia(nomeFornecedor);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao pesquisar. - " + ex.getMessage());
        }
    }
    
    public String botaoAddFornecedor() {
        this.produto.addFornecedor(fornecedorSelecionado);
        return "cadProduto?faces-redirect=true";
    }
    
    public void botaoRemoverFornecedor() {
        this.produto.removerFornecedor(fornecedorSelecionado);
    }
    
    public List<Grupo> pesquisarGrupoAutoComplete(String valorPesquisar) {
        try {
            return this.grupoSBean.pesquisar(valorPesquisar);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
        return null;
    }
    
    public List<UnidadeMedida> pesquisarUnidadeMedidaAutoComplete(String valorPesquisar) {
        return this.unidadeMedidaSBean.pesquisar(valorPesquisar);
    }
    
    public void botaoPesquisar() {
        try {
            this.listaProdutos = this.produtoSBean.pesquisar(valorPesquisar);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao Pesquisar - " + ex.getMessage());
        }
    }
    
    public String botaoNavPesquisar() {
        return "consProduto?faces-redirect=true";
    }
    
    public void botaoExcluir() {
        try {
            this.produtoSBean.excluir(this.produto);
            this.listaProdutos.remove(this.produto);
            UteisJsf.addMensagemInfo("Produto excluido com sucesso...");
        } catch (Exception ex) {
            UteisJsf.addMensagemErro("Erro ao excluir Produto - " + ex.getMessage());
        }
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
    
    public GrupoConverter getGrupoConverter() {
        return grupoConverter;
    }
    
    public void setGrupoConverter(GrupoConverter grupoConverter) {
        this.grupoConverter = grupoConverter;
    }
    
    public UnidadeMedidaConverter getUnidadeMedidaConverter() {
        return unidadeMedidaConverter;
    }
    
    public void setUnidadeMedidaConverter(UnidadeMedidaConverter unidadeMedidaConverter) {
        this.unidadeMedidaConverter = unidadeMedidaConverter;
    }
    
    public Fornecedor getFornecedorSelecionado() {
        return fornecedorSelecionado;
    }
    
    public void setFornecedorSelecionado(Fornecedor fornecedorSelecionado) {
        this.fornecedorSelecionado = fornecedorSelecionado;
    }
    
    public String getNomeFornecedor() {
        return nomeFornecedor;
    }
    
    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
    
    public List<Fornecedor> getListaFornecedores() {
        return listaFornecedores;
    }
    
    public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }
    
}
