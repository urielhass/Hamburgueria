package br.com.hamburgueria.exception;

public class HamburgueriaException extends Exception {
	public HamburgueriaException(){
		super("Ocorreu um erro inesperado.");
	}
	public HamburgueriaException(String msg){
		super(msg);
	}
	public HamburgueriaException(Throwable t){
		super(t);
	}	
}
