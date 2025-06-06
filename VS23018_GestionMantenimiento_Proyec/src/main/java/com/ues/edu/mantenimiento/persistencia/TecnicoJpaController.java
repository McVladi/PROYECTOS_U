/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.persistencia;

import com.ues.edu.mantenimiento.logica.Tecnico;
import java.util.List;

/**
 *
 * @author vladi
 */
public class TecnicoJpaController {

    private JpaController<Tecnico> jpaCtrl;

    public TecnicoJpaController() {
        
        jpaCtrl = new JpaController<>(Tecnico.class);
    }

    public List<Tecnico> findTecnicoEntities() {
        return jpaCtrl.findAll();
    }

    public Tecnico findTecnico(int id) {
        return jpaCtrl.find(id);
    }

    public String create(Tecnico tecnico) {
        return jpaCtrl.create(tecnico);
    }

    public String edit(Tecnico tecnico) throws Exception {
        return jpaCtrl.edit(tecnico);
    }

    public String destroy(int id) throws Exception {
        return jpaCtrl.destroy(id);
    }
}