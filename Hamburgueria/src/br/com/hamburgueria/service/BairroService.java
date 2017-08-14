package br.com.hamburgueria.service;

import java.sql.Connection;
import java.util.List;

import br.com.hamburgueria.bd.conexao.Conexao;
import br.com.hamburgueria.exception.HamburgueriaException;
import br.com.hamburgueria.jdbc.JDBCBairrosDAO;
import br.com.hamburgueria.jdbcinterface.BairroDAO;
import br.com.hamburgueria.objs.Bairro;

public class BairroService {

	public List<Bairro> buscarPorId(int id) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			BairroDAO jdbcBairro = new JDBCBairrosDAO(conexao);
			
			return jdbcBairro.buscarPorId(id);
		}catch(Exception e){
			e.printStackTrace();
			throw new HamburgueriaException();
		}finally{
			conec.fecharConexao();
		}
	}

	
}
