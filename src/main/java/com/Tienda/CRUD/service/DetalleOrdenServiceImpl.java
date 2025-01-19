/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.CRUD.service;

import com.Tienda.CRUD.model.DetalleOrden;
import com.Tienda.CRUD.repository.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService{
	
	@Autowired
	private DetalleOrdenRepository detalleOrdenRepository;

	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden);
	}    
}
