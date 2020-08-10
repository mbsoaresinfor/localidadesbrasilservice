package br.com.mbs.localidadesbrasilservice.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.mbs.localidadesbrasilservice.entidades.Cliente;

public interface ClienteRepository  extends 
		CrudRepository<Cliente,Long> {

}
