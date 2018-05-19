package com.giomerito.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giomerito.cursomc.domain.Cidade;
import com.giomerito.cursomc.domain.Cliente;
import com.giomerito.cursomc.domain.Endereco;
import com.giomerito.cursomc.domain.enums.Perfil;
import com.giomerito.cursomc.domain.enums.TipoCliente;
import com.giomerito.cursomc.dto.ClienteDTO;
import com.giomerito.cursomc.dto.ClienteNewDTO;
import com.giomerito.cursomc.repositories.ClienteRepository;
import com.giomerito.cursomc.repositories.EnderecoRepository;
import com.giomerito.cursomc.security.UserSS;
import com.giomerito.cursomc.services.exceptions.AuthorizationException;
import com.giomerito.cursomc.services.exceptions.DataIntegrityException;
import com.giomerito.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired // esta dependência vai ser automaticamente instânciada pelo spring
	private ClienteRepository repo;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Cliente obj = repo.findOne(id);
		if (obj == null) { // Lança uma Exception
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}

	//Metodo salva um cliente com todos os datos obrigatórios (endereços e telefones)
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir por que há pedidos relacionados!");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	// Metodo de paginação
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// Metodo auxiliar para pegar um DTO e converter em uma entidade
	public Cliente fromDto(ClienteNewDTO newDTO) {
		Cliente cli = new Cliente(null, newDTO.getNome(), newDTO.getEmail(), newDTO.getCpfOuCnpj(), TipoCliente.toEnum(newDTO.getTipo()), pe.encode(newDTO.getSenha()));
		Cidade cid = new Cidade(newDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, newDTO.getLogradouro(), newDTO.getNumero(), newDTO.getComplemento(), newDTO.getBairro(), newDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(newDTO.getTelefone1());
		if(newDTO.getTelefone2() != null) {
			cli.getTelefones().add(newDTO.getTelefone2());
		}
		if(newDTO.getTelefone3() != null) {
			cli.getTelefones().add(newDTO.getTelefone3());
		}
		return cli;
	}

	// Metodo auxiliar para pegar um DTO e converter em uma entidade
	public Cliente fromDto(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
