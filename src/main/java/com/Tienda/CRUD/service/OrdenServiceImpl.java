package com.Tienda.CRUD.service;

import com.Tienda.CRUD.model.Orden;
import com.Tienda.CRUD.model.Usuario;
import com.Tienda.CRUD.repository.OrdenRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación de la interfaz {@link OrdenService} para manejar las operaciones
 * relacionadas con las órdenes.
 * Proporciona métodos para guardar, buscar y generar números únicos para las órdenes.
 */
@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    /**
     * Guarda una orden en la base de datos.
     * Si la orden ya existe, la actualiza.
     * 
     * @param orden La orden a guardar.
     * @return La orden guardada o actualizada.
     */
    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    /**
     * Recupera todas las órdenes existentes en la base de datos.
     * 
     * @return Una lista con todas las órdenes.
     */
    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }
    
    /**
     * Genera un número único para identificar una nueva orden.
     * El número es incremental y se formatea con ceros a la izquierda.
     * 
     * @return Un número de orden único como cadena formateada. //000001
     */


    public String generarNumeroOrden() {
        int numero = 0;
        String numeroConcatenado = "";

        List<Orden> ordenes = findAll();

        List<Integer> numeros = new ArrayList<Integer>();

        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));

        if (ordenes.isEmpty()) {
            numero = 1;
        } else {
            numero = numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if (numero < 10) { //0000001000
            numeroConcatenado = "000000000" + String.valueOf(numero);
        } else if (numero < 100) {
            numeroConcatenado = "00000000" + String.valueOf(numero);
        } else if (numero < 1000) {
            numeroConcatenado = "0000000" + String.valueOf(numero);
        } else if (numero < 10000) {
            numeroConcatenado = "0000000" + String.valueOf(numero);
        }

        return numeroConcatenado;
    }

    /**
     * Recupera las órdenes asociadas a un usuario específico.
     * 
     * @param usuario El usuario para el que se buscarán las órdenes.
     * @return Una lista de órdenes asociadas al usuario.
     */
    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return ordenRepository.findByUsuario(usuario);
    }

    /**
     * Busca una orden específica por su identificador único.
     * 
     * @param id El identificador de la orden a buscar.
     * @return Un {@link Optional} que contiene la orden si se encuentra,
     *         o vacío si no existe.
     */
    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenRepository.findById(id);
    }

}
