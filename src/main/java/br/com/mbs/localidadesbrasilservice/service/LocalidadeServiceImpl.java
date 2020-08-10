package br.com.mbs.localidadesbrasilservice.service;

import java.util.Iterator;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.mbs.localidadesbrasilservice.arquivo.PdfLocalidades;
import br.com.mbs.localidadesbrasilservice.entidades.Cliente;
import br.com.mbs.localidadesbrasilservice.entidades.ClienteNaoEncontrado;
import br.com.mbs.localidadesbrasilservice.entidades.Localidades;
import br.com.mbs.localidadesbrasilservice.repository.LocalidadesRepository;
import br.com.mbs.localidadesbrasilservice.vo.Email;
import br.com.mbs.localidadesbrasilservice.vo.builder.BuilderEmail;

@Service
public class LocalidadeServiceImpl implements LocalidadeService {

	private static final Logger LOG = Logger.getLogger(LocalidadeServiceImpl.class);
	
	@Autowired
	private LocalidadesRepository localidadesRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
    private JobLauncher jobLauncher;
 
    @Autowired
    private Job processJob;
	
	//@Async
	@Override
	public void enviaLocalidadesPorEmail() throws Exception {

		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(processJob, jobParameters);
		
	}

	@Override
	public void enviaLocalidadesPorEmail(Long idCliente) throws Exception {

		Cliente cliente = clienteService.buscar(idCliente);
		boolean naoExisteCliente = cliente  instanceof ClienteNaoEncontrado;
		if(naoExisteCliente) {
			throw new Exception("Não existe cliente com o id " + idCliente);
		}
		
		Iterator<Localidades> itLocalidades= localidadesRepository.findAll().iterator();

		Email email = new BuilderEmail.Builder().
				adicionaPara(cliente.getEmail()).
				adicionaAnexo(new PdfLocalidades(itLocalidades)).
				adicionaCorpo("localidades do brasil em anexo").
				adicionaTitulo("Localidades do Brasil").
				adicionaMimeTypeAnexo("application/pdf").
				adicionaNomeAnexo("localidades.pdf").criaEmail();
				
		
		emailService.envia(email);
	}

	
	
	@Override
	public Long salvar(Localidades localidade) throws Exception {

		Localidades localidadeBanco = localidadesRepository.save(localidade);
		LOG.info("Salvo com sucesso: " + localidadeBanco);
		return localidadeBanco.getId();
	}

	@Override
	public Iterable<Localidades> buscar() {
	
		return localidadesRepository.findAll();
	}

	@Override
	public void salvar(List<Localidades> localidades) throws Exception {
		
		localidadesRepository.saveAll(localidades);
		LOG.info("Salvo com sucesso um total de: " + localidades.size());
		
		
	}

}
