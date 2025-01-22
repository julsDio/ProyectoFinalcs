package com.Tienda.CRUD.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 * La clase Usuario representa un usuario dentro del sistema de la tienda.
 * Contiene información relevante como nombre, email, dirección, contraseña y 
 * el tipo de usuario. Además, establece relaciones con las entidades Producto y Orden.
 * 
 * Atributos:
 * - id: Identificador único del usuario.
 * - nombre: Nombre completo del usuario.
 * - email: Correo electrónico del usuario.
 * - direccion: Dirección física del usuario.
 * - password: Contraseña del usuario para acceder al sistema.
 * - tipo: Tipo de usuario (por ejemplo, administrador o cliente).
 * - productos: Lista de productos asociados al usuario.
 * - ordenes: Lista de órdenes realizadas por el usuario.
 * 
 * Métodos:
 * - Métodos getter y setter para cada uno de los atributos.
 * - Método toString para obtener una representación en texto del objeto.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {
    /**
     * Identificador único del usuario. Es generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Nombre completo del usuario.
     */
    private String nombre;

    /**
     * Correo electrónico del usuario.
     */
    private String email;

    /**
     * Dirección física del usuario.
     */
    private String direccion;

    /**
     * Contraseña del usuario para acceder al sistema.
     */
    private String password;

    /**
     * Tipo de usuario, como administrador o cliente.
     */
    private String tipo;

    /**
     * Lista de productos asociados al usuario.
     */
    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;

    /**
     * Lista de órdenes realizadas por el usuario.
     */
    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;

    /**
     * Constructor completo con parámetros.
     * 
     * @param id Identificador único del usuario.
     * @param nombre Nombre completo del usuario.
     * @param email Correo electrónico del usuario.
     * @param direccion Dirección física del usuario.
     * @param password Contraseña del usuario.
     * @param tipo Tipo de usuario.
     * @param productos Lista de productos asociados al usuario.
     * @param ordenes Lista de órdenes realizadas por el usuario.
     */
    public Usuario(Integer id, String nombre, String email, String direccion, String password, String tipo, List<Producto> productos, List<Orden> ordenes) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.password = password;
        this.tipo = tipo;
        this.productos = productos;
        this.ordenes = ordenes;
    }

    /**
     * Constructor vacío requerido por JPA.
     */
    public Usuario() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Orden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<Orden> ordenes) {
        this.ordenes = ordenes;
    }

    /**
     * Devuelve una representación en texto del objeto Usuario.
     * 
     * @return Representación en texto del objeto.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", password='" + password + '\'' +
                ", tipo='" + tipo + '\'' +
                ", productos=" + productos +
                ", ordenes=" + ordenes +
                '}';
    }
}