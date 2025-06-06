/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.service;


import com.ues.edu.mantenimiento.logica.Laboratorio;
import com.ues.edu.mantenimiento.persistencia.LaboratorioJpaController;
import jakarta.persistence.*;
import java.util.List;

/**
 *
 * @author vladi
 */
public class LaboratorioService {
    
   private final LaboratorioJpaController dao;

    public LaboratorioService() {
        this.dao = new LaboratorioJpaController();
    }

    /**
     * Lista todos los laboratorios, con JPQL ordenado.
     */
    public List<Laboratorio> listarTodos() {
        return dao.findAllOrdered();
    }

    /**
     * Crea un nuevo laboratorio.
     */
    public String crear(Laboratorio lab) {
        // Aquí podrías agregar validaciones adicionales.
        return dao.create(lab);
    }

    /**
     * Busca un laboratorio por su ID.
     */
    public Laboratorio buscarPorId(Long id) {
        return dao.findLaboratorio(id);
    }

    /**
     * Actualiza los campos del laboratorio que cambia (merge).
     */
    public String actualizar(Laboratorio lab) {
        // Antes de actualizar, podrías validar que exista:
        Laboratorio existente = dao.findLaboratorio(lab.getId());
        if (existente == null) {
            return "error";
        }
        existente.setNombre(lab.getNombre());
        existente.setEdificio(lab.getEdificio());
        existente.setPiso(lab.getPiso());
        return dao.edit(existente);
    }

    /**
     * Elimina el laboratorio con el ID indicado.
     */
    public String eliminar(Long id) {
        return dao.destroy(id);
    }
}
