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

    public void generateTickets(int pAmount) {
        repositorioTickets.generateTickets(pAmount);
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
    
    public int attendedAmountEmployeeCategorie(Empleado emp, Categoria c) {
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
    
    public String employeesAttentionPercentage () {
        String report = "";
        for (Categoria c : Categoria.values()) {
            report += "\nCategorie: " + c;
            float categorieAmount = repositorioTickets.contarCategoria(c);
            report += "\nAttended Tickets: " + categorieAmount;
            for (Object obj : repositorioDepartamento.lista) {
                Empleado emp = (Empleado) obj;
                report += "\nEmployee: " + emp.getNombre() + ", ";
                float employeeAttetionAmount = attendedAmountEmployeeCategorie(emp, c);
                if (categorieAmount > 0) {
                    float percentage = (employeeAttetionAmount / categorieAmount) * 100;
                    report += "Attention Percentage: " + (int) percentage + "%\n";
                } else {
                    report += "Attention Percentage: 0%\n";
                }
            }
        }
        return report;
    }
    
    public boolean asignTicketCategorie(Ticket tick, Categoria c) {        
        int where = this.repositorioTickets.lista.indexOf(tick);
        if (where != -1) {
            Ticket t = (Ticket) this.repositorioTickets.lista.get(where);
            t.setCategoria(c);
            this.repositorioTickets.lista.set(where, t);
            return true;
        }
        return false;
    }
    
    public boolean asignTicketEmployee(Ticket tick, Empleado emp) {        
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
    
    public boolean completeTicketAttention(Ticket tick) {        
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
