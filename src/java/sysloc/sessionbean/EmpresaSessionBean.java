/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.sessionbean;


import sysloc.entidades.Empresa;
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
public class EmpresaSessionBean {

    @PersistenceContext(unitName = "syslocPU")
    EntityManager em;
    
    
    public void salvar(Empresa empresa) {
         em.merge(empresa);
    }
    
    public void excluir(Empresa empresa) {
        em.remove(em.find(Empresa.class, empresa.getId()));
    }
    
    public Empresa pesquisar(Long id) {
        return em.find(Empresa.class, id);
    }
    
    public List<Empresa> pesquisar(String razaoSocial) {
        Query consulta = em.createNamedQuery("Empresa.findByRazaoSocial");
        consulta.setParameter("razaoSocial", razaoSocial.toUpperCase() + "%");
        return consulta.getResultList();       
    }
    
    public List<Empresa> pesquisarPorNomeFantasia(String nomeFantasia) {
        Query consulta = em.createNamedQuery("Empresa.findByNomeFantasia");
        consulta.setParameter("nomeFantasia", nomeFantasia.toUpperCase() + "%");
        return consulta.getResultList();       
    }
    
    public Empresa pesquisarPorCnpj(String cnpj) {
        Query consulta = em.createNamedQuery("Empresa.findByCnpj");
        consulta.setParameter("cnpj", cnpj);
        return (Empresa)consulta.getSingleResult();       
    }
}
