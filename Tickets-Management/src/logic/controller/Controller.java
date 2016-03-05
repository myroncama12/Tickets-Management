/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.Engine;

/**
 *
 * @author myron
 */
public class Controller implements ActionListener {
    
    private Engine model;
    
    public Controller(Engine pEngine){
        model=pEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
