package com.giomerito.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.giomerito.cursomc.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	//Esta interface fará todas as operações referente a classe passada e o Id
	
	@Transactional(readOnly=true)
	public List<Estado> findAllByOrderByNome();
}
