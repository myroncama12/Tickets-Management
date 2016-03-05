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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import logic.Engine;
import view.panels.MainPanel;
import view.panels.TicketGenerator;

/**
 *
 * @author myron
 */
public class Controller implements ActionListener, ChangeListener, ListSelectionListener {
    
    private TicketGenerator viewTicketGenerator;
    private MainPanel viewMainPanel;
    
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
           //TODO: Cambiar a MainPanel
            
        
        }
    }

    public void setTicketGenerator(TicketGenerator pTicket) {
        this.viewTicketGenerator=pTicket;
    }
    
    public void setMainPanel(MainPanel pPanel) {
        this.viewMainPanel=pPanel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
