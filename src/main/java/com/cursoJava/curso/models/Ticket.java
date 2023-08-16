package com.cursoJava.curso.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter
    @Column(name = "idticket")
    private int idticket;
    @Getter @Setter
    @Column(name = "disponibilidad")
    private int disponibilidad;
    @Getter @Setter
    @Column(name = "precioticket")
    private Double precioticket;
    @Getter @Setter
    @Column(name = "idevento")
    private long idevento;

}
