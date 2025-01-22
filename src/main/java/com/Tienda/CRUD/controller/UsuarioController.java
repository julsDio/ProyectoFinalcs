package com.Tienda.CRUD.controller;

import com.Tienda.CRUD.model.Orden;
import com.Tienda.CRUD.model.Usuario;
import com.Tienda.CRUD.service.OrdenService;
import com.Tienda.CRUD.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador para manejar las operaciones relacionadas con los usuarios de la tienda.
 * Incluye el registro, login, vista de compras y el cierre de sesión.
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    /**
     * Logger para registrar los eventos y la información del controlador.
     */
    private final Logger logger= LoggerFactory.getLogger(UsuarioController.class);
    
    /**
     * Servicio para manejar las operaciones relacionadas con los usuarios.
     */
    @Autowired
    private UsuarioService usuarioService;
    
    /**
     * Servicio para manejar las operaciones relacionadas con las órdenes de compra.
     */
    @Autowired
    private OrdenService ordenService;
    
    /**
     * Muestra el formulario de registro para un nuevo usuario.
     * 
     * @return Nombre de la vista para el registro de usuario.
     */
    @GetMapping("/registro")
    public String create() {
        return "usuario/registro";
    }
    
    /**
     * Guarda un nuevo usuario en la base de datos con el tipo "USER".
     * 
     * @param usuario El objeto Usuario con los datos del nuevo usuario.
     * @return Redirección a la página principal después de guardar el usuario.
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Usuario usuario) {
        logger.info("Usuario registro: {}", usuario);
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        return "redirect:/";
    }
    
    /**
     * Muestra el formulario de login para que el usuario ingrese a su cuenta.
     * 
     * @return Nombre de la vista para el login.
     */
    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }
    
    /**
     * Valida las credenciales del usuario y establece la sesión si son correctas.
     * Redirige al administrador si es un usuario con tipo "ADMIN", o a la página principal si es un "USER".
     * 
     * @param usuario Objeto Usuario con las credenciales de login.
     * @param session La sesión actual del usuario.
     * @return Redirección según el tipo de usuario o a la página principal.
     */
    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session) {
        logger.info("Accesos : {}", usuario);

        Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());
        logger.info("Usuario de db: {}", user.get());

        if (user.isPresent()) {
            session.setAttribute("idusuario", user.get().getId());

            if (user.get().getTipo().equals("ADMIN")) {
                return "redirect:/administrador";
            } else {
                return "redirect:/";
            }
        } else {
            logger.info("Usuario no existe");
        }

        return "redirect:/";
    }
    
    /**
     * Muestra la vista con las compras realizadas por el usuario.
     * 
     * @param model Modelo que contiene las compras del usuario.
     * @param session Información de la sesión actual.
     * @return Nombre de la vista para mostrar las compras.
     */
    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session) {
        if (session.getAttribute("idusuario") == null) {
            return "redirect:/usuario/login"; // Redirigir al login si no hay sesión activa
        }

        model.addAttribute("sesion", session.getAttribute("idusuario"));

        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).orElse(null);
        if (usuario == null) {
            return "redirect:/usuario/login"; // Redirigir si el usuario no existe
        }

        List<Orden> ordenes = ordenService.findByUsuario(usuario);
        logger.info("Ordenes {}", ordenes);

        model.addAttribute("ordenes", ordenes);
        return "usuario/compras";
    }
    
    /**
     * Muestra los detalles de una compra específica.
     * 
     * @param id Identificador de la orden de compra.
     * @param session Información de la sesión del usuario.
     * @param model Modelo que contiene los detalles de la compra.
     * @return Nombre de la vista para mostrar los detalles de la compra.
     */
    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
        logger.info("Id de la orden: {}", id);
        Optional<Orden> orden = ordenService.findById(id);
        model.addAttribute("detalles", orden.get().getDetalle());
        //session
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/detallecompra";
    }
    
    /**
     * Cierra la sesión del usuario y lo redirige a la página principal.
     * 
     * @param session La sesión actual del usuario.
     * @return Redirección a la página principal después de cerrar la sesión.
     */
    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session) {
        session.removeAttribute("idusuario");
        return "redirect:/";
    }
  
}
