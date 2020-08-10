package br.com.mbs.localidadesbrasilservice;

import org.junit.Test;

import br.com.mbs.localidadesbrasilservice.vo.Email;
import br.com.mbs.localidadesbrasilservice.vo.builder.BuilderEmail;

public class BuilderEmailTest {

	@Test
	public void builder() {
		Email email = new BuilderEmail.Builder().adicionaPara("teste@gmmil.com").criaEmail();
	}
	
	@Test(expected = Exception.class)
	public void builderComError() {
		Email email = new BuilderEmail.Builder().criaEmail();
	}
}
