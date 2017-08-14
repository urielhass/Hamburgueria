package br.com.hamburgueria.service;
import java.sql.Connection;
import java.util.List;

import br.com.hamburgueria.bd.conexao.Conexao;
import br.com.hamburgueria.exception.HamburgueriaException;
import br.com.hamburgueria.jdbc.JDBCProdutoDAO;
import br.com.hamburgueria.jdbcinterface.ProdutoDAO;
import br.com.hamburgueria.objs.Produto;

public class ProdutoService {

	public Produto buscarId(int id) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try {			
			Connection conexao = conec.abrirConexao();
			ProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			return jdbcProduto.buscarId(id);
		}catch(Exception e){
			e.printStackTrace();
			throw new HamburgueriaException();
		}finally{
			conec.fecharConexao();
		}
	}
	
	public List<Produto> buscarNome(String nome) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			ProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			return jdbcProduto.buscarNome(nome);
		}catch(Exception e){
			e.printStackTrace();
			throw new HamburgueriaException();
		}finally{
			conec.fecharConexao();
		}
	}
	
	public void adicionar(Produto prod) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try {
			Connection conexao = conec.abrirConexao();
			ProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			//ADICIONAR VALIDAÇÃO
			jdbcProduto.inserir(prod);

		} catch (Exception e){
			throw new HamburgueriaException(e.getMessage());
		}finally{
			conec.fecharConexao();
		}
	}
	
	public void deletar(int id) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try{
			Connection conexao = conec.abrirConexao();
			ProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			jdbcProduto.deletar(id);
		} catch (Exception e){
			e.printStackTrace();
			throw new HamburgueriaException();
		}finally{
			conec.fecharConexao();
		}
	}
	
	public void atualizar(Produto prod) throws HamburgueriaException{
		Conexao conec = new Conexao();
		try{
			Connection conexao = conec.abrirConexao();
			ProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			//ADICIONAR VALIDAÇÃO USUÁRIO
			jdbcProduto.atualizar(prod);

		} catch (Exception e){
			e.printStackTrace();
		}finally{
			conec.fecharConexao();
		}
	}
}