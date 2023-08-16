package com.cursoJava.curso.controllers;

import com.cursoJava.curso.servicios.ticketServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.cursoJava.curso.models.DetalleCompra;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetalleCompraController {

    @Autowired
    ticketServicio ticketservicio;

    @RequestMapping(value="api/detalleCompra",method = RequestMethod.POST)
    public ResponseEntity<String> comprarBoletos(@RequestBody DetalleCompra detalleCompra ){
        boolean compraExitosa = ticketservicio.ComprarBoletos(
                detalleCompra.getIdticket(),
                detalleCompra.getIdevento(),
                detalleCompra.getIdusuariocliente(),
                detalleCompra.getCantidad(),
                detalleCompra.getCostototal(),
                detalleCompra.getFecha(),
                detalleCompra.getFormaPago()
        );

        if(compraExitosa){
            return ResponseEntity.ok("Compra Exitosa");
        }else{
            return ResponseEntity.badRequest().body("No hay suficiente disponibilidad");
        }
    }
}
