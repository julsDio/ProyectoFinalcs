package com.Tienda.CRUD.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * La clase DetalleOrden representa los detalles individuales de una orden.
 * Contiene información sobre el producto, cantidad, precio y total, además
 * de las relaciones con la orden a la que pertenece y el producto que detalla.
 * 
 * Atributos:
 * - id: Identificador único del detalle de la orden.
 * - nombre: Nombre del producto.
 * - cantidad: Cantidad del producto solicitada en la orden.
 * - precio: Precio unitario del producto.
 * - total: Total calculado para este detalle (cantidad * precio).
 * - orden: Orden a la que pertenece este detalle (relación @ManyToOne).
 * - producto: Producto al que hace referencia este detalle (relación @ManyToOne).
 * 
 * Métodos:
 * - Métodos getter y setter para cada atributo.
 * - Método toString para obtener una representación en texto del objeto.
 */
@Entity
@Table (name = "detalles")
public class DetalleOrden {
    /**
     * Identificador único del detalle de la orden, generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre del producto incluido en este detalle.
     */
    private String nombre;

    /**
     * Cantidad del producto solicitada en este detalle.
     */
    private double cantidad;

    /**
     * Precio unitario del producto.
     */
    private double precio;

    /**
     * Total calculado para este detalle (cantidad * precio).
     */
    private double total;

    /**
     * Orden a la que pertenece este detalle.
     * Relación de muchos a uno con la entidad Orden.
     */
    @ManyToOne
    private Orden orden;
    
    /**
     * Producto al que hace referencia este detalle.
     * Relación de muchos a uno con la entidad Producto.
     */
    @ManyToOne
    private Producto producto;

    
     /**
     * Constructor completo con parámetros.
     * 
     * @param id Identificador único del detalle de la orden.
     * @param nombre Nombre del producto.
     * @param cantidad Cantidad del producto solicitada.
     * @param precio Precio unitario del producto.
     * @param total Total calculado para este detalle.
     */
    public DetalleOrden(Integer id, String nombre, double cantidad, double precio, double total) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    /**
     * Constructor vacío requerido por JPA.
     */
    public DetalleOrden() {
    }

    // Métodos getter y setter para cada atributo

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    

    /**
     * Devuelve una representación en texto del objeto DetalleOrden.
     * 
     * @return Representación en texto del objeto.
     */
    @Override
    public String toString() {
        return "DetalleOrden{" + "id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + ", total=" + total + '}';
    }
   
}
