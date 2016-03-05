/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tickets.management;

import java.time.LocalDate;
import library.Categoria;
import library.Empleado;
import library.Estado;
import library.Ticket;
import logic.Engine;
import logic.Rep_Tickets;
import logic.controller.Controller;
<<<<<<< HEAD
import logic.gDepartamento;
=======
import view.panels.MainPanel;
>>>>>>> 8c2b20501bcf3db9b741222ecc0c457d4ecdf857
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
        Ticket tick1 = new Ticket(LocalDate.now(), "Topo 1", Estado.ATENDIDO, Categoria.VERDE, "");
        Ticket tick2 = new Ticket(LocalDate.now(), "Topo 2", Estado.ATENDIDO, Categoria.ROJO, "");
        Ticket tick3 = new Ticket(LocalDate.now(), "Topo 3", Estado.ATENDIDO, Categoria.VERDE, "");
        Rep_Tickets repo = new Rep_Tickets();
        repo.agregar(tick1);
        repo.agregar(tick2);
        repo.agregar(tick3);
        
        Empleado emp1 = new Empleado("Kevin Castro", Categoria.VERDE);
        Empleado emp2 = new Empleado("Marco Ramirez", Categoria.VERDE);
        Empleado emp3 = new Empleado("Myron Camacho", Categoria.VERDE);
        
        gDepartamento dep = new gDepartamento();
        dep.agregar(emp1);
        dep.agregar(emp2);
        dep.agregar(emp3);
        
        tick1.setEmpleado(emp1);
        tick2.setEmpleado(emp2);
        tick3.setEmpleado(emp3);
        
        Engine eng = new Engine();
        eng.setRepositorioDepartamento(dep);
        eng.setRepositorioTickets(repo);
        
        System.out.println(repo.toString());
        System.out.println(dep.toString());
        System.out.println(eng.employeesAttentionPercentage());
        
       /*
        System.out.println(repo.tipoTicketMasRecibido());
        System.out.println(repo.distribucionTicketXCategoria(LocalDate.now()));
        System.out.println(repo.contadorTicketsAtendidos());
       */
        
        Engine engine=new Engine();
        
        //-------------------------Controllers---------------------------------------------
        Controller controller=new Controller(engine);
        
        //----------------------Ventanas--------------------------------------------
        TicketGenerator viewTicketGenerator=new TicketGenerator(controller);
        MainPanel viewMainPanel = new MainPanel(controller);
        
        engine.addNewView(viewTicketGenerator);
        engine.addNewView(viewMainPanel);
        
        viewTicketGenerator.setVisible(true);
        
        
        

    }

}
