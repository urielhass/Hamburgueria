package br.com.hamburgueria.exception;

public class NoValueException extends HamburgueriaException{
	public NoValueException(){
		super("Campos estão vazios.");
	}
	public NoValueException(String msg){
		super(msg);
	}
	public NoValueException(Throwable t){
		super(t);
	}
}
