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

/**
 * Controlador para gestionar las vistas y operaciones relacionadas con la administración
 * de productos, usuarios y órdenes.
 */
@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    /**
     * Servicio para gestionar operaciones relacionadas con productos.
     */
    @Autowired
    private ProductoService productoService;
    
    /**
     * Servicio para gestionar operaciones relacionadas con usuarios.
     */
    @Autowired
    private UsuarioService usuarioService;

    /**
     * Servicio para gestionar operaciones relacionadas con órdenes.
     */
    @Autowired
    private OrdenService ordenService;

    /**
     * Logger para registrar información y eventos en el controlador.
     */
    private Logger logg = LoggerFactory.getLogger(AdministradorController.class);

    /**
     * Muestra la página principal del administrador con una lista de productos.
     * 
     * @param model Modelo de datos utilizado para pasar información a la vista.
     * @return Nombre de la vista para la página principal del administrador.
     */
    @GetMapping("")
    public String home(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "administrador/home";
    }

    /**
     * Muestra una lista de usuarios en la vista correspondiente.
     * 
     * @param model Modelo de datos utilizado para pasar información a la vista.
     * @return Nombre de la vista para la página de usuarios.
     */
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "administrador/usuarios";
    }

    /**
     * Muestra una lista de órdenes en la vista correspondiente.
     * 
     * @param model Modelo de datos utilizado para pasar información a la vista.
     * @return Nombre de la vista para la página de órdenes.
     */
    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        model.addAttribute("ordenes", ordenService.findAll());
        return "administrador/ordenes";
    }

     /**
     * Muestra el detalle de una orden específica.
     * 
     * @param model Modelo de datos utilizado para pasar información a la vista.
     * @param id Identificador de la orden cuyo detalle se desea mostrar.
     * @return Nombre de la vista para la página de detalles de la orden.
     */
    @GetMapping("/detalle/{id}")
    public String detalle(Model model, @PathVariable Integer id) {
        logg.info("Id de la orden {}", id);
        Orden orden = ordenService.findById(id).get();
        model.addAttribute("detalles", orden.getDetalle());
        return "administrador/detalleorden";
    }

}
