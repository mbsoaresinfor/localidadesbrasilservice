package br.com.mbs.localidadesbrasilservice;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import br.com.mbs.localidadesbrasilservice.entidades.Localidades;
import br.com.mbs.localidadesbrasilservice.service.ClienteService;
import br.com.mbs.localidadesbrasilservice.service.ClienteServiceImpl;
import br.com.mbs.localidadesbrasilservice.service.DadosIBGEService;
import br.com.mbs.localidadesbrasilservice.service.LocalidadeService;
import br.com.mbs.localidadesbrasilservice.converter.ConverterDadosIBGE;
import br.com.mbs.localidadesbrasilservice.entidades.Cliente;
import br.com.mbs.localidadesbrasilservice.integration.ibge.vo.Municipio;
import br.com.mbs.localidadesbrasilservice.repository.ClienteRepository;


@EnableBatchProcessing
@EnableCircuitBreaker
@SpringBootApplication
//@EnableAsync
public class StartService {

	private static final Logger LOG = Logger.getLogger(StartService.class);
	
	@Autowired
	private DadosIBGEService dadosIBGEService; 
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private LocalidadeService localidadeService;
	
	@Autowired
	private ConverterDadosIBGE converterDadosIBGE;
	
	@Value("${cliente.total}")
	private String clienteTotal;
	
	@Value("${cliente.email}")
	private String clienteEmail;
	
	public static void main(String[] args) {

		SpringApplication.run(StartService.class, args);
	}

	@Bean
    public CommandLineRunner run() {
      return (args) -> {
    	  try {
	    	   cadastraClientes();    		   
	    	   cadastraLocalidades();
	    	   
    	  }catch(Exception e) {
    		  LOG.info("NAO FOI POSSIVEL INICIAR O SERVICO\n"+e.getMessage());
    		  System.exit(-1);
    	  }
      };
    }

	private void cadastraLocalidades() throws Exception {
		List<Municipio> listaMuncipio = dadosIBGEService.buscaDados();
		   List<Localidades> listaLocalidades = new ArrayList<>();
		   listaMuncipio.forEach(m -> listaLocalidades.add(converterDadosIBGE.toLocalidades(m)));
		   localidadeService.salvar(listaLocalidades);
	}
	
	private void cadastraClientes() throws Exception {
		int totalCliente = Integer.parseInt(clienteTotal);
		List<Cliente> listaCliente = new ArrayList<Cliente>(totalCliente);
		for(int i=1; i <= totalCliente; i++) {
			Cliente cliente = new Cliente();
			cliente.setId(i);
			cliente.setNome("nome_"+i);
			cliente.setEmail(clienteEmail);
			listaCliente.add(cliente);
			
		}
		clienteService.salvar(listaCliente);
	}

}
