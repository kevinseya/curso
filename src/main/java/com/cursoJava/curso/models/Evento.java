package com.cursoJava.curso.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "evento")
public class Evento {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "idevento")
    private Long idevento;
    @Getter@Setter @Column(name = "nombre")
    private String nombre;
    @Getter@Setter @Column(name = "descripcion")
    private String descripcion;
    @Getter@Setter @Column(name = "genero")
    private String genero;
    @Getter@Setter @Column(name = "cantboletosdisponibles")
    private int cantboletosdisponibles;
    @Getter@Setter @Column(name = "duracion")
    private String duracion;
    @Getter@Setter @Column(name = "fecha")
    private LocalDate fecha;
    @Getter@Setter @Column(name = "lugar")
    private String lugar;
    @Getter@Setter @Column(name = "precioticket")
    private Double precioticket;
    @Getter@Setter @Column(name = "etiqueta")
    private String etiqueta;
    @Setter @Getter @Column(name="idusuarioadmin")
    private Long idusuarioadmin;



}
