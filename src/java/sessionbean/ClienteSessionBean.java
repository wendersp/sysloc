/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;


import entidades.Cliente;
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
public class ClienteSessionBean {

    @PersistenceContext(unitName = "syslocPU")
    EntityManager em;
    
    
    public void salvar(Cliente cliente) {
         em.merge(cliente);
    }
    
    public void excluir(Cliente cliente) {
        em.remove(em.find(Cliente.class, cliente.getId()));
    }
    
    public Cliente pesquisar(Long id) {
        return em.find(Cliente.class, id);
    }
    
    public List<Cliente> pesquisar(String nome) {
        Query consulta = em.createNamedQuery("Cliente.findByNome");
        consulta.setParameter("nome", nome.toUpperCase() + "%");
        return consulta.getResultList();
       
    }
}
