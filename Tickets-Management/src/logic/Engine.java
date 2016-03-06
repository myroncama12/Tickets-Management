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
import static library.Estado.*;
import library.Ticket;
import view.interfaces.viewInterface;

/**
 *
 * @author Valakuth
 */
public class Engine {

    private ArrayList<viewInterface> viewReferenceList;
    private Rep_Tickets repositorioTickets;
    private gDepartamento repositorioDepartamento;

    public Engine() {
        repositorioTickets = new Rep_Tickets();
        repositorioDepartamento = new gDepartamento();
        viewReferenceList = new ArrayList<>();
    }

    public Rep_Tickets getRepositorioTickets() {
        return repositorioTickets;
    }

    public void setRepositorioTickets(Rep_Tickets repositorioTickets) {
        this.repositorioTickets = repositorioTickets;
    }

    public gDepartamento getRepositorioDepartamento() {
        return repositorioDepartamento;
    }
    
    public void setRepositorioDepartamento(gDepartamento repositorioDepartamento) {
        this.repositorioDepartamento = repositorioDepartamento;
    }    
    
    public ArrayList<Ticket> obtenerTiquetesSinCategoria() {
        ArrayList<Ticket> tickets = new ArrayList<>();        
        for (Object obj : this.repositorioTickets.lista) {
            Ticket tick = (Ticket) obj;
            if (tick.getCategoria().equals(SINASIGNAR)) {
                tickets.add(tick);
            }
        }        
        return tickets;
    }
    
    public ArrayList<Ticket> obtenerTiquetesSinAtender() {
        ArrayList<Ticket> tickets = new ArrayList<>();        
        for (Object obj : this.repositorioTickets.lista) {
            Ticket tick = (Ticket) obj;
            if (!tick.getCategoria().equals(SINASIGNAR)) {
                if (tick.getEstado().equals(SINATENDER)) {
                    tickets.add(tick);
                }
            }
        }        
        return tickets;
    } 
    
    public ArrayList<Ticket> obtenerTiquetesEnAtencion() {
        ArrayList<Ticket> tickets = new ArrayList<>();        
        for (Object obj : this.repositorioTickets.lista) {
            Ticket tick = (Ticket) obj;
            if (!tick.getCategoria().equals(SINASIGNAR)) {
                if (tick.getEstado().equals(ENATENCION)) {
                    tickets.add(tick);
                }
            }
        }        
        return tickets;
    }      

    public void generarTickets(int pAmount) {
        repositorioTickets.generarTickets(pAmount);
    }
    
    public Categoria tipoTicketMasRecibido() {
        return repositorioTickets.tipoTicketMasRecibido();
    }
        
    public String distribucionTicketXCategoria(LocalDate D) {
        return repositorioTickets.distribucionTicketXCategoria(D);
    }
    
    public void addNewView(viewInterface pViewInterface) {
        this.viewReferenceList.add(pViewInterface);
    }
    
    public int cantidadDeAtencionCategoriaXEmpleado(Empleado emp, Categoria c) {
        int amount = 0;
        for (Object t : this.repositorioTickets.lista) {
            Ticket tick = (Ticket) t;
            if (tick.getEstado().equals(ATENDIDO)) {
                if (tick.getEmpleado().equals(emp) && tick.getCategoria().equals(c)) {
                    amount++;
                }
            }
        }
        return amount;
    }
    
    public String porcentajeDeAtencionXEmpleados () {
        String report = "";
        for (Categoria c : Categoria.values()) {
            if (c.equals(Categoria.SINASIGNAR)){
                break;
            }
            report += "\nCategoría: " + c;
            float categorieAmount = repositorioTickets.contarCategoria(c);
            report += "\nTickets atendidos: " + categorieAmount;
            for (Object obj : repositorioDepartamento.lista) {
                Empleado emp = (Empleado) obj;
                report += "\nEmpleado: " + emp.getNombre() + ", ";
                float employeeAttetionAmount = cantidadDeAtencionCategoriaXEmpleado(emp, c);
                if (categorieAmount > 0) {
                    float percentage = (employeeAttetionAmount / categorieAmount) * 100;
                    report += "Porcentaje de atención: " + (int) percentage + "%";
                } else {
                    report += "Porcentaje de atención: 0%";
                }
            }
        }
        return report;
    }
    
    public boolean asignarCategoriaATicket(Ticket tick) {        
        int where = this.repositorioTickets.lista.indexOf(tick);
        if (where != -1) {
            this.repositorioTickets.lista.set(where, tick);
            return true;
        }
        return false;
    }
    
    public boolean asignarEmpleadoATicket(Ticket tick, Empleado emp) {        
        int where = this.repositorioTickets.lista.indexOf(tick);
        if (where != -1) {
            Ticket t = (Ticket) this.repositorioTickets.lista.get(where);
            t.setEmpleado(emp);
            t.setEstado(ENATENCION);
            this.repositorioTickets.lista.set(where, t);
            return true;
        }
        return false;
    }    
    
    public boolean finalizarAtencionTicket(Ticket tick) {        
        int where = this.repositorioTickets.lista.indexOf(tick);
        if (where != -1) {
            Ticket t = (Ticket) this.repositorioTickets.lista.get(where);
            t.setEstado(ATENDIDO);
            this.repositorioTickets.lista.set(where, t);
            return true;
        }
        return false;
    }
}
