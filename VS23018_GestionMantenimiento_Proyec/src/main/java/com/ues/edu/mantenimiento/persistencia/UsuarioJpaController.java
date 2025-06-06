/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.persistencia;

import com.ues.edu.mantenimiento.logica.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vladi
 */
public class UsuarioJpaController {

    private final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("mantenimientoPU");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   
    public String create(Usuario user) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return "exito";
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return "error";
        } finally {
            em.close();
        }
    }

    // 2) Buscar por ID
    public Usuario findUsuario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

  
    public Usuario findByUsername(String username) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> q = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.username = :username", Usuario.class);
            q.setParameter("username", username);
            List<Usuario> lista = q.getResultList();
            return lista.isEmpty() ? null : lista.get(0);
        } finally {
            em.close();
        }
    }

  
    public String update(Usuario user) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return "exito";
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return "error";
        } finally {
            em.close();
        }
    }

    
    public List<Usuario> findUsuarioEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> q = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
public long countUsuariosActivos() {
    EntityManager em = getEntityManager();
    try {
        Long total = em.createQuery(
            "SELECT COUNT(u) FROM Usuario u WHERE u.activo = true", Long.class
        ).getSingleResult();
        return (total != null ? total : 0L);
    } finally {
        em.close();
    }
}

}
