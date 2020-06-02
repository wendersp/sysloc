/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;


import entidades.Fornecedor;
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
public class FornecedorSessionBean {

    @PersistenceContext(unitName = "syslocPU")
    EntityManager em;
    
    
    public void salvar(Fornecedor fornecedor) {
         em.merge(fornecedor);
    }
    
    public void excluir(Fornecedor fornecedor) {
        em.remove(em.find(Fornecedor.class, fornecedor.getId()));
    }
    
    public Fornecedor pesquisarPorId(Long id) {
        return em.find(Fornecedor.class, id);
    }
    
    public List<Fornecedor> pesquisarPorRazaoSocial(String razaoSocial) {
        Query consulta = em.createNamedQuery("Fornecedor.findByRazaoSocial");
        consulta.setParameter("razaoSocial", razaoSocial.toUpperCase() + "%");
        return consulta.getResultList();       
    }
    
    public List<Fornecedor> pesquisarPorNomeFantasia(String nomeFantasia) {
        Query consulta = em.createNamedQuery("Fornecedor.findByNomeFantasia");
        consulta.setParameter("nomeFantasia", nomeFantasia.toUpperCase() + "%");
        return consulta.getResultList();       
    }
    
    public Fornecedor pesquisarPorCnpj(String cnpj) {
        Query consulta = em.createNamedQuery("Fornecedor.findByCnpj");
        consulta.setParameter("cnpj", cnpj);
        return (Fornecedor)consulta.getSingleResult();       
    }
}
