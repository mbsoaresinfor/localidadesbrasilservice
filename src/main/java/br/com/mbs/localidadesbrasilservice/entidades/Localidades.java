package br.com.mbs.localidadesbrasilservice.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "localidades")
public class Localidades implements Serializable{

	
	private static final long serialVersionUID = -7013678963971557063L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "idEstado")
	private Integer idEstado;

	@Column(name = "siglaEstado")
	private String siglaEstado;
	
	@Column(name = "regiaoNome")
	private String regiaoNome;

	@Column(name = "nomeCidade")
	private String  nomeCidade;

	@Column(name = "nomeMesorregiao")
	private String nomeMesorregiao;
	
	
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public String getSiglaEstado() {
		return siglaEstado;
	}
	public String getRegiaoNome() {
		return regiaoNome;
	}
	public String getNomeMesorregiao() {
		return nomeMesorregiao;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}
	public void setRegiaoNome(String regiaoNome) {
		this.regiaoNome = regiaoNome;
	}
	public void setNomeMesorregiao(String nomeMesorregiao) {
		this.nomeMesorregiao = nomeMesorregiao;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Localidades [id=" + id + ", idEstado=" + idEstado + ", siglaEstado=" + siglaEstado + ", regiaoNome="
				+ regiaoNome + ", nomeCidade=" + nomeCidade + ", nomeMesorregiao=" + nomeMesorregiao + "]";
	}
	
	
	
}
