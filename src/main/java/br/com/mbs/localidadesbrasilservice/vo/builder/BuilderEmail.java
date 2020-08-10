package br.com.mbs.localidadesbrasilservice.vo.builder;

import br.com.mbs.localidadesbrasilservice.arquivo.CriaFormataArquivo;
import br.com.mbs.localidadesbrasilservice.vo.Email;


public class BuilderEmail {

	private BuilderEmail() {}
	
	public static class Builder{
		
		private String para;
		private String titulo;
		private String corpo;	
		private  byte[] anexo;
		private String nomeAnexo;
		private String mimeTypeAnexo;
		
		public Builder(){
		}
		
		public Builder adicionaPara(String para){
			this.para = para;
			return this;
		}
		
		public Builder adicionaTitulo(String titulo){
			this.titulo = titulo;
			return this;
		}
		
		public Builder adicionaCorpo(String corpo){
			this.corpo = corpo;
			return this;
		}
		
		public Builder adicionaAnexo(CriaFormataArquivo<?> criaFormataArquivo){
			this.anexo = criaFormataArquivo.executa();
			return this;
		}
		
		public Builder adicionaNomeAnexo(String nomeAnexo){
			this.nomeAnexo = nomeAnexo;
			return this;
		}
		
		public Builder adicionaMimeTypeAnexo(String mimeTypeAnexo){
			this.mimeTypeAnexo = mimeTypeAnexo;
			return this;
		}
		
	
		
		public Email criaEmail() {
			Email email =new Email();
			valida();
			email.setAnexo(anexo);
			email.setCorpo(corpo);
			email.setNomeAnexo(nomeAnexo);
			email.setPara(para);
			email.setTitulo(titulo);
			email.setMimeTypeAnexo(mimeTypeAnexo);
			return email;
		}
		
		private void valida() {
			if(para == null || "".equals(para)) {
				throw new IllegalArgumentException("Campo Para não preenchido");
			}
		}
		
		
		
		
	}
	
	
}
