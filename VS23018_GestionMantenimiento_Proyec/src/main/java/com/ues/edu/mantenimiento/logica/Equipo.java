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
@Table(name = "equipo")                  
public class Equipo implements Serializable {

    @Id                                  
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")                  
    private Long id;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @Column(name = "numero_serie", nullable = false, length = 100)
    private String numeroSerie;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoEquipo estado;

   
    @ManyToOne
    @JoinColumn(name = "laboratorio_id", nullable = false,
                foreignKey = @ForeignKey(name = "fk_equipo_laboratorio"))
    private Laboratorio laboratorio;

   
    @OneToOne(mappedBy = "equipo",
              cascade = CascadeType.ALL, orphanRemoval = true)
    private FichaTecnica fichaTecnica;

    
    @OneToMany(mappedBy = "equipo",
               cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mantenimiento> mantenimientos = new ArrayList<>();
   
}
