/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import library.Categoria;
import static library.Categoria.*;
import library.Estado;
import static library.Estado.ATENDIDO;
import library.Ticket;

/**
 *
 * @author Valakuth
 */
public class Rep_Tickets extends GestorGeneral {

    public Rep_Tickets() {
    }

    @Override
    public boolean agregar(Object o) {
        int where = lista.indexOf(o);
        if (where == -1) {
            lista.add(o);
        }
        return (where == -1);     // si es -1 entonces si lo agrego..
    }

    @Override
    public boolean modificar(Object o) {
        int where = lista.indexOf(o);
        if (where != -1) {
            lista.set(where, o);
            return true;
        }
        return false;
    }

    public int contarCategoria(Categoria C) {
        int cantidad = 0;
        Ticket tick;
        for (Object t : lista) {
            tick = (Ticket) t;
            if (tick.getCategoria() == C) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int contarCategoriaxFecha(Categoria C, LocalDate D) {
        int cantidad = 0;
        Ticket tick;
        for (Object t : lista) {
            tick = (Ticket) t;
            if (tick.getCategoria() == C && tick.getFecha_Hora() == D) {
                cantidad++;
            }
        }
        return cantidad;
    }

    ;
    
    
    public Categoria tipoTicketMasRecibido() {

        Categoria mayor = VERDE;

        int cantMayor = contarCategoria(mayor);
        for (Categoria c : Categoria.values()) {
            int cuentaActual = contarCategoria(c);
            if (cuentaActual >= cantMayor) {
                mayor = c;
                cantMayor = cuentaActual;
            }
        }
        return mayor;
    }

    public String distribucionTicketXCategoria(LocalDate D) {
        String reporte = "";

        for (Categoria c : Categoria.values()) {
            reporte += "Categoria: " + c + ", cantidad: " + contarCategoriaxFecha(c, D) + "\n";
        }
        return reporte;
    }

    public int contadorTicketsAtendidos() {
        int cantidad = 0;
        Estado estado = ATENDIDO;
        Ticket tick;
        for (Object t : lista) {
            tick = (Ticket) t;
            if (tick.getEstado() == estado) {
                cantidad++;
            }
        }
        return cantidad;
    }
;

}
