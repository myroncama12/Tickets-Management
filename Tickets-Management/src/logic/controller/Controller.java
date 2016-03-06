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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import library.Categoria;
import library.Empleado;
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
            int indexEmpleado = this.viewMainPanel.getCmbboxAtendientes().getSelectedIndex();
            Empleado atender = (Empleado) model.getRepositorioDepartamento().getLista().get(indexEmpleado);
            attendTicket(atender);
        }
        
        if(e.getSource()==viewMainPanel.getBtnFinalizar()){
            completeTicket();
        }
        
        
        if(e.getSource()==viewMainPanel.getBtnEstado()){
            classifyTicket();
        }
        
        if(e.getSource()==viewMainPanel.getBtnEstadisticas1()){
            JOptionPane.showMessageDialog(this.viewMainPanel, model.distribucionTicketXCategoria(LocalDate.now()));
        }
        
        if(e.getSource()==viewMainPanel.getBtnEstadisticas2()){
            JOptionPane.showMessageDialog(this.viewMainPanel, model.porcentajeDeAtencionXEmpleados());
        }
        
        if(e.getSource()==viewMainPanel.getBtnEstadisticas3()){
            JOptionPane.showMessageDialog(this.viewMainPanel, model.tipoTicketMasRecibido());
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
            listaAtender.addRow(new Object[]{aAgregar.getFecha_Hora().toString(), aAgregar.getCliente().toString(), aAgregar.getCategoria().toString(), aAgregar.getAsunto().toString()});
        }
    }
    
    private void addToTableCategorized(Categoria category){
        DefaultTableModel listaAtender = (DefaultTableModel) this.viewMainPanel.getTableAtender().getModel();
        Ticket aAgregar;
        for (Object elemento : model.obtenerTiquetesSinAtender(category) ){
            aAgregar = (Ticket) elemento;
            listaAtender.addRow(new Object[]{aAgregar.getFecha_Hora().toString(), aAgregar.getCliente().toString(), aAgregar.getCategoria().toString(), aAgregar.getAsunto().toString()});
        }
    }
    
    private void addToTableEnd(){
        DefaultTableModel listaFinalizar = (DefaultTableModel) this.viewMainPanel.getTableFinalizar().getModel();
        Ticket aAgregar;
        for (Object elemento : model.obtenerTiquetesEnAtencion() ){
            aAgregar = (Ticket) elemento;
            listaFinalizar.addRow(new Object[]{aAgregar.getFecha_Hora().toString(), aAgregar.getCliente().toString(), aAgregar.getCategoria().toString(),aAgregar.getEmpleado().getNombre().toString(), aAgregar.getAsunto().toString()});
        }
    }
    
    private void clearTable(JTable tableToClear){
        DefaultTableModel lista = (DefaultTableModel) tableToClear.getModel();
        int rowCount = lista.getRowCount();
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
    
    private void attendTicket(Empleado atendiente){
        int position = getTableSelection(this.viewMainPanel.getTableAtender());
        if (position == -1){
            JOptionPane.showMessageDialog(this.viewMainPanel, "Elemento no seleccionado","Acción no válida",JOptionPane.ERROR_MESSAGE);
        }
        int row = this.viewMainPanel.getTableAtender().getSelectedRow();
        Categoria categoriaTicket = Categoria.valueOf(this.viewMainPanel.getTableAtender().getValueAt(row, 2).toString());
        if (!atendiente.getCategoria().equals(categoriaTicket)){
            JOptionPane.showMessageDialog(this.viewMainPanel, "Atendiente seleccionado no puede atender esto","Acción no válida",JOptionPane.ERROR_MESSAGE);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse((CharSequence) this.viewMainPanel.getTableAtender().getValueAt(row, 0), formatter);
            String cliente = (String) this.viewMainPanel.getTableAtender().getValueAt(row, 1);
            String descripcion = (String) this.viewMainPanel.getTableAtender().getValueAt(row, 3);
            Ticket tick = new Ticket(fecha, cliente, Estado.ENATENCION,  categoriaTicket, descripcion);
            tick.setEmpleado(atendiente);
            model.asignarEmpleadoATicket(tick, atendiente);
            
            clearTable(this.viewMainPanel.getTableFinalizar());
            clearTable(this.viewMainPanel.getTableAtender());
            addToTableEnd();
            addToTableCategorized();
            System.out.println("Exitoso");
        }
    }
    
    private void completeTicket(){
        int position = getTableSelection(this.viewMainPanel.getTableFinalizar());
        if (position == -1){
            JOptionPane.showMessageDialog(this.viewMainPanel, "Elemento no seleccionado","Acción no válida",JOptionPane.ERROR_MESSAGE);
        } else {
            int row = this.viewMainPanel.getTableFinalizar().getSelectedRow();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse((CharSequence) this.viewMainPanel.getTableFinalizar().getValueAt(row, 0), formatter);
            String cliente = (String) this.viewMainPanel.getTableFinalizar().getValueAt(row, 1);
            Categoria categoriaTicket = Categoria.valueOf(this.viewMainPanel.getTableFinalizar().getValueAt(row, 2).toString());
            String descripcion = (String) this.viewMainPanel.getTableFinalizar().getValueAt(row, 4);
            System.out.println("Aqui todo está bien");
            Ticket tick = new Ticket(fecha, cliente, Estado.ATENDIDO, categoriaTicket, descripcion);
            model.finalizarAtencionTicket(tick);
            
            clearTable(this.viewMainPanel.getTableFinalizar());
            addToTableEnd();
            System.out.println("Exitoso");
        }
    }
    
    
}
