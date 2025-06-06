/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.persistencia;

import com.ues.edu.mantenimiento.logica.Laboratorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author vladi
 */
public class LaboratorioJpaController {
    
      private final JpaController<Laboratorio> dao;
     public LaboratorioJpaController() {
        this.dao = new JpaController<>(Laboratorio.class);
    }

    public String create(Laboratorio lab) {
        return dao.create(lab);
    }

    
    public String edit(Laboratorio lab) {
        try {
            return dao.edit(lab);
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

    
    public Laboratorio findLaboratorio(Long id) {
        return dao.find(id);
    }

    public List<Laboratorio> findAllOrdered() {
        EntityManager em = dao.getEntityManager();
        try {
            TypedQuery<Laboratorio> q = em.createQuery(
                "SELECT l FROM Laboratorio l ORDER BY l.id", Laboratorio.class
            );
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
