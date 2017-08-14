package br.com.hamburgueria.jdbcinterface;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.hamburgueria.exception.NoResultException;
import br.com.hamburgueria.exception.ValueZException;
import br.com.hamburgueria.objs.Funcionario;

public interface FuncionarioDAO {

	public List<Funcionario> buscarPorNome(String nome) throws NoResultException;

	public boolean deletarFuncionario(int id) throws NoResultException;

	public boolean atualizar(Funcionario funcionario) throws ValueZException;

	public boolean inserir(Funcionario funcionario) throws ValueZException;
	
	public Funcionario buscarPorId(int id) throws NoResultException;

	public boolean buscarEmail(Funcionario func, HttpServletRequest request) throws NoResultException;

}
