/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    private final Logger logger= LoggerFactory.getLogger(UsuarioController.class);
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private OrdenService ordenService;
    
    
    @GetMapping("/registro")
    public String create() {
        return "usuario/registro";
    }
    
    @PostMapping("/save")
    public String save(@ModelAttribute Usuario usuario) {
        logger.info("Usuario registro: {}", usuario);
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }
    
    @PostMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session) {
//        logger.info("Accesos : {}", usuario);
//
//        // Buscar usuario por correo electrónico
//        Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());
//        logger.info("Usuario de db: {}", user.orElse(null));
//
//        if (user.isPresent()) {
//            // Comparar las contraseñas directamente
//            if (user.get().getPassword().equals(usuario.getPassword())) {
//                session.setAttribute("idusuario", user.get().getId());
//
//                // Redirigir según el tipo de usuario
//                if (user.get().getTipo().equals("ADMIN")) {
//                    return "redirect:/administrador";
//                } else {
//                    return "redirect:/";
//                }
//            } else {
//                logger.info("Contraseña incorrecta");
//                return "redirect:/usuario/login";  // Contraseña incorrecta, redirige a login
//            }
//        } else {
//            logger.info("Usuario no existe");
//            return "redirect:/usuario/login";  // Usuario no encontrado, redirige a login
//        }
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
    
    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
        logger.info("Id de la orden: {}", id);
        Optional<Orden> orden = ordenService.findById(id);
        model.addAttribute("detalles", orden.get().getDetalle());
        //session
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/detallecompra";
    }
    
    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session) {
        session.removeAttribute("idusuario");
        return "redirect:/";
    }
  
}
