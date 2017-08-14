package br.com.hamburgueria.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.hamburgueria.exception.NoResultException;
import br.com.hamburgueria.exception.ValueZException;
import br.com.hamburgueria.jdbcinterface.ProdutoDAO;
import br.com.hamburgueria.objs.Produto;

public class JDBCProdutoDAO implements ProdutoDAO{
	
	private Connection conexao;
	public JDBCProdutoDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public List<Produto> buscarNome(String nome) throws NoResultException  {
		String comando = "select * from produto  ";
		if (!nome.equals("")) {
			comando += "where nomeproduto like '" + nome + "%'";
		}
		List<Produto> list = new ArrayList<Produto>();
		Produto prod = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				prod = new Produto();
				prod.setCod(rs.getInt("codproduto"));
				prod.setNome(rs.getString("nomeproduto"));
				prod.setDescricao(rs.getString("descricao"));
				prod.setAnexo(rs.getString("anexo"));
				prod.setCancelamento(rs.getString("cancelamento"));
				prod.setObservacao(rs.getString("observacao"));
				prod.setValor(rs.getInt("valor"));
				prod.setCategoria(rs.getString("categoria"));
				list.add(prod);
			}
			if(list.isEmpty()){
				throw new NoResultException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean deletar(int cod) throws NoResultException{
		if(cod == 0){
			throw new NoResultException("Erro ao deletar Produto");
		}
		String comando = "delete from produto where codproduto = "
				+ cod;
		Statement p;
		try {
			p = this.conexao.createStatement();
			p.execute(comando);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean atualizar(Produto prod) throws ValueZException{
		if(prod == null){
			throw new ValueZException("Erro ao atualizar os dados do Produto");
		}
		String comando = "UPDATE produto SET nomeproduto=?,"
				+ " descricao=?, categoria=?, valor=? WHERE codproduto="+prod.getCod();
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, prod.getNome());
			p.setString(2, prod.getDescricao());
			p.setString(3, prod.getCategoria());
			p.setInt(4, prod.getValor());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean inserir(Produto prod) throws ValueZException {
		if(prod == null){
			throw new ValueZException("Produto não foi adicionado no banco de dados.");
		}
		String comando = "insert into produto (nomeproduto, descricao, categoria,valor)"
				+ " values (?, ?, ?, ?);";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, prod.getNome());
			p.setString(2, prod.getDescricao());
			p.setString(3, prod.getCategoria());
			p.setInt(4, prod.getValor());
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Produto buscarId(int cod) throws NoResultException {
		String comando = "select * from produto where codproduto = "
				+ cod;
		Produto prod = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				prod = new Produto();
				prod.setCod(rs.getInt("codproduto"));
				prod.setNome(rs.getString("nomeproduto"));
				prod.setDescricao(rs.getString("descricao"));
				prod.setAnexo(rs.getString("anexo"));
				prod.setCancelamento(rs.getString("cancelamento"));
				prod.setObservacao(rs.getString("observacao"));
				prod.setValor(rs.getInt("valor"));
				prod.setCategoria(rs.getString("categoria"));
			}
			if(prod == null){
				throw new NoResultException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prod;
	}

}
