package com.cursoJava.curso.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Entity
@Table(name = "detallecompra")
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "iddetallecompra")
    private Long idetallecompra;
    @Getter@Setter @Column(name = "fecha")
    private LocalDate fecha;
    @Getter@Setter @Column(name = "cantidad")
    private int cantidad;
    @Getter@Setter @Column(name = "costototal")
    private double costototal;
    @Getter@Setter @Column(name = "formaPago")
    private String formaPago;
    @Getter@Setter @Column(name = "estadoPago")
    private int estadoPago;
    @Getter@Setter @Column(name = "idevento")
    private long idevento;
    @Getter@Setter @Column(name = "idticket")
    private long idticket;
    @Getter@Setter @Column(name = "idusuariocliente")
    private long idusuariocliente;



}
