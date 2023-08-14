package com.cursoJava.curso.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarioadmin")
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
    private float precioticket;
    @Getter @Setter
    @Column(name = "idevento")
    private int idevento;

}
