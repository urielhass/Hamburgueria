package br.com.hamburgueria.jdbcinterface;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.hamburgueria.exception.NoResultException;
import br.com.hamburgueria.exception.ValueZException;
import br.com.hamburgueria.objs.Usuario;

public interface UsuarioDAO {

	public List<Usuario> buscarPorNome(String nome) throws NoResultException;

	public boolean deletarUsuario(int id) throws NoResultException;

	public boolean atualizar(Usuario user) throws ValueZException;

	public boolean inserir(Usuario user) throws ValueZException;
	
	public Usuario buscarPorId(int id) throws NoResultException;

	public boolean buscarEmail(Usuario user,  HttpServletRequest request) throws NoResultException;

}
