package br.com.hamburgueria.service;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.com.hamburgueria.auxilia.Crip;
import br.com.hamburgueria.bd.conexao.Conexao;
import br.com.hamburgueria.exception.HamburgueriaException;
import br.com.hamburgueria.jdbc.JDBCUsuarioDAO;
import br.com.hamburgueria.jdbcinterface.UsuarioDAO;
import br.com.hamburgueria.objs.Usuario;

public class UsuarioService {

	private HttpServletRequest request;

	public Usuario buscarUsuarioPorId(int id) throws HamburgueriaException {
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			UsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			return jdbcUsuario.buscarPorId(id);
		} catch (HamburgueriaException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HamburgueriaException();
		} finally {
			conec.fecharConexao();
		}
	}

	public List<Usuario> buscarUsuarioPorNome(String nome)
			throws HamburgueriaException {
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			UsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			return jdbcUsuario.buscarPorNome(nome);
		} catch (HamburgueriaException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HamburgueriaException();
		} finally {
			conec.fecharConexao();
		}
	}

	public void adicionarUsuario(Usuario user) throws HamburgueriaException {
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			UsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			Crip crip = new Crip();
			user.setSenha(crip.cripto(user.getSenha()));
			// ADICIONAR VALIDAÇÃO USUÁRIO
			jdbcUsuario.inserir(user);

			// throw new
			// HamburgueriaException("Campos vazios, por favor preencha todos.");
		} catch (Exception e) {
			throw new HamburgueriaException(e.getMessage());
		} finally {
			conec.fecharConexao();
		}
	}

	public void deletarUsuario(int id) throws HamburgueriaException {
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			UsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			jdbcUsuario.deletarUsuario(id);
		} catch (HamburgueriaException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new HamburgueriaException();
		} finally {
			conec.fecharConexao();
		}
	}

	public void atualizarUsuario(Usuario user) throws HamburgueriaException {
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			UsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			Crip crip = new Crip();
			user.setSenha(crip.cripto(user.getSenha()));
			// ADICIONAR VALIDAÇÃO USUÁRIO
			jdbcUsuario.atualizar(user);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conec.fecharConexao();
		}
	}


}