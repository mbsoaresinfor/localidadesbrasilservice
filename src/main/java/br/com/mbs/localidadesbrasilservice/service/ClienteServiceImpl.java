package br.com.mbs.localidadesbrasilservice.service;


import java.util.Iterator;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mbs.localidadesbrasilservice.entidades.ClienteNaoEncontrado;
import br.com.mbs.localidadesbrasilservice.StartService;
import br.com.mbs.localidadesbrasilservice.entidades.Cliente;
import br.com.mbs.localidadesbrasilservice.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	private static final Logger LOG =Logger.getLogger(ClienteServiceImpl.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void salvar(Cliente cliente) throws Exception {
		Cliente c = 	clienteRepository.save(cliente);
		LOG.info("Cliente adicionado: " + c);
	}

	@Override
	public Cliente buscar(Long idCliente) {
		return	clienteRepository.findById(idCliente).orElse(new ClienteNaoEncontrado());
	}

	@Override
	public void salvar(List<Cliente> clientes) throws Exception {
		clienteRepository.saveAll( clientes);
		LOG.info("Clientes adicionado com sucesso. " + clientes.size());
	}

	@Override
	public Iterable<Cliente> buscar() {
		
		return clienteRepository.findAll();
	}

}
