/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;


import entidades.Cidade;
import entidades.Grupo;
import entidades.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wender
 */
@Stateless
public class ProdutoSessionBean {

    @PersistenceContext(unitName = "syslocPU")
    EntityManager em;
    
    
    public void salvar(Produto produto) {
         em.merge(produto);
    }
    
    public void excluir(Produto produto) {
        em.remove(em.find(Produto.class, produto.getId()));
    }
    
    public Produto pesquisar(Long id) {
        return em.find(Produto.class, id);
    }
    
    public List<Produto> pesquisar(String nome) {
        Query consulta = em.createNamedQuery("Produto.findByNome");
        consulta.setParameter("nome", nome.toUpperCase() + "%");
        return  consulta.getResultList();       
    }
    
    public List<Produto> pesquisarPorGrupo(Grupo grupo) {
        Query consulta = em.createNamedQuery("Produto.findByGrupo");
        consulta.setParameter("grupo", grupo);
        return  consulta.getResultList();       
    }
}
