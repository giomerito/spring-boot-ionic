package com.giomerito.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giomerito.cursomc.domain.Categoria;
import com.giomerito.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired //esta dependência vai ser automaticamente instânciada pelo spring
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		return obj;
	}
	
}
