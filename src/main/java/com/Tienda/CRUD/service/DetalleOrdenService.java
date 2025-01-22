package com.Tienda.CRUD.service;

import com.Tienda.CRUD.model.DetalleOrden;

/**
 * Interfaz para el servicio de DetalleOrden.
 * Define las operaciones relacionadas con la entidad DetalleOrden.
 */
public interface DetalleOrdenService {
    /**
     * Guarda un objeto DetalleOrden en la base de datos.
     *
     * @param detalleorden Objeto DetalleOrden a guardar.
     * @return El DetalleOrden que se ha guardado.
     */
    DetalleOrden save (DetalleOrden detalleorden);
}
