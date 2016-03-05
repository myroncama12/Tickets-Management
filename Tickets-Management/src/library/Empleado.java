/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.Objects;

/**
 *
 * @author myron
 */
public class Empleado {

    private String nombre;
    private String codigo;
    private Categoria categoria;
    private Boolean esSupervisor;

    public Empleado(String nombre, Categoria categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Boolean getEsSupervisor() {
        return esSupervisor;
    }

    public void setEsSupervisor(Boolean esSupervisor) {
        this.esSupervisor = esSupervisor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (this.categoria != other.categoria) {
            return false;
        }
        if (!Objects.equals(this.esSupervisor, other.esSupervisor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + ", codigo=" + codigo + ", categoria=" + categoria + ", esSupervisor=" + esSupervisor + "}\ns";
    } 
    
}
