package com.Tienda.CRUD.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * La clase Producto representa un producto dentro del sistema de la tienda.
 * Contiene información como el nombre, descripción, imagen, precio y cantidad 
 * disponible. También establece una relación con la entidad Usuario, que indica 
 * el propietario del producto.
 * 
 * Atributos:
 * - id: Identificador único del producto.
 * - nombre: Nombre del producto.
 * - descripcion: Descripción breve del producto.
 * - imagen: URL o ruta de la imagen asociada al producto.
 * - precio: Precio unitario del producto.
 * - cantidad: Cantidad disponible en inventario.
 * - usuario: Usuario propietario del producto.
 * 
 * Métodos:
 * - Métodos getter y setter para cada uno de los atributos.
 * - Método toString para obtener una representación en texto del objeto.
 */
@Entity
@Table (name = "productos")
public class Producto {
    /**
     * Identificador único del producto. Es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre del producto.
     */
    private String nombre;

    /**
     * Descripción breve del producto.
     */
    private String descripcion;

    /**
     * URL o ruta de la imagen asociada al producto.
     */
    private String imagen;

    /**
     * Precio unitario del producto.
     */
    private double precio;

    /**
     * Cantidad disponible en inventario.
     */
    private int cantidad;

    /**
     * Usuario propietario del producto. 
     * Representa una relación de muchos a uno con la entidad Usuario.
     */
    
    @ManyToOne
    private Usuario usuario;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Producto() {
    }

    /**
     * Constructor completo con parámetros.
     * 
     * @param id Identificador único del producto.
     * @param nombre Nombre del producto.
     * @param descripcion Descripción breve del producto.
     * @param imagen URL o ruta de la imagen asociada al producto.
     * @param precio Precio unitario del producto.
     * @param cantidad Cantidad disponible en inventario.
     * @param usuario Usuario propietario del producto.
     */
    public Producto(Integer id, String nombre, String descripcion, String imagen, double precio, int cantidad, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.cantidad = cantidad;
        this.usuario = usuario;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    /**
     * Devuelve una representación en texto del objeto Producto.
     * 
     * @return Representación en texto del objeto.
     */
    @Override
    public String toString() {
        return "producto{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen=" + imagen + ", precio=" + precio + ", cantidad=" + cantidad + '}';
    }

    
}
