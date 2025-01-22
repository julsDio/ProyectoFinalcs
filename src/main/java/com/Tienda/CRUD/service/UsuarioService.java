/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.CRUD.service;

import com.Tienda.CRUD.model.Usuario;
import java.util.List;
import java.util.Optional;


/**
 * Interfaz para el servicio de gestión de usuarios.
 * Define las operaciones básicas relacionadas con la entidad Usuario.
 */
public interface UsuarioService {
    
     /**
     * Obtiene una lista de todos los usuarios registrados.
     *
     * @return Una lista de objetos Usuario.
     */
    List<Usuario> findAll();

    /**
     * Busca un usuario por su identificador único.
     *
     * @param id El identificador único del usuario.
     * @return Un objeto Optional que contiene el Usuario si existe, o vacío si no se encuentra.
     */
    Optional<Usuario> findById(Integer id);

    /**
     * Guarda un nuevo usuario o actualiza uno existente en la base de datos.
     *
     * @param usuario El objeto Usuario a guardar o actualizar.
     * @return El objeto Usuario guardado o actualizado.
     */
    Usuario save(Usuario usuario);

    /**
     * Busca un usuario por su dirección de correo electrónico.
     *
     * @param email El correo electrónico del usuario.
     * @return Un objeto Optional que contiene el Usuario si existe, o vacío si no se encuentra.
     */
    Optional<Usuario> findByEmail(String email);

}
