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
import logic.gDepartamento;
import view.panels.MainPanel;

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
        Empleado emp1 = new Empleado("Kevin Castro", Categoria.VERDE);
        Empleado emp2 = new Empleado("Juan C.", Categoria.AMARILLO);
        Empleado emp3 = new Empleado("Jim La Sombra", Categoria.ROJO);
        Empleado emp4 = new Empleado("Jose Andrés", Categoria.AMARILLO);
        Empleado emp5 = new Empleado("Marco Ramirez", Categoria.VERDE);
        Empleado emp6 = new Empleado("Petardo Martínez", Categoria.AMARILLO);
        Empleado emp7 = new Empleado("Myron Camacho", Categoria.VERDE);
        Empleado emp8 = new Empleado("Piter La Anguila", Categoria.ROJO);
        Empleado emp9 = new Empleado("Myron Byron", Categoria.ROJO);
        
        gDepartamento dep = new gDepartamento();
        dep.agregar(emp1);
        dep.agregar(emp2);
        dep.agregar(emp3);
        dep.agregar(emp4); 
        dep.agregar(emp5); 
        dep.agregar(emp6); 
        dep.agregar(emp7); 
        dep.agregar(emp8); 
        dep.agregar(emp9); 
        Engine engine=new Engine();
        engine.setRepositorioDepartamento(dep);
        
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
