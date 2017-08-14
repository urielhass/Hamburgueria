package br.com.hamburgueria.exception;

public class NoResultException extends HamburgueriaException{
	public NoResultException(){
		super("Nenhum registro encontrado.");
	}
	public NoResultException(String msg){
		super(msg);
	}
	public NoResultException(Throwable t){
		super(t);
	}
}
