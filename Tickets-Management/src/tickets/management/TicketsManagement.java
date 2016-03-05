/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tickets.management;

import library.Categoria;
import logic.Engine;
import logic.Rep_Tickets;
import logic.controller.Controller;
import view.panels.TicketGenerator;

/**
 *
 * @author myron
 */
public class TicketsManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*Categoria C;
        for (Categoria d : Categoria.values()) {
            System.out.println(d);
        }*/
        Rep_Tickets repo = new Rep_Tickets();
        repo.generateTickets(15);
        System.out.println(repo.toString());
        
        Engine engine=new Engine();
        
        //-------------------------Controllers---------------------------------------------
        Controller controller=new Controller(engine);
        
        //----------------------Ventanas--------------------------------------------
        TicketGenerator viewTicketGenerator=new TicketGenerator(controller);
        
        
        engine.addNewView(viewTicketGenerator);
        
        viewTicketGenerator.setVisible(true);
        
        
        

    }

}
