package br.com.mbs.localidadesbrasilservice.service;

import java.util.List;

import br.com.mbs.localidadesbrasilservice.entidades.Localidades;

public interface LocalidadeService {

	
	public void enviaLocalidadesPorEmail() throws Exception ;
	
	public void enviaLocalidadesPorEmail(Long idCliente) throws Exception;
	
	public Long salvar(Localidades localidade) throws Exception;
	
	public void salvar(List<Localidades> localidades) throws Exception;
	
	public Iterable<Localidades> buscar();
	
}
