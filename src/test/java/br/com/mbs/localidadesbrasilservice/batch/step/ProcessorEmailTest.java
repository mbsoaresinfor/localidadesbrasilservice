package br.com.mbs.localidadesbrasilservice.batch.step;

import org.junit.Test;

import br.com.mbs.localidadesbrasilservice.vo.Email;
import junit.framework.Assert;

public class ProcessorEmailTest {

	@Test
	public void testReturn() throws Exception {
		ProcessorEmail processorEmail = new ProcessorEmail();
		Email email = new Email();
		Assert.assertEquals(email, processorEmail.process(email));
	}
}
