package br.com.hamburgueria.exception;

public class ValueZException extends Exception {
	
	public ValueZException(){
		super("Campo não preenchido.");
	}
	public ValueZException(String msg){
		super(msg);
	}
	public ValueZException(Throwable t){
		super(t);
	}	
}
