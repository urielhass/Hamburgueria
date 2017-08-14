package br.com.hamburgueria.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hamburgueria.jdbcinterface.BairroDAO;
import br.com.hamburgueria.objs.Bairro;

public class JDBCBairrosDAO implements BairroDAO {
	private Connection conexao;

	public JDBCBairrosDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public List<Bairro> buscarPorId(int cod){
		String comando = "select * from bairros where cidade ="+cod;
		List<Bairro> listBairro = new ArrayList<Bairro>();
		Bairro bairro = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				bairro = new Bairro();

				bairro.setCodBairro(rs.getInt("id"));
				bairro.setBairro(rs.getString("nome"));
				listBairro.add(bairro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listBairro;
		
	}
}
