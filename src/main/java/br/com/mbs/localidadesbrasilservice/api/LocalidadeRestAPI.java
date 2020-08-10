package br.com.mbs.localidadesbrasilservice.api;

import java.util.List;
import java.util.concurrent.Executors;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.mbs.localidadesbrasilservice.service.DadosIBGEService;
import br.com.mbs.localidadesbrasilservice.service.DadosIBGEServiceImpl;
import br.com.mbs.localidadesbrasilservice.service.LocalidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController(value="API para manipulacao de localidades do Brasil")
@Api(description="Api de Localidades")
public class LocalidadeRestAPI {

	private static final Logger LOG = Logger.getLogger(LocalidadeRestAPI.class);
	
	@Autowired
	private LocalidadeService localidadeService;
	

	
	 @ApiOperation(value = "Envia as localidades por email para todos os clientes.\n"
	 		+ "Esse processo é assincrono.")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso no envio dos emails"),
			 			@ApiResponse(code = 405, message = "Problema no envio dos emails")})	 
	 @RequestMapping(value = "/enviaemail", method = RequestMethod.GET)	 
	  public ResponseEntity<String>  enviaEmail() {
		 ResponseEntity<String> ret = null;
		 try {
			 
			 executaenviaLocalidadesPorEmail();
			 ret = new ResponseEntity<>("Essa solicitação pode demorar muito, por favor aguarde<br>",			 		
					 HttpStatus.OK);
			 
		 }catch(Exception e) {
			 ret = new ResponseEntity<>(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
		 }
		 return ret;
	  }
	 
	 @ApiOperation(value = "Envia as localidades por email para um cliente")
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso no envio do email"),
			 				@ApiResponse(code = 405, message = "Problema no envio do email")})	 
	 @RequestMapping(value = "/enviaemail/{id}", method = RequestMethod.GET)	 
	  public ResponseEntity<String>  enviaEmail(@PathVariable Long id) {

		 ResponseEntity<String> ret = null;
		 try {
			 localidadeService.enviaLocalidadesPorEmail(id);
			 ret = ResponseEntity.ok().build();
		 }catch(Exception e) {
			 ret = new ResponseEntity<>(e.getMessage(),HttpStatus.METHOD_NOT_ALLOWED);
		 }
		 return ret;
	  }

	 private void executaenviaLocalidadesPorEmail() {
		 Runnable task2 = () -> { try {
			localidadeService.enviaLocalidadesPorEmail();
		} catch (Exception e) {			
			LOG.error(e);
		}};
		 		
		new Thread(task2).start();
	 }
	 
}
	 
	
	 
	 
	
