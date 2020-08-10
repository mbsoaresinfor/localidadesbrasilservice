package br.com.mbs.localidadesbrasilservice.vo;

import java.io.Serializable;

import br.com.mbs.localidadesbrasilservice.arquivo.CriaFormataArquivo;

public class Email implements Serializable {

	
	private static final long serialVersionUID = -3426097501681959725L;
	private String para;
	private String titulo;
	private String corpo;	
	private  byte[] anexo;
	private String nomeAnexo;
	private String mimeTypeAnexo;
	
	
	
	public String getMimeTypeAnexo() {
		return mimeTypeAnexo;
	}
	public void setMimeTypeAnexo(String tipoArquivo) {
		this.mimeTypeAnexo = tipoArquivo;
	}
	public byte[] getAnexo() {
		return anexo;
	}
	public String getCorpo() {
		return corpo;
	}
	public String getNomeAnexo() {
		return nomeAnexo;
	}
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	public void setAnexo(byte[] anexo) {
		this.anexo = anexo;
	}
	public void setNomeAnexo(String nomeAnexo) {
		this.nomeAnexo = nomeAnexo;
	}
	public String getPara() {
		return para;
	}
	public String getTitulo() {
		return titulo;
	}
	
	public void setPara(String para) {
		this.para = para;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Override
	public String toString() {
		return "Email [para=" + para + "]";
	}
	
	
	
}
