/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.persistencia;


import com.ues.edu.mantenimiento.logica.Equipo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author vladi
 */
public class EquipoJpaController extends JpaController<Equipo> {
    public EquipoJpaController() {
        super(Equipo.class);
    }

    public List<Equipo> findByLaboratorio(Long labId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Equipo> q = em.createQuery(
                "SELECT e FROM Equipo e WHERE e.laboratorio.id = :labId ORDER BY e.numeroSerie",
                Equipo.class);
            q.setParameter("labId", labId);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
public long countEquipos() {
    EntityManager em = getEntityManager();
    try {
        Long total = em.createQuery(
            "SELECT COUNT(e) FROM Equipo e", Long.class
        ).getSingleResult();
        return (total != null ? total : 0L);
    } finally {
        em.close();
    }
}

public List<Equipo> findEquipoEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Equipo> q = em.createQuery("SELECT e FROM Equipo e", Equipo.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

}