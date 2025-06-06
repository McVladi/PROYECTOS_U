/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.test;


import com.ues.edu.mantenimiento.service.UsuarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
/**
 *
 * @author vladi
 */
public class CrearEsquema {
    public static void main(String[] args) {
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mantenimientoPU");
      
        EntityManager em = emf.createEntityManager();
        em.close();
        emf.close();
        System.out.println("Esquema generado correctamente.");
       
    }
}