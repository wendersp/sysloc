/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entidades.Usuario;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author wender
 */
@Stateful
public class UsuairoSessionBean {

    @PersistenceContext(unitName = "syslocPU")
    private EntityManager em;
    
    public void salvar(Usuario usuario) {
            em.merge(usuario);       
    }

    public void excluir(Usuario usuario) {
            em.remove(em.find(usuario.getClass(), usuario.getId()));        
    }

    public Usuario pesquisar(Long id) {        
        return (Usuario) em.find(Usuario.class, id);        
    }

    public List<Usuario> pesquisar(String nome) {
        Query consulta = em.createNamedQuery("Usuario.findByNome");
        consulta.setParameter("nome", nome + "%");
        return consulta.getResultList();
    }
}
