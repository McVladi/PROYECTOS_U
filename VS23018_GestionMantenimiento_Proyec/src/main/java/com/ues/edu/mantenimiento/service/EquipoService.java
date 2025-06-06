/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.service;

import com.ues.edu.mantenimiento.logica.Equipo;
import com.ues.edu.mantenimiento.logica.Mantenimiento;
import com.ues.edu.mantenimiento.persistencia.EquipoJpaController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author vladi
 */

public class EquipoService {
   private EquipoJpaController dao = new EquipoJpaController();

    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("mantenimientoPU");
    private EntityManager em = emf.createEntityManager();

    
    public List<Equipo> listarTodos() {
        return em.createQuery("SELECT e FROM Equipo e", Equipo.class)
                 .getResultList();
    }

   
    public List<Equipo> listarPorLaboratorio(Long labId) {
        return dao.findByLaboratorio(labId);
    }
}