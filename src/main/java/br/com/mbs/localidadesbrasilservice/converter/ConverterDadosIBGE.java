package br.com.mbs.localidadesbrasilservice.converter;

import org.springframework.stereotype.Component;

import br.com.mbs.localidadesbrasilservice.entidades.Localidades;
import br.com.mbs.localidadesbrasilservice.integration.ibge.vo.Mesorregiao;
import br.com.mbs.localidadesbrasilservice.integration.ibge.vo.Microrregiao;
import br.com.mbs.localidadesbrasilservice.integration.ibge.vo.Municipio;
import br.com.mbs.localidadesbrasilservice.integration.ibge.vo.Regiao;
import br.com.mbs.localidadesbrasilservice.integration.ibge.vo.UF;

@Component
public class ConverterDadosIBGE {
	// TODO fazer um builder
	public Municipio toMunicipio(Localidades localidade) {
		Municipio municipio = new Municipio();
		municipio.setNome(localidade.getNomeCidade());		
		UF uf = new UF();
		uf.setId(localidade.getIdEstado().toString());
		uf.setSigla(localidade.getSiglaEstado());
		Regiao regiao = new Regiao();
		regiao.setNome(localidade.getRegiaoNome());		
		Mesorregiao mesorrgiao = new Mesorregiao();
		mesorrgiao.setNome(localidade.getNomeMesorregiao());
		mesorrgiao.setUF(uf);
		Microrregiao microRegiao = new Microrregiao();
		microRegiao.setMesorregiao(mesorrgiao);
		municipio.setMicrorregiao(microRegiao);
		
		
		return municipio;
	}
	
	public Localidades toLocalidades(Municipio municipio) {
		Localidades ret = new Localidades();
		Microrregiao microrregiaoVo = municipio.getMicrorregiao();
		Mesorregiao mesorregiaoVo = microrregiaoVo.getMesorregiao();
		UF uFVo = mesorregiaoVo.getUF();
		Regiao regiaoVo = uFVo.getRegiao();		
		
		ret.setIdEstado( Integer.parseInt(uFVo.getId()));
		ret.setNomeCidade(municipio.getNome());
		ret.setNomeMesorregiao(microrregiaoVo.getNome());
		ret.setRegiaoNome(regiaoVo.getNome());
		ret.setSiglaEstado( uFVo.getSigla() );
		return ret;
	}
	
}
