package br.com.mbs.localidadesbrasilservice.converter;

import org.junit.Assert;
import org.junit.Test;

import br.com.mbs.localidadesbrasilservice.entidades.Localidades;
import br.com.mbs.localidadesbrasilservice.integration.ibge.vo.Mesorregiao;
import br.com.mbs.localidadesbrasilservice.integration.ibge.vo.Microrregiao;
import br.com.mbs.localidadesbrasilservice.integration.ibge.vo.Municipio;
import br.com.mbs.localidadesbrasilservice.integration.ibge.vo.Regiao;
import br.com.mbs.localidadesbrasilservice.integration.ibge.vo.UF;

public class ConverterDadosIBGETest {

	ConverterDadosIBGE converter = new ConverterDadosIBGE();
	
	@Test(expected = Exception.class)
	public void testToLocalidades(){
		Municipio municipio = new Municipio();				
		
		Localidades localidade =  converter.toLocalidades(municipio);
		Assert.assertNotNull(localidade);				
	}
	
	@Test(expected = Exception.class)
	public void testToMunicipio(){
		Localidades localidades = new Localidades();
		Municipio municipio =  converter.toMunicipio(localidades);
		Assert.assertNotNull(municipio);				
	}
	
}
