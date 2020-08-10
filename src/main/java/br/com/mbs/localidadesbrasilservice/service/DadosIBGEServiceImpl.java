package br.com.mbs.localidadesbrasilservice.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import br.com.mbs.localidadesbrasilservice.converter.ConverterDadosIBGE;
import br.com.mbs.localidadesbrasilservice.entidades.Localidades;
import br.com.mbs.localidadesbrasilservice.integration.ibge.vo.Municipio;

import br.com.mbs.localidadesbrasilservice.repository.LocalidadesRepository;


@Service
public class DadosIBGEServiceImpl implements DadosIBGEService {

	private static final Logger LOG = Logger.getLogger(DadosIBGEServiceImpl.class);
	
	 private static final String END_POINT_CONSULTA_MUNICIPIOS = 			
			 "https://servicodados.ibge.gov.br/api/v1/localidades/municipios";
	 
	 private final RestTemplate restTemplate;
	 
	 @Autowired
	 private LocalidadesRepository localidadesSimplesRepository;
	 
	 @Autowired
	 private ConverterDadosIBGE converterDadosIBGE;
	 
	
	 public DadosIBGEServiceImpl(RestTemplate rest) {
		  this.restTemplate = rest;
	 }

	
	  private  List<Municipio> buscaDadosError() throws Exception {
		  
		  LOG.error("***********************************************************************************");
		  LOG.error("Ocorreu um erro no acesso " + END_POINT_CONSULTA_MUNICIPIOS + "\nRealizando tratramento (circuit)");

		  boolean isVAzio = localidadesSimplesRepository.count() == 0;
		  if(isVAzio) {			  
			  LOG.error("Nao foi possivel realizar o tratamento\n");
			  throw new Exception ("Ocorreu um erro na busca dos dados IBGE");
		  }
		  
		  ArrayList<Municipio> ret = new ArrayList<>();
		  Iterator<Localidades> it = localidadesSimplesRepository.findAll().iterator();
		  while (it.hasNext()) {
	            ret.add(converterDadosIBGE.toMunicipio(it.next()));
	        }
		  LOG.info("Tratamento realizado com sucesso. Buscado dados no cache.\n");
		  
		  return ret;
	  }

	  
	  
	@HystrixCommand(fallbackMethod = "buscaDadosError",
		    commandProperties = {
		            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000"),
		        }) 
	
	
	@Override
	public List<Municipio> buscaDados()  throws Exception  {
		LOG.info("Buscando os dados no IBGE: " + END_POINT_CONSULTA_MUNICIPIOS);
		URI uri = URI.create(END_POINT_CONSULTA_MUNICIPIOS);
		
		Municipio[] entity  = this.restTemplate.getForObject(uri, Municipio[].class);
		
		List<Municipio> ret = Arrays.asList(entity);
		LOG.info("Dados buscados no IBGE com sucesso");
		return ret;
	}
	

}
