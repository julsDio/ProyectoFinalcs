package com.Tienda.CRUD.controller;

import com.Tienda.CRUD.model.DetalleOrden;
import com.Tienda.CRUD.model.Orden;
import com.Tienda.CRUD.model.Producto;
import com.Tienda.CRUD.model.Usuario;
import com.Tienda.CRUD.service.DetalleOrdenService;
import com.Tienda.CRUD.service.OrdenService;
import com.Tienda.CRUD.service.ProductoService;
import com.Tienda.CRUD.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador principal para gestionar las vistas y operaciones de la tienda,
 * como mostrar productos, añadir productos al carrito, realizar pedidos y realizar búsquedas.
 */
@Controller
@RequestMapping("/")
public class HomeController {
    
    /**
     * Logger para registrar información y eventos en el controlador.
     */
    private final Logger log = LoggerFactory.getLogger(HomeController.class);
    
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
     * Servicio para gestionar operaciones relacionadas con los detalles de las órdenes.
     */
    @Autowired
    private DetalleOrdenService detalleOrdenService;
    
    /**
     * Lista que almacena los detalles de los productos añadidos al carrito.
     */
    List<DetalleOrden> detalles = new ArrayList<>(); 
    
    /**
     * Objeto de tipo Orden que contiene la información del pedido en curso.
     */
    Orden orden = new Orden();

    /**
     * Muestra la página de inicio de la tienda, con todos los productos disponibles.
     * 
     * @param model Modelo de datos utilizado para pasar información a la vista.
     * @param session Sesión actual del usuario.
     * @return Nombre de la vista para la página principal del usuario.
     */
    @GetMapping("")
    public String home(Model model, HttpSession session) {
        log.info("Sesion del usuario: {}", session.getAttribute("idusuario"));

        model.addAttribute("productos", productoService.findAll());

        // Añadir la sesión del usuario al modelo para mostrarla en la vista
        model.addAttribute("sesion", session.getAttribute("idusuario"));

        return "usuario/home";
    }
    
    /**
     * Muestra la página con detalles de un producto específico.
     * 
     * @param id Identificador del producto.
     * @param model Modelo de datos utilizado para pasar información a la vista.
     * @return Nombre de la vista para la página del producto.
     */
    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        log.info("Id producto enviado como parámetro {}", id);
        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();

        model.addAttribute("producto", producto);

        return "usuario/productohome";
    }

    /**
     * Añade un producto al carrito de compras.
     * 
     * @param id Identificador del producto a añadir.
     * @param cantidad Cantidad del producto a añadir.
     * @param model Modelo de datos utilizado para pasar información a la vista.
     * @return Nombre de la vista para el carrito de compras.
     */
    @PostMapping("/cart")
    public String addcart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;
        
        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("Producto añadido: {}", optionalProducto.get());
        log.info("Cantidad: {}", cantidad);
        producto = optionalProducto.get();
        
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);
        
        // Validar que el producto no se añada dos veces al carrito
        Integer idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        if (!ingresado) {
            detalles.add(detalleOrden);
        }
        
        // Calcular el total del carrito
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        
        return "usuario/carrito";
    }

    /**
     * Elimina un producto del carrito de compras.
     * 
     * @param id Identificador del producto a eliminar.
     * @param model Modelo de datos utilizado para pasar información a la vista.
     * @return Nombre de la vista para el carrito de compras actualizado.
     */
    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable Integer id, Model model) {
        // Crear una nueva lista sin el producto a eliminar
        List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

        for (DetalleOrden detalleOrden : detalles) {
            if (detalleOrden.getProducto().getId() != id) {
                ordenesNueva.add(detalleOrden);
            }
        }

        // Asignar la nueva lista al carrito
        detalles = ordenesNueva;

        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    /**
     * Muestra el carrito de compras actual.
     * 
     * @param model Modelo de datos utilizado para pasar información a la vista.
     * @param session Sesión actual del usuario.
     * @return Nombre de la vista para la página del carrito.
     */
    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        
        // Añadir la sesión del usuario al modelo para mostrarla en la vista
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "/usuario/carrito";
    }
    
    /**
     * Muestra la página de resumen del pedido.
     * 
     * @param model Modelo de datos utilizado para pasar información a la vista.
     * @param session Sesión actual del usuario.
     * @return Nombre de la vista para la página de resumen del pedido.
     */
    @GetMapping("/order")
    public String order(Model model, HttpSession session) {
        // Verificar si el usuario está logueado
        if (session.getAttribute("idusuario") == null) {
            return "redirect:usuario/login";
        }

        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);

        return "usuario/resumenorden";
    }

    /**
     * Guarda el pedido realizado, incluyendo la orden y los detalles del carrito.
     * 
     * @param session Sesión actual del usuario.
     * @return Redirección a la página principal.
     */
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session) {
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());

        // Asignar el usuario a la orden
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        orden.setUsuario(usuario);
        ordenService.save(orden);

        // Guardar los detalles de la orden
        for (DetalleOrden dt : detalles) {
            dt.setOrden(orden);
            detalleOrdenService.save(dt);
        }

        // Limpiar el carrito y la orden
        orden = new Orden();
        detalles.clear();

        return "redirect:/";
    }

    /**
     * Realiza una búsqueda de productos por nombre.
     * 
     * @param nombre Nombre del producto a buscar.
     * @param model Modelo de datos utilizado para pasar información a la vista.
     * @return Nombre de la vista para mostrar los resultados de la búsqueda.
     */
    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model) {
        log.info("Nombre del producto: {}", nombre);
        List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
        model.addAttribute("productos", productos);
        return "usuario/home";
    }
}
