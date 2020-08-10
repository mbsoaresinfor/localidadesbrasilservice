package br.com.mbs.localidadesbrasilservice.arquivo;

public abstract class CriaFormataArquivo<T>{

	protected T obj;

	public CriaFormataArquivo(T t) {
		obj = t;
	}
	
	public abstract byte[] executa();
	
}
