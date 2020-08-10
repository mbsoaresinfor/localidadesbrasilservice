package br.com.mbs.localidadesbrasilservice.entidades;

import org.junit.Assert;
import org.junit.Test;

public class ClienteNaoEncontradoTest {

	@Test
	public void testTypeObj() {
		ClienteNaoEncontrado obj = new ClienteNaoEncontrado();
		Assert.assertTrue(obj instanceof Cliente);
	}
	
}
