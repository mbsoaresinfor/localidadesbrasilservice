package br.com.mbs.localidadesbrasilservice.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.mbs.localidadesbrasilservice.entidades.Cliente;
import br.com.mbs.localidadesbrasilservice.entidades.Localidades;

public interface LocalidadesRepository  extends 
		CrudRepository<Localidades,Long> {

}
