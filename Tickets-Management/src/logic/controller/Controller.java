/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import logic.Engine;
import view.panels.TicketGenerator;

/**
 *
 * @author myron
 */
public class Controller implements ActionListener, ChangeListener {
    
    private TicketGenerator viewTicketGenerator;
    
    private Engine model;
    
    public Controller(Engine pEngine){
        model=pEngine;
    }
    
    public Engine getModel(){
        return model;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==viewTicketGenerator.getButtonGenerarTicket()){
            model.generateTickets((int) this.viewTicketGenerator.getSpinnerCantidadTickets().getValue());
            
            
        }
    }

    public void setTicketGenerator(TicketGenerator pTicket) {
        this.viewTicketGenerator=pTicket;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
