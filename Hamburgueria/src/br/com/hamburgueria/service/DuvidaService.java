package br.com.hamburgueria.service;

import java.sql.Connection;
import java.util.List;

import br.com.hamburgueria.bd.conexao.Conexao;
import br.com.hamburgueria.exception.HamburgueriaException;
import br.com.hamburgueria.jdbc.JDBCDuvidaDAO;
import br.com.hamburgueria.jdbcinterface.DuvidaDAO;
import br.com.hamburgueria.objs.Duvida;

public class DuvidaService {


	public void adicionar(Duvida duv) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			DuvidaDAO jdbcDuvida = new JDBCDuvidaDAO(conexao);
			//VALIDAR
				jdbcDuvida.inserir(duv);

		} catch (Exception e){
			throw new HamburgueriaException();
		}finally{
			conec.fecharConexao();
		}
	}
	public List<Duvida> buscarNome(String nome) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			DuvidaDAO jdbcDuvida = new JDBCDuvidaDAO(conexao);
			return jdbcDuvida.buscarNome(nome);
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
