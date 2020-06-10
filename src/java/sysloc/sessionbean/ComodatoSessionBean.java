/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sysloc.sessionbean;

import sysloc.entidades.Cliente;
import sysloc.entidades.Comodato;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

    public List<Comodato> pesquisar(Cliente cliente, Date dataInicial, Date dataFinal, Boolean isDataVencimentoContrato) throws Exception {
        try {
            Boolean isWhere = false;
            Boolean isAnd = false;

            String consulta = "SELECT c FROM Comodato c ";

            if (cliente != null) {                
                consulta += " WHERE ";
                consulta += " c.cliente = :cliente ";
                isWhere = true;
                isAnd = true;
            }
            if (dataInicial != null && dataFinal != null) {
                if (!isWhere) {
                    consulta += " WHERE ";
                } else {
                    if (isAnd) {
                        consulta += " AND ";
                    }
                }
                if (isDataVencimentoContrato) {
                    consulta += " c.dataVencimento BETWEEN :dataInicial AND :dataFinal ";
                } else {
                    consulta += " c.dataContrato BETWEEN :dataInicial AND :dataFinal ";
                }
            }

            TypedQuery<Comodato> query = em.createQuery(consulta, Comodato.class);
            
            if (cliente != null) {
                query.setParameter("cliente", cliente);
            }
            if (dataInicial != null && dataFinal != null) {
                query.setParameter("dataInicial", dataInicial);
                query.setParameter("dataFinal", dataFinal);
            }
            
            return query.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

}
