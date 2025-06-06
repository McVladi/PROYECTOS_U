/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.logica;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "laboratorio")
public class Laboratorio implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre",    nullable = false, length = 100)
    private String nombre;

    @Column(name = "edificio",  nullable = false, length = 50)
    private String edificio;

    @Column(name = "piso",      nullable = false, length = 10)
    private String piso;

    @OneToMany(mappedBy = "laboratorio",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<Equipo> equipos = new ArrayList<>();

    
}
