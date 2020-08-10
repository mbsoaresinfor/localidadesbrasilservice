package br.com.mbs.localidadesbrasilservice.batch.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.mbs.localidadesbrasilservice.service.EmailService;
import br.com.mbs.localidadesbrasilservice.vo.Email;




public class WriterEmail implements ItemWriter<Email> {

		
	@Autowired
	private EmailService emailService;
	
	@Override
	public void write(List<? extends Email> items) throws Exception {
		
		items.forEach(e -> emailService.envia(e));
	}

	

	

}
