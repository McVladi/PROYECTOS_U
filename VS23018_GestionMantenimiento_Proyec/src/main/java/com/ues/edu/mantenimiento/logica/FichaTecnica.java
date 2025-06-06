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
@Table(name = "ficha_tecnica")
public class FichaTecnica implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_compra", nullable = false)
    private Date fechaCompra;

    @Column(name = "garantia_meses", nullable = false)
    private Integer garantiaMeses;

    @OneToOne
    @JoinColumn(name = "equipo_id",
                nullable = false,
                foreignKey = @ForeignKey(name = "fk_ficha_equipo"))
    private Equipo equipo;

   
}