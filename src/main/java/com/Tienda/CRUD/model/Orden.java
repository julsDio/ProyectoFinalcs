package com.Tienda.CRUD.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * La clase Orden representa una orden realizada en el sistema de la tienda.
 * Contiene información sobre la orden, como el número, la fecha de creación, 
 * la fecha de recepción, el total y las relaciones con el usuario que realizó 
 * la orden y los detalles de la misma.
 * 
 * Atributos:
 * - id: Identificador único de la orden.
 * - numero: Número único asignado a la orden.
 * - fechaCreacion: Fecha en que se creó la orden.
 * - fechaRecibida: Fecha en que la orden fue recibida.
 * - total: Total de la orden en términos monetarios.
 * - usuario: Usuario que realizó la orden (relación @ManyToOne).
 * - detalle: Lista de detalles asociados a la orden (relación @OneToMany).
 * 
 * Métodos:
 * - Métodos getter y setter para cada uno de los atributos.
 * - Método toString para obtener una representación en texto del objeto.
 */
@Entity
@Table(name = "ordenes")
public class Orden {
   /**
     * Identificador único de la orden, generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Número único asignado a la orden.
     */
    private String numero;

    /**
     * Fecha en que se creó la orden.
     */
    private Date fechaCreacion;

    /**
     * Fecha en que la orden fue recibida.
     */
    private Date fechaRecibida;

    /**
     * Total de la orden en términos monetarios.
     */
    private double total;

    /**
     * Usuario que realizó la orden.
     * Relación de muchos a uno con la entidad Usuario.
     */
    @ManyToOne
    private Usuario usuario;
     /**
     * Lista de detalles asociados a la orden.
     * Relación de uno a muchos con la entidad DetalleOrden.
     */
    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalle;

    /**
     * Constructor completo con parámetros.
     * 
     * @param id Identificador único de la orden.
     * @param numero Número único asignado a la orden.
     * @param fechaCreacion Fecha en que se creó la orden.
     * @param fechaRecibida Fecha en que la orden fue recibida.
     * @param total Total de la orden.
     */
    public Orden(Integer id, String numero, Date fechaCreacion, Date fechaRecibida, double total) {
        this.id = id;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.fechaRecibida = fechaRecibida;
        this.total = total;
    }

    /**
     * Constructor vacío requerido por JPA.
     */
    public Orden() {
    }

    // Métodos getter y setter para cada atributo

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaRecibida() {
        return fechaRecibida;
    }

    public void setFechaRecibida(Date fechaRecibida) {
        this.fechaRecibida = fechaRecibida;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleOrden> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleOrden> detalle) {
        this.detalle = detalle;
    }

    /**
     * Devuelve una representación en texto del objeto Orden.
     * 
     * @return Representación en texto del objeto.
     */
    @Override
    public String toString() {
        return "Orden{" + "id=" + id + ", numero=" + numero + ", fechaCreacion=" + fechaCreacion + ", fechaRecibida=" + fechaRecibida + ", total=" + total + '}';
    }
    
}
