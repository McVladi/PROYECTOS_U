/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.service;

import com.ues.edu.mantenimiento.logica.Mantenimiento;
import com.ues.edu.mantenimiento.persistencia.MantenimientoJpaController;
import jakarta.persistence.*;
import java.util.List;

/**
 *
 * @author vladi
 */
public class MantenimientoService {

   private final MantenimientoJpaController dao;

    public MantenimientoService() {
        this.dao = new MantenimientoJpaController();
    }

    public List<Mantenimiento> listarTodos() {
        return dao.findAllOrderedByFechaProg();
    }

   
    public String crear(Mantenimiento m) {
       
        return dao.create(m);
    }

   
    public Mantenimiento buscarPorId(Long id) {
        return dao.findMantenimiento(id);
    }

   
    public String actualizar(Mantenimiento m) {
        Mantenimiento existente = dao.findMantenimiento(m.getId());
        if (existente == null) {
            return "error";
        }
       
        existente.setFechaProgramada(m.getFechaProgramada());
        existente.setFechaReal(m.getFechaReal());
        existente.setTipo(m.getTipo());
        existente.setObservaciones(m.getObservaciones());
        existente.setPiezasReemplazadas(m.getPiezasReemplazadas());
        existente.setEquipo(m.getEquipo());
        existente.setTecnico(m.getTecnico());
        return dao.edit(existente);
    }

    
   
    public String eliminar(Long id) {
        return dao.destroy(id);
    }
}
