package br.com.mbs.localidadesbrasilservice.batch.step;

import org.springframework.batch.item.ItemProcessor;

import br.com.mbs.localidadesbrasilservice.vo.Email;

public class ProcessorEmail implements ItemProcessor<Email, Email>{

	@Override
	public Email process(Email item) throws Exception {
	
		return item;
	}

}
