package com.Tienda.CRUD.controller;

import com.Tienda.CRUD.model.Producto;
import com.Tienda.CRUD.model.Usuario;
import com.Tienda.CRUD.service.ProductoService;
import com.Tienda.CRUD.service.UploadFileService;
import com.Tienda.CRUD.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controlador para manejar las operaciones relacionadas con los productos
 * de la tienda (crear, editar, eliminar y mostrar productos).
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    /**
     * Logger para registrar la información y los eventos del controlador.
     */
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    
    /**
     * Servicio que maneja las operaciones relacionadas con los productos.
     */
    @Autowired
    private ProductoService productoService;
    
    /**
     * Servicio que maneja las operaciones relacionadas con los usuarios.
     */
    @Autowired
    private UsuarioService usuarioService;
    
    /**
     * Servicio para cargar y gestionar archivos de imagen.
     */
    @Autowired
    private UploadFileService upload;

    /**
     * Muestra todos los productos disponibles en la tienda.
     * 
     * @param model Modelo que contiene la lista de productos a mostrar.
     * @return Nombre de la vista para mostrar los productos.
     */
    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("productos", productoService.findAll());    
        return "productos/show";
    }

    /**
     * Muestra el formulario para crear un nuevo producto.
     * 
     * @return Nombre de la vista para la creación de productos.
     */
    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }
    
    /**
     * Guarda un nuevo producto en la base de datos, incluyendo la imagen si es necesario.
     * 
     * @param producto Objeto Producto con los datos del nuevo producto.
     * @param file Imagen del producto a subir.
     * @param session Información de la sesión actual del usuario.
     * @return Redirección a la vista que muestra todos los productos.
     * @throws IOException Si ocurre un error al guardar la imagen.
     */
    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        LOGGER.info("Este es el objeto producto {}", producto);
        Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        producto.setUsuario(u);
        //imagen
        if (producto.getId() == null) { // cuando se crea un producto
            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        } else {

        }
        productoService.save(producto);
        return "redirect:/productos";
    }
    
    /**
     * Muestra el formulario para editar un producto existente.
     * 
     * @param id Identificador del producto a editar.
     * @param model Modelo para pasar el producto al formulario de edición.
     * @return Nombre de la vista para editar el producto.
     */
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable Integer id, Model model){
        Producto producto = new Producto();
        Optional<Producto> optionalProducto=productoService.get(id);
        producto= optionalProducto.get();
        LOGGER.info("Producto Buscado: {}",producto);
        model.addAttribute("producto", producto);
        return "productos/edit";
    }
    
    /**
     * Actualiza un producto en la base de datos, incluyendo la imagen si es necesario.
     * 
     * @param producto Objeto Producto con los datos actualizados.
     * @param file Imagen del producto a actualizar.
     * @return Redirección a la vista que muestra todos los productos.
     * @throws IOException Si ocurre un error al actualizar la imagen.
     */
    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        Producto p = new Producto();
        p = productoService.get(producto.getId()).get();

        if (file.isEmpty()) {
            producto.setImagen(p.getImagen());
        } else {//edita la imagen  
            if (!p.getImagen().equals("default.jpg")) {
                upload.deleteImage(p.getImagen());
            }

            String nombreImagen = upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(p.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";
    }
    
    /**
     * Elimina un producto de la base de datos y su imagen asociada si no es la imagen predeterminada.
     * 
     * @param id Identificador del producto a eliminar.
     * @return Redirección a la vista que muestra todos los productos.
     */
    @GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		Producto p = new Producto();
		p=productoService.get(id).get();
		//eliminar cuando no sea la imagen por defecto
		if (!p.getImagen().equals("default.jpg")) {
			upload.deleteImage(p.getImagen());
		}
		productoService.delete(id);
		return "redirect:/productos";
	}
    
  

}

