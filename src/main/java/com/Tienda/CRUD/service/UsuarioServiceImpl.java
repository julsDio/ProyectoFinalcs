package com.Tienda.CRUD.service;

import com.Tienda.CRUD.model.Usuario;
import com.Tienda.CRUD.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación de la interfaz UsuarioService.
 * Proporciona los métodos para gestionar las operaciones relacionadas con la entidad Usuario.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Busca un usuario por su identificador único.
     *
     * @param id El identificador único del usuario.
     * @return Un Optional que contiene el Usuario si existe, o vacío si no se encuentra.
     */
    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    /**
     * Guarda un nuevo usuario o actualiza uno existente en la base de datos.
     *
     * @param usuario El objeto Usuario a guardar o actualizar.
     * @return El objeto Usuario guardado o actualizado.
     */
    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca un usuario por su dirección de correo electrónico.
     *
     * @param email El correo electrónico del usuario.
     * @return Un Optional que contiene el Usuario si existe, o vacío si no se encuentra.
     */
    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    /**
     * Obtiene una lista de todos los usuarios registrados.
     *
     * @return Una lista de objetos Usuario.
     */
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
}

