package com.Tienda.CRUD.service;

import com.Tienda.CRUD.model.Producto;
import com.Tienda.CRUD.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio para gestionar operaciones relacionadas con la entidad Producto.
 * Implementa la interfaz ProductoService, proporcionando la lógica de negocio.
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    // Inyección de dependencia para interactuar con el repositorio de productos.
   @Autowired
	private ProductoRepository productoRepository;

   
    /**
    * Guarda un producto en la base de datos.
    * Si el producto ya existe, lo actualiza.
    *
    * @param producto Objeto Producto a guardar.
    * @return Producto guardado o actualizado.
    */
	@Override
	public Producto save(Producto producto) {
		return productoRepository.save(producto);
	}
        
    /**
    * Obtiene un producto por su ID.
    *
    * @param id Identificador del producto.
    * @return Optional que contiene el producto si se encuentra.
    *//**
    * Obtiene un producto por su ID.
    *
    * @param id Identificador del producto.
    * @return Optional que contiene el producto si se encuentra.
    */
	@Override
	public Optional<Producto> get(Integer id) {
		return productoRepository.findById(id);
	}

    /**
    * Actualiza un producto en la base de datos.
    * 
    * @param producto Objeto Producto actualizado.
    *//**
    * Actualiza un producto en la base de datos.
    * 
    * @param producto Objeto Producto actualizado.
    */
	@Override
	public void update(Producto producto) {
		productoRepository.save(producto);		
	}

    /**
    * Elimina un producto de la base de datos por su ID.
    *
    * @param id Identificador del producto a eliminar.
    */
	@Override
	public void delete(Integer id) {
		productoRepository.deleteById(id);		
	}

    /**
    * Obtiene una lista de todos los productos en la base de datos.
    *
    * @return Lista de productos.
    */
	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}
    
}
