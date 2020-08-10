package br.com.mbs.localidadesbrasilservice.service;

import java.util.List;

import br.com.mbs.localidadesbrasilservice.integration.ibge.vo.Municipio;



public interface DadosIBGEService {

	//public List<LocalidadeBaseVo> consultaMunicipios() throws Exception;
	
	public List<Municipio> buscaDados()  throws Exception ;
	
}
