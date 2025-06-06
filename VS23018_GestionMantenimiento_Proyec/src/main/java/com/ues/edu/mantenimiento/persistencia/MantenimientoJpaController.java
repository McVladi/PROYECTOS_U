/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.persistencia;


import com.ues.edu.mantenimiento.logica.Mantenimiento;
import com.ues.edu.mantenimiento.logica.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;


/**
 *
 * @author vladi
 */
public class MantenimientoJpaController {
     private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("mantenimientoPU");
      private final JpaController<Mantenimiento> dao;

    public MantenimientoJpaController() {
        this.dao = new JpaController<>(Mantenimiento.class);
    }

    public String create(Mantenimiento m) {
        return dao.create(m);
    }

    public String edit(Mantenimiento m) {
        try {
            return dao.edit(m);
        } catch (Exception e) {
            return "error";
        }
    }

    
    public String destroy(Long id) {
        try {
            return dao.destroy(id);
        } catch (Exception e) {
            return "error";
        }
    }

   
    public Mantenimiento findMantenimiento(Long id) {
        return dao.find(id);
    }

    public List<Mantenimiento> findAllOrderedByFechaProg() {
        EntityManager em = dao.getEntityManager();
        try {
            TypedQuery<Mantenimiento> q = em.createQuery(
                "SELECT m FROM Mantenimiento m ORDER BY m.fechaProgramada ASC",
                Mantenimiento.class
            );
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    

public long countMantPendientes() {
    EntityManager em = dao.getEntityManager();
    try {
        Long total = em.createQuery(
            "SELECT COUNT(m) FROM Mantenimiento m WHERE m.estado = 'PENDIENTE'", Long.class
        ).getSingleResult();
        return (total != null ? total : 0L);
    } finally {
        em.close();
    }
}

public List<Mantenimiento> findMantenimientoEntities() {
    EntityManager em = dao.getEntityManager();
    try {
        TypedQuery<Mantenimiento> q = em.createQuery(
            "SELECT m FROM Mantenimiento m", Mantenimiento.class); // Consulta todos los mantenimientos
        return q.getResultList();
    } finally {
        em.close();
    }
}

  
}
