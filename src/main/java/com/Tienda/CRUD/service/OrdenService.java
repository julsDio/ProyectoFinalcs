/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Tienda.CRUD.service;

import com.Tienda.CRUD.model.Orden;
import com.Tienda.CRUD.model.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Usuario
 */
public interface OrdenService {
    List<Orden> findAll();
    Orden save (Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario (Usuario usuario);
    Optional<Orden> findById(Integer id);
}
