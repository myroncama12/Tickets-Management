/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tickets.management;

import library.Categoria;

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
        Categoria C;
        for (Categoria d : Categoria.values()) {
            System.out.println(d);
        }

    }

}
