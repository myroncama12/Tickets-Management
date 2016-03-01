/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

/**
 *
 * @author Valakuth
 */
public class gDepartamento extends GestorGeneral{

    public gDepartamento() {
    }
    
    @Override
    public boolean agregar(Object o) {
        int where = lista.indexOf(o);
        if (where == -1)
            lista.add(o);
        return (where  == -1);     // si es -1 entonces si lo agrego..
    }
    
    @Override
    public boolean modificar(Object o){
        int where = lista.indexOf(o);
        if (where != -1){
            lista.set(where, o );
            return true;
        }
        return false;
    }
 
}
