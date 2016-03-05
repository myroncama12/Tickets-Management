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
import javax.swing.table.DefaultTableModel;
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
            System.out.println("Se generarán " + this.viewTicketGenerator.getSpinnerCantidadTickets().getValue());
            //model.generateTickets((int) this.viewTicketGenerator.getSpinnerCantidadTickets().getValue());
            this.setVisibleMainPanel();
        
        }
        
        if(e.getSource()==viewMainPanel.getBtnAtender()){
            System.out.println("Atendiendo!");
        }
        
        if(e.getSource()==viewMainPanel.getBtnFinalizar()){
            System.out.println("Finalizado!");
        }
        
        if(e.getSource()==viewMainPanel.getBtnCambiarAtendiente()){
            System.out.println("Cambiando atendiente!");
        }
        
        if(e.getSource()==viewMainPanel.getBtnEstado()){
            System.out.println("Cambiando estado!");
        }
        
        if(e.getSource()==viewMainPanel.getBtnEstadisticas1()){
            System.out.println("Estadística 1");
            DefaultTableModel model = (DefaultTableModel) this.viewMainPanel.getTableClasificar().getModel();
            model.addRow(new Object[] {"Hola", "mis", "nalgas"});
        }
        
        if(e.getSource()==viewMainPanel.getBtnEstadisticas2()){
            System.out.println("Estadística 2");
        }
        
        if(e.getSource()==viewMainPanel.getBtnEstadisticas3()){
            System.out.println("Estadística 3");
        }
    }

    public void setTicketGenerator(TicketGenerator pTicket) {
        this.viewTicketGenerator=pTicket;
    }
    
    public void setMainPanel(MainPanel pPanel) {
        this.viewMainPanel=pPanel;
    }

    private void setVisibleTicketGenerator(){
        this.viewMainPanel.setVisible(false);
        this.viewTicketGenerator.setVisible(true);
    }
    
    private void setVisibleMainPanel(){
        this.viewMainPanel.setVisible(true);
        this.viewTicketGenerator.setVisible(false);
    }
    
    
    
    @Override
    public void stateChanged(ChangeEvent e) {
        
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("penerecto");
    }
    
}
