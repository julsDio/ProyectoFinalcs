package com.Tienda.CRUD.service;

import com.Tienda.CRUD.model.Orden;
import com.Tienda.CRUD.model.Usuario;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el servicio OrdenService.
 * Define los métodos que deben ser implementados para manejar la lógica de negocio relacionada con las órdenes.
 */
public interface OrdenService {
    
    /**
     * Recupera todas las órdenes almacenadas en el sistema.
     *
     * @return Una lista con todas las órdenes.
     */
    List<Orden> findAll();
    
    /**
     * Guarda una nueva orden en la base de datos.
     *
     * @param orden La orden a guardar.
     * @return La orden guardada, incluyendo cualquier información generada, como el ID.
     */
    Orden save(Orden orden);

    /**
     * Genera un número único para identificar una orden.
     * Este número puede ser usado para seguimiento o referencia de la orden.
     *
     * @return Un número único de orden en formato de cadena.
     */
    String generarNumeroOrden();

    /**
     * Busca todas las órdenes asociadas a un usuario específico.
     *
     * @param usuario El usuario cuyas órdenes se desean recuperar.
     * @return Una lista de órdenes asociadas al usuario.
     */
    List<Orden> findByUsuario(Usuario usuario);

    /**
     * Busca una orden específica por su ID.
     *
     * @param id El ID de la orden que se desea buscar.
     * @return Un objeto Optional que contiene la orden si existe, o está vacío si no se encuentra.
     */
    Optional<Orden> findById(Integer id);
}