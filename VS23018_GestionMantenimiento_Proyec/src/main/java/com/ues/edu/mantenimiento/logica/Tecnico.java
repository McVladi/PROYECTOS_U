/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.logica;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author vladi
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Tecnico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre",          nullable = false, length = 100)
    private String nombre;

    @Column(name = "identificacion",  nullable = false, length = 50)
    private String identificacion;

    @Column(name = "especialidad",    nullable = false, length = 50)
    private String especialidad;

    @Column(name = "telefono",        nullable = false, length = 20)
    private String telefono;

    @Column(name = "email",           nullable = false, length = 100)
    private String email;

    @OneToMany(mappedBy = "tecnico",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Mantenimiento> mantenimientos = new ArrayList<>();
  
}
