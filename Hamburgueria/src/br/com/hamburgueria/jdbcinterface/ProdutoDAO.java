package br.com.hamburgueria.jdbcinterface;

import java.util.List;

import br.com.hamburgueria.exception.NoResultException;
import br.com.hamburgueria.exception.ValueZException;
import br.com.hamburgueria.objs.Produto;

public interface ProdutoDAO {

	public Produto buscarId(int id) throws NoResultException;

	public List<Produto> buscarNome(String nome) throws NoResultException;

	public boolean inserir(Produto prod) throws ValueZException;

	public boolean deletar(int cod) throws NoResultException;

	public boolean atualizar(Produto prod) throws ValueZException;

}
