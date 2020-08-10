package br.com.mbs.localidadesbrasilservice.batch.step;


import java.util.Iterator;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mbs.localidadesbrasilservice.arquivo.PdfLocalidades;

import br.com.mbs.localidadesbrasilservice.entidades.Cliente;
import br.com.mbs.localidadesbrasilservice.entidades.Localidades;
import br.com.mbs.localidadesbrasilservice.service.ClienteService;
import br.com.mbs.localidadesbrasilservice.service.LocalidadeService;
import br.com.mbs.localidadesbrasilservice.vo.Email;
import br.com.mbs.localidadesbrasilservice.vo.builder.BuilderEmail;


public class ReaderEmail implements ItemReader<Email> {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private LocalidadeService localidadeService;
	
	private Iterator<Cliente> itCliente;
	
	private Email email ;
	
	 @BeforeStep
	  public void before(StepExecution stepExecution) {
		// FIXME melhorar essa parte, pegar um por vez.
		 itCliente = clienteService.buscar().iterator();
	
		  email = new BuilderEmail.Builder().				
					adicionaAnexo(new PdfLocalidades(localidadeService.buscar().iterator())).
					adicionaPara("email_cliente").
					adicionaCorpo("localidades do brasil em anexo").
					adicionaTitulo("Localidades do Brasil").
					adicionaMimeTypeAnexo("application/pdf").
					adicionaNomeAnexo("localidades.pdf").criaEmail();
		 
	  }
	
	@Override
	public Email read() throws Exception {
				
		Cliente cliente = null;
		if( itCliente != null && itCliente.hasNext()) {
			cliente = itCliente.next();		
			email.setPara(cliente.getEmail());		
		}else {
			email = null;
		}
		
		return email;
	}
	
}
