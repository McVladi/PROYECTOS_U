/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.persistencia;


import com.ues.edu.mantenimiento.logica.Role;
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
public class RolJpaController {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mantenimientoPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public String create(Role role) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(role);
            em.getTransaction().commit();
            return "exito";
        } catch (Exception e) {
            em.getTransaction().rollback();
            return "error";
        } finally {
            em.close();
        }
    }

    public Role find(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Role.class, id);
        } finally {
            em.close();
        }
    }

    public Role findByName(String nombre) {
    EntityManager em = getEntityManager();
    try {
        TypedQuery<Role> query = em.createQuery(
            "SELECT r FROM Rol r WHERE r.nombre = :nombre", Role.class);
        query.setParameter("nombre", nombre);
        return query.getResultStream().findFirst().orElse(null);
    } finally {
        em.close();
    }
}

    public List<Role> findAll() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Role> q = em.createQuery("SELECT r FROM Rol r", Role.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Role> findRolEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Role> q = em.createQuery("SELECT r FROM Rol r", Role.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}
