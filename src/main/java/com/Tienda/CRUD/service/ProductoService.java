package com.Tienda.CRUD.service;

import com.Tienda.CRUD.model.Producto;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz para la gestión de productos en el sistema.
 * Define los métodos principales para realizar operaciones CRUD sobre productos.
 */
public interface ProductoService {

    /**
     * Guarda un nuevo producto en la base de datos.
     * Si el producto ya existe, lo actualiza.
     *
     * @param producto El producto a guardar o actualizar.
     * @return El producto guardado o actualizado.
     */
    public Producto save(Producto producto);

    /**
     * Recupera un producto específico por su identificador.
     *
     * @param id El identificador único del producto.
     * @return Un {@link Optional} que contiene el producto si se encuentra,
     *         o vacío si no existe.
     */
    public Optional<Producto> get(Integer id);

    /**
     * Actualiza la información de un producto existente.
     *
     * @param producto El producto con la información actualizada.
     */
    public void update(Producto producto);

    /**
     * Elimina un producto de la base de datos por su identificador.
     *
     * @param id El identificador único del producto a eliminar.
     */
    public void delete(Integer id);

    /**
     * Recupera una lista de todos los productos disponibles.
     *
     * @return Una lista con todos los productos.
     */
    public List<Producto> findAll();
}
