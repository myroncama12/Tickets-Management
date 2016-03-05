/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import library.Categoria;
import library.Empleado;
import static library.Estado.ATENDIDO;
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

    public void generateTickets(int pAmount) {
        repositorioTickets.generateTickets(pAmount);
    }
    
    public void addNewView(viewInterface pViewInterface){
        this.viewReferenceList.add(pViewInterface);
    }
    
    public String employeesAttentionPercentage () {
        String report = "";
        for (Categoria c : Categoria.values()) {
            report += "\nCategorie: " + c;
            float categorieAmount = repositorioTickets.contarCategoria(c);
            report += "\nAttended Tickets: " + categorieAmount;
            for (Object obj : repositorioDepartamento.lista) {
                report += repositorioTickets.employeeAttetionPercentage(obj, c);

            }
        }
        return report;
    }

}
