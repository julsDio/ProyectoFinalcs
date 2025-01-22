/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.CRUD.repository;

import com.Tienda.CRUD.model.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para realizar operaciones CRUD sobre la entidad DetalleOrden.
 * Extiende JpaRepository para aprovechar las funcionalidades proporcionadas por Spring Data JPA.
 */
@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer>{
    // No es necesario declarar métodos adicionales, JpaRepository proporciona
    // todas las operaciones básicas como save(), findById(), findAll(), delete(), etc.
}
