package br.com.mbs.localidadesbrasilservice.service;

import java.util.Iterator;
import java.util.List;

import br.com.mbs.localidadesbrasilservice.entidades.Cliente;

public interface ClienteService {

	public void salvar(Cliente cliente) throws Exception;
	
	public void salvar(List<Cliente> clientes) throws Exception;
	
	public Cliente buscar(Long idCliente);
	
	public Iterable<Cliente> buscar();
}
