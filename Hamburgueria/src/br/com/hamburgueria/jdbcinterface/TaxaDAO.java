package br.com.hamburgueria.jdbcinterface;

import java.util.List;

import br.com.hamburgueria.exception.NoResultException;
import br.com.hamburgueria.exception.ValueZException;
import br.com.hamburgueria.objs.Taxa;

public interface TaxaDAO {
	public List<Taxa> buscar(String list) throws NoResultException;

	public boolean editar(Taxa taxa) throws ValueZException;
}
