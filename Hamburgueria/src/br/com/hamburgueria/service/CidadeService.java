package br.com.hamburgueria.service;

import java.sql.Connection;
import java.util.List;

import br.com.hamburgueria.bd.conexao.Conexao;
import br.com.hamburgueria.exception.HamburgueriaException;
import br.com.hamburgueria.jdbc.JDBCCidadesDAO;
import br.com.hamburgueria.jdbcinterface.CidadeDAO;
import br.com.hamburgueria.objs.Cidade;

public class CidadeService {

	public List<Cidade> listarTodas() throws HamburgueriaException{
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			CidadeDAO jdbcCidade = new JDBCCidadesDAO(conexao);
			
			return jdbcCidade.listarTodas();
		}catch(Exception e){
			e.printStackTrace();
			throw new HamburgueriaException();
		}finally{
			conec.fecharConexao();
		}
	}
}
