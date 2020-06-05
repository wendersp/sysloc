/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.sessionbean;


import sysloc.entidades.Cliente;
import sysloc.entidades.Comodato;
import sysloc.entidades.Empresa;
import java.util.Date;
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
public class ComodatoSessionBean {

    @PersistenceContext(unitName = "syslocPU")
    EntityManager em;
    
    
    public void salvar(Comodato comodato) {
         em.merge(comodato);
    }
    
    public void excluir(Comodato comodato) {
        em.remove(em.find(Comodato.class, comodato.getId()));
    }
    
    public Comodato pesquisar(Long id) {
        return em.find(Comodato.class, id);
    }
    
    public List<Comodato> pesquisarPorCliente(Empresa empresa, Cliente cliente) {
        Query consulta = em.createNamedQuery("Comodato.findByCliente");
        consulta.setParameter("empresa", empresa);
        consulta.setParameter("cliente", cliente);
        return consulta.getResultList();       
    }
    
     public List<Comodato> pesquisarPorDataContrato(Empresa empresa, Date dataContrato) {
        Query consulta = em.createNamedQuery("Comodato.findByDataContrato");
        consulta.setParameter("empresa", empresa);
        consulta.setParameter("dataCantrato", dataContrato);
        return consulta.getResultList();       
    }
     
     public List<Comodato> pesquisarPorDataVencimento(Empresa empresa, Date dataVencimento) {
        Query consulta = em.createNamedQuery("Comodato.findByDataVencimento");
        consulta.setParameter("empresa", empresa);
        consulta.setParameter("dataVencimento", dataVencimento);
        return consulta.getResultList();       
    }
     
      public List<Comodato> pesquisarPorEmpresa(Empresa empresa) {
        Query consulta = em.createNamedQuery("Comodato.findByEmpresa");
        consulta.setParameter("empresa", empresa);        
        return consulta.getResultList();       
    }
     
}
