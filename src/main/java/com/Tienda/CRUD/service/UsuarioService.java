/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.CRUD.service;

import com.Tienda.CRUD.model.Usuario;
import java.util.List;
import java.util.Optional;



public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(Integer id);
    Usuario save(Usuario usuario);
    Optional <Usuario> findByEmail(String email);

}
