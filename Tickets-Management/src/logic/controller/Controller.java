/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import library.Categoria;
import library.Estado;
import library.Ticket;
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
            int cantidadTickets = (int) this.viewTicketGenerator.getSpinnerCantidadTickets().getValue();
            model.generarTickets(cantidadTickets);
            System.out.println("Se generarán " + this.viewTicketGenerator.getSpinnerCantidadTickets().getValue() + " tiquetes");
            addToTableNonCategorized();
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
            classifyTicket();
        }
        
        if(e.getSource()==viewMainPanel.getBtnEstadisticas1()){
            System.out.println("Estadística 1");
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
        
    }
    
    private void addToTableNonCategorized(){
        DefaultTableModel listaClasificar = (DefaultTableModel) this.viewMainPanel.getTableClasificar().getModel();
        Ticket aAgregar;
        for (Object elemento : model.obtenerTiquetesSinCategoria() ){
            aAgregar = (Ticket) elemento;
            listaClasificar.addRow(new Object[]{aAgregar.getFecha_Hora().toString(), aAgregar.getCliente().toString(), aAgregar.getAsunto().toString()});
        }
    }
    
    private void addToTableCategorized(){
        DefaultTableModel listaAtender = (DefaultTableModel) this.viewMainPanel.getTableAtender().getModel();
        Ticket aAgregar;
        for (Object elemento : model.obtenerTiquetesSinAtender() ){
            aAgregar = (Ticket) elemento;
            listaAtender.addRow(new Object[]{aAgregar.getFecha_Hora().toString(), aAgregar.getCliente().toString(), aAgregar.getCategoria().toString(), aAgregar.getEstado().toString(), aAgregar.getAsunto().toString()});
        }
    }
    
    private void clearTable(JTable tableToClear){
        DefaultTableModel lista = (DefaultTableModel) tableToClear.getModel();
        int rowCount = lista.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            lista.removeRow(i);
        }
    }
    
    private int getTableSelection(JTable tableToCheck){
        return tableToCheck.getSelectedRow();
    }
    
    private void classifyTicket(){
        int position = getTableSelection(this.viewMainPanel.getTableClasificar());
        if (position == -1){
            JOptionPane.showMessageDialog(this.viewMainPanel, "Elemento no seleccionado","Acción no válida",JOptionPane.ERROR_MESSAGE);
        } else {
            int row = this.viewMainPanel.getTableClasificar().getSelectedRow();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse((CharSequence) this.viewMainPanel.getTableClasificar().getValueAt(row, 0), formatter);
            String cliente = (String) this.viewMainPanel.getTableClasificar().getValueAt(row, 1);
            String descripcion = (String) this.viewMainPanel.getTableClasificar().getValueAt(row, 2);
            Categoria categoria = Categoria.valueOf(this.viewMainPanel.getCmbboxEstados().getSelectedItem().toString());
            Ticket tick = new Ticket(fecha, cliente, Estado.SINATENDER,  categoria, descripcion);
            model.asignarCategoriaATicket(tick);
            
            clearTable(this.viewMainPanel.getTableClasificar());
            clearTable(this.viewMainPanel.getTableAtender());
            addToTableNonCategorized();
            addToTableCategorized();
        }
    }
    
    
}
