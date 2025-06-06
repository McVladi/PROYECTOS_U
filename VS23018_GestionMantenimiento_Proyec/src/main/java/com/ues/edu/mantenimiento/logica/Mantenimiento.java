/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.mantenimiento.logica;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
@Table(name = "mantenimiento")
public class Mantenimiento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_programada", nullable = false)
    private Date fechaProgramada;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_real")
    private Date fechaReal;

     @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private TipoMantenimiento tipo;


    @Column(name = "observaciones",       length = 255)
    private String observaciones;

    @Column(name = "piezas_reemplazadas", length = 255)
    private String piezasReemplazadas;

    @ManyToOne
    @JoinColumn(name = "equipo_id",
                nullable = false,
                foreignKey = @ForeignKey(name = "fk_mant_equipo"))
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "tecnico_id",
                nullable = false,
                foreignKey = @ForeignKey(name = "fk_mant_tecnico"))
    private Tecnico tecnico;

    
}
