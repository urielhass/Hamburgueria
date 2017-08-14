package br.com.hamburgueria.jdbcinterface;

import java.util.List;

import br.com.hamburgueria.objs.Bairro;

public interface BairroDAO {
	List<Bairro> buscarPorId(int id);
}
