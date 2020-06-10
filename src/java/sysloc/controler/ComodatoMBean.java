
package sysloc.controler;

import sysloc.entidades.Comodato;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sysloc.controler.converter.ClienteConverter;
import sysloc.controler.converter.ProdutoConverter;
import sysloc.controler.uteis.Pagina;
import sysloc.controler.uteis.UteisJsf;
import sysloc.entidades.Cliente;
import sysloc.entidades.ItemComodato;
import sysloc.entidades.Produto;
import sysloc.sessionbean.ClienteSessionBean;
import sysloc.sessionbean.ComodatoSessionBean;
import sysloc.sessionbean.ProdutoSessionBean;

/**
 *
 * @author wender
 */
@Named(value = "comodatoMBean")
@SessionScoped
public class ComodatoMBean implements Serializable {

    @EJB
    private ComodatoSessionBean comodatoSBean;
    @EJB
    private ClienteSessionBean clienteSBean;
    @EJB
    private ProdutoSessionBean produtoSBean;
    
    private Comodato comodato;
    
    /*---TELA DE PESQUISA-------*/
    private List<Comodato> listaComodatos;
    
    private Cliente clientePesquisar;    
    private ClienteConverter clienteConverter;
    private Boolean isPesquisarDataVencimento;
    private Date dataInicial;
    private Date dataFinal;
    
   /*------------------------------------------*/
    
    /*------TELA DE ADD ITEM COMODATO---------*/
    private Produto produtoSelecionado;
    private ProdutoConverter produtoConverter;
   
    private ItemComodato itemComodato;
    /*----------------------------------------*/

    public ComodatoMBean() {

    }
    
    @PostConstruct
    public void init() {
        this.clienteConverter = new ClienteConverter();
        this.clienteConverter.setClienteSessionBean(clienteSBean);
    }

    private String abrirFormularioAddItemComdato() {
        this.produtoConverter = new ProdutoConverter();
        this.produtoConverter.setProdutoSessionBean(produtoSBean);        
        return Pagina.ITEM_COMODATO_FRM;
    }

    private void novoItemComodato() {
        this.itemComodato = new ItemComodato();
        this.itemComodato.setQuantidade(1.0);
    }
    
    private void novoComodato() {
        this.comodato = new Comodato();
        this.comodato.setDataContrato(new Date());
        this.comodato.setDataVencimento(dataVencimentoContrato());
    }

    public String botaoNovo() {
        this.novoComodato();
        return Pagina.COMODATO_FRM;
    }

    private Date dataVencimentoContrato() {
        Date hoje = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(hoje);
//        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 10);
//        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
        return c.getTime();
    }

    public void botaoSalvar() {
        this.comodatoSBean.salvar(this.comodato);
        this.novoComodato();
    }

    public void botaoPesquisarComodato() {
        try {
            this.listaComodatos = this.comodatoSBean.pesquisar(clientePesquisar, dataInicial, dataFinal, isPesquisarDataVencimento);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
    }

    public String botaoPesquisarNav() {
        return Pagina.COMODATO_CONS;
    }

    public List<Cliente> pesquisarClienteAutoComplete(String nomeCliente) {
        return this.clienteSBean.pesquisar(nomeCliente);
    }

    public List<Produto> pesquisarProdutoAutoComplete(String nomeProduto) {
        try {
            return this.produtoSBean.pesquisar(nomeProduto);
        } catch (Exception ex) {
            UteisJsf.addMensagemErro(ex.getMessage());
        }
        return null;
    }

    public String addItemComodatoNav() {
        this.novoItemComodato();
        return this.abrirFormularioAddItemComdato();
    }

    public String botaoVoltarComodatoFrm() {
        return Pagina.COMODATO_FRM;
    }

    public void botaoAddItemComodato() {
        this.comodato.adicionarItem(this.itemComodato);
        this.novoItemComodato();
    }

    public String botaoEditarItemComodato() {
        return Pagina.ITEM_COMODATO_FRM;
    }
    
    public void botaoRemoverItemComodato() {
        this.comodato.removerItens(itemComodato);

    }

    public void botaoExcluirComodato() {
        this.comodatoSBean.excluir(this.comodato);
    }

    public String botaoEditarComodato() {        
        return Pagina.COMODATO_FRM;
    }

    public void onSelectProduto() {
        if (this.itemComodato == null) {
            this.novoItemComodato();
        }
        this.itemComodato.setProduto(produtoSelecionado);
    }

    public void calcularValorTotalItem() {
        this.itemComodato.calcularValotTotal();
    }

    public Comodato getComodato() {
        return comodato;
    }

    public void setComodato(Comodato comodato) {
        this.comodato = comodato;
    }

    public List<Comodato> getListaComodatos() {
        return listaComodatos;
    }

    public void setListaComodatos(List<Comodato> listaComodatos) {
        this.listaComodatos = listaComodatos;
    }

    public Cliente getClientePesquisar() {
        return clientePesquisar;
    }

    public void setClientePesquisar(Cliente clientePesquisar) {
        this.clientePesquisar = clientePesquisar;
    }

    public ClienteConverter getClienteConverter() {
        return clienteConverter;
    }

    public void setClienteConverter(ClienteConverter clienteConverter) {
        this.clienteConverter = clienteConverter;
    }

    public ItemComodato getItemComodato() {
        return itemComodato;
    }

    public void setItemComodato(ItemComodato itemComodato) {
        this.itemComodato = itemComodato;
    }

    public ProdutoConverter getProdutoConverter() {
        return produtoConverter;
    }

    public void setProdutoConverter(ProdutoConverter produtoConverter) {
        this.produtoConverter = produtoConverter;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public Boolean getIsPesquisarDataVencimento() {
        return isPesquisarDataVencimento;
    }

    public void setIsPesquisarDataVencimento(Boolean isPesquisarDataVencimento) {
        this.isPesquisarDataVencimento = isPesquisarDataVencimento;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

}
