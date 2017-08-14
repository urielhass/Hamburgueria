package br.com.hamburgueria.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hamburgueria.exception.NoResultException;
import br.com.hamburgueria.exception.ValueZException;
import br.com.hamburgueria.jdbcinterface.TaxaDAO;
import br.com.hamburgueria.objs.Taxa;

public class JDBCTaxaDAO implements TaxaDAO {
	private Connection conexao;
	public JDBCTaxaDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	@Override
	public List<Taxa> buscar(String nome) throws NoResultException  {
		String comando = "select * from taxas";
		if (!nome.equals("")) {
			comando += "where nometaxa like '%" + nome + "%'";
		}
		List<Taxa> list = new ArrayList<Taxa>();
		Taxa taxa = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				taxa = new Taxa();
				taxa.setCod(rs.getInt("codtaxas"));
				taxa.setDescricao(rs.getString("descricao"));
				taxa.setNome(rs.getString("nometaxa"));
				taxa.setValor(rs.getInt("valor"));
				list.add(taxa);
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
	public boolean editar(Taxa taxa) throws ValueZException{
		if(taxa == null){
			throw new ValueZException("Erro ao atualizar os valores.");
		}
		String comando = "UPDATE taxas SET valor=? WHERE codtaxas = " +taxa.getCod();
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setInt(1, taxa.getValor());
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}