package com.cursoJava.curso.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "usuariocliente")
public class UsuarioCliente {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "idusuariocliente")
    private long idusuariocliente;
    @Getter@Setter @Column(name = "nombre")
    private String nombre;
    @Getter@Setter @Column(name = "apellido")
    private String apellido;
    @Getter@Setter @Column(name = "email")
    private String email;
    @Getter@Setter @Column(name = "telf")
    private String telf;
    @Getter@Setter @Column(name = "password")
    private String password;

}
