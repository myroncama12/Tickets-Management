/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
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

    public void generateTickets(int pAmount) {
        repositorioTickets.generateTickets(pAmount);
    }

}
