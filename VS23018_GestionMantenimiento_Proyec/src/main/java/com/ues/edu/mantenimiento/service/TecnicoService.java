/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.service;

import com.ues.edu.mantenimiento.logica.Tecnico;
import com.ues.edu.mantenimiento.persistencia.TecnicoJpaController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 *
 * @author vladi
 */
public class TecnicoService {
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("mantenimientoPU");

    public List<Tecnico> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Tecnico t", Tecnico.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

  
    public Tecnico buscarPorId(long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Tecnico.class, id);
        } finally {
            em.close();
        }
    }

   
    public String crear(Tecnico t) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(t);
            tx.commit();
            return "exito";
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return "error";
        } finally {
            em.close();
        }
    }

   
    public String actualizar(Tecnico t) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(t);
            tx.commit();
            return "exito";
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return "error";
        } finally {
            em.close();
        }
    }

  
    public String eliminar(long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Tecnico t = em.find(Tecnico.class, id);
            if (t == null) {
                return "error";           
            }
            em.remove(t);
            tx.commit();
            return "exito";
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            return "error";
        } finally {
            em.close();
        }
    }
}
