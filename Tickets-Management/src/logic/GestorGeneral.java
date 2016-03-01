/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Valakuth
 */
public abstract class GestorGeneral {

    protected ArrayList<Object> lista;
    
    public ArrayList<Object> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Object> lista) {
        this.lista = lista;
    }
    
   
    
    public abstract boolean agregar(Object o);
    public abstract boolean modificar(Object o);
   
    
}
