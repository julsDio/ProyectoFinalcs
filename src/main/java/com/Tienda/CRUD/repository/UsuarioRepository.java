/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.CRUD.repository;

import com.Tienda.CRUD.model.Usuario;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para realizar operaciones CRUD sobre la entidad Usuario.
 * Extiende JpaRepository para aprovechar las funcionalidades proporcionadas por Spring Data JPA.
 */
@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer>{
    
    /**
     * Método personalizado para encontrar un usuario por su correo electrónico.
     * 
     * @param Email Correo electrónico del usuario.
     * @return Un Optional que puede contener el usuario encontrado.
     */
    Optional <Usuario> findByEmail(String Email);
    
}
