package com.Tienda.CRUD.service;

import com.Tienda.CRUD.model.DetalleOrden;
import com.Tienda.CRUD.repository.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio DetalleOrdenService.
 * Proporciona la lógica necesaria para interactuar con el repositorio de DetalleOrden.
 */
@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService{
    
    // Inyección de dependencia para el repositorio DetalleOrdenRepository.
    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    /**
     * Guarda un objeto DetalleOrden en la base de datos.
     *
     * @param detalleOrden El objeto DetalleOrden que se desea guardar.
     * @return El objeto DetalleOrden que fue guardado, con su ID generado si es nuevo.
     */
    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        // Llama al método save del repositorio para persistir el objeto en la base de datos.
        return detalleOrdenRepository.save(detalleOrden);
    } 
}
