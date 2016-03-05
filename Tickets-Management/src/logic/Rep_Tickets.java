/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.time.LocalDate;
import java.util.ArrayList;
import library.Categoria;
import static library.Categoria.*;
import library.Empleado;
import library.Estado;
import static library.Estado.*;
import library.Ticket;

/**
 *
 * @author Valakuth
 */
public class Rep_Tickets extends GestorGeneral {

    public Rep_Tickets() {
        this.lista = new ArrayList<>();
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
            if (tick.getCategoria() == C && tick.getFecha_Hora().equals(D)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public Categoria tipoTicketMasRecibido() {
        Categoria mayor = SINASIGNAR;
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

    public void generateTickets(int pAmount) {
        Ticket newTicket;
        for (int i = 0; i < pAmount; i++) {
            newTicket = new Ticket(LocalDate.now(), "Client " + i, SINATENDER, SINASIGNAR, "bla bla bla..");
            this.agregar(newTicket);
        }
    }

    public int attendedAmountEmployeeCategorie(Empleado emp, Categoria c) {
        int amount = 0;
        for (Object t : this.lista) {
            Ticket tick = (Ticket) t;
            if (tick.getEstado().equals(ATENDIDO)) {
                if (tick.getEmpleado().equals(emp) && tick.getCategoria().equals(c)) {
                    amount++;
                }
            }
        }
        return amount;
    }

    public String employeeAttetionPercentage(Object obj, Categoria c) {
        String report = "";
        Empleado emp = (Empleado) obj;
        report += "\nEmployee: " + emp.getNombre() + ", ";
        float employeeAttetionAmount = attendedAmountEmployeeCategorie(emp, c);
        int categorieAmount = contarCategoria(c);
        if (categorieAmount > 0) {
            float percentage = (employeeAttetionAmount / categorieAmount) * 100;
            report += "Attetion Amount: " + (int) percentage + "%\n";
        } else {
            report += "Attetion Amount: 0%\n";
        }
        return report;
    }

    @Override
    public String toString() {
        String report = "Rep_Tickets{";
        report = this.lista.stream().map((t) -> ((Ticket) t).toString()).reduce(report, String::concat);
        return report + '}';
    }
}
