/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author myron
 */
public class Ticket {

    private LocalDate fecha_Hora;
    private String cliente;
    private Estado estado;
    private Categoria categoria;
    private String asunto;
    private Empleado empleado;

    public Ticket(LocalDate fecha_Hora, String cliente, Estado estado, Categoria categoria, String asunto) {
        this.fecha_Hora = fecha_Hora;
        this.cliente = cliente;
        this.estado = estado;
        this.categoria = categoria;
        this.asunto = asunto;
    }

    public Ticket() {
    }

    public void setFecha_Hora(LocalDate fecha_Hora) {
        this.fecha_Hora = fecha_Hora;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public LocalDate getFecha_Hora() {
        return fecha_Hora;
    }

    public String getCliente() {
        return cliente;
    }

    public Estado getEstado() {
        return estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getAsunto() {
        return asunto;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }    

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Ticket other = (Ticket) obj;
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.asunto, other.asunto)) {
            return false;
        }
        if (!Objects.equals(this.fecha_Hora, other.fecha_Hora)) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (this.categoria != other.categoria) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ticket{" + "fecha_Hora=" + fecha_Hora + ", cliente=" + cliente + ", estado=" + estado + ", categoria=" + categoria + ", asunto=" + asunto + ", empleado=" + empleado + "}\n";
    }
}
