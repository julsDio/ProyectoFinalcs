/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.CRUD.controller;

import com.Tienda.CRUD.model.Orden;
import com.Tienda.CRUD.model.Producto;
import com.Tienda.CRUD.service.OrdenService;
import com.Tienda.CRUD.service.ProductoService;
import com.Tienda.CRUD.service.UsuarioService;
import java.util.List;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OrdenService ordensService;

    private Logger logg = LoggerFactory.getLogger(AdministradorController.class);

    @GetMapping("")
    public String home(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "administrador/home";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "administrador/usuarios";
    }

    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        model.addAttribute("ordenes", ordensService.findAll());
        return "administrador/ordenes";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(Model model, @PathVariable Integer id) {
        logg.info("Id de la orden {}", id);
        Orden orden = ordensService.findById(id).get();
        model.addAttribute("detalles", orden.getDetalle());
        return "administrador/detalleorden";
    }

}
