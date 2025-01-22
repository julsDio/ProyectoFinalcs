/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.CRUD.repository;

import com.Tienda.CRUD.model.Orden;
import com.Tienda.CRUD.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para realizar operaciones CRUD sobre la entidad Orden.
 * Extiende JpaRepository para aprovechar las funcionalidades proporcionadas por Spring Data JPA.
 * También se incluye un método personalizado para encontrar las órdenes de un usuario específico.
 */
@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    
    /**
     * Método personalizado para obtener todas las órdenes de un usuario específico.
     * @param usuario El usuario cuyas órdenes se desean obtener.
     * @return Una lista de órdenes asociadas al usuario.
     */
    List<Orden> findByUsuario (Usuario usuario);
    
}
