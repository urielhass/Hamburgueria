package br.com.hamburgueria.jdbcinterface;

import java.util.List;

import br.com.hamburgueria.exception.NoResultException;
import br.com.hamburgueria.exception.ValueZException;
import br.com.hamburgueria.objs.Duvida;

public interface DuvidaDAO {
	
	public List<Duvida> buscarNome(String nome) throws NoResultException;
	public boolean inserir(Duvida duv) throws ValueZException;

}
