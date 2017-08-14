package br.com.hamburgueria.service;
import java.sql.Connection;
import java.util.List;

import br.com.hamburgueria.auxilia.Crip;
import br.com.hamburgueria.bd.conexao.Conexao;
import br.com.hamburgueria.exception.HamburgueriaException;
import br.com.hamburgueria.jdbc.JDBCFuncionarioDAO;
import br.com.hamburgueria.jdbcinterface.FuncionarioDAO;
import br.com.hamburgueria.objs.Funcionario;
import br.com.hamburgueria.validacoes.ValidaFuncionario;


public class FuncionarioService {

	public Funcionario buscarFuncionarioPorId(int id) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try {			
			Connection conexao = conec.abrirConexao();
			FuncionarioDAO jdbcFuncionario = new JDBCFuncionarioDAO(conexao);
			return jdbcFuncionario.buscarPorId(id);
		}catch(HamburgueriaException e){
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			throw new HamburgueriaException();
		}finally{
			conec.fecharConexao();
		}
	}
	
	public List<Funcionario> buscarFuncionarioPorNome(String nome) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			FuncionarioDAO jdbcFuncionario = new JDBCFuncionarioDAO(conexao);
			return jdbcFuncionario.buscarPorNome(nome);
		}catch(HamburgueriaException e){
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			throw new HamburgueriaException();
		}finally{
			conec.fecharConexao();
		}
	}
	
	public void adicionarFuncionario(Funcionario func) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			Crip crip = new Crip();
			func.setSenha(crip.cripto(func.getSenha()));
			FuncionarioDAO jdbcFuncionario = new JDBCFuncionarioDAO(conexao);
			ValidaFuncionario validaFuncionario = new ValidaFuncionario();
			boolean isvazio = validaFuncionario.funcionario(func);
			if(isvazio == true){
				jdbcFuncionario.inserir(func);
			}else{
				throw new HamburgueriaException("Campos vazios, por favor preencha todos.");
			}
		} catch (HamburgueriaException e) {
			e.printStackTrace();
			throw e;			
		}catch (Exception e){
			throw new HamburgueriaException();
		}finally{
			conec.fecharConexao();
		}
	}
	
	public void deletarFuncionario(int id) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try{
			Connection conexao = conec.abrirConexao();
			FuncionarioDAO jdbcFuncionario = new JDBCFuncionarioDAO(conexao);
			jdbcFuncionario.deletarFuncionario(id);
		} catch (HamburgueriaException e) {
			throw e;			
		}catch (Exception e){
			e.printStackTrace();
			throw new HamburgueriaException();
		}finally{
			conec.fecharConexao();
		}
	}
	
	public void atualizarFuncionario(Funcionario func) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try{
			Connection conexao = conec.abrirConexao();
			FuncionarioDAO jdbcFuncionario = new JDBCFuncionarioDAO(conexao);
			ValidaFuncionario validaFuncionario = new ValidaFuncionario();
			Crip crip = new Crip();
			func.setSenha(crip.cripto(func.getSenha()));
			boolean isvazio = validaFuncionario.funcionario(func);
			if(isvazio == true){
				jdbcFuncionario.atualizar(func);
			}else{
				throw new HamburgueriaException("Campos vazios, por favor preencha todos.");
			}
		} catch (HamburgueriaException e) {
			throw e;			
		}catch (Exception e){
			e.printStackTrace();
			throw new HamburgueriaException();
		}finally{
			conec.fecharConexao();
		}
	}
	
	public boolean buscarLogin(Funcionario func) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			FuncionarioDAO jdbcFuncionario = new JDBCFuncionarioDAO(conexao);
			Crip crip = new Crip();
			func.setSenha(crip.cripto(func.getSenha()));
			return jdbcFuncionario.buscarEmail(func, null);
		}catch(HamburgueriaException e){
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			throw new HamburgueriaException();
		}finally{
			conec.fecharConexao();
		}
	}
}