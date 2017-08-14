package br.com.hamburgueria.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hamburgueria.jdbcinterface.CidadeDAO;
import br.com.hamburgueria.objs.Cidade;

public class JDBCCidadesDAO implements CidadeDAO {
	private Connection conexao;

	public JDBCCidadesDAO(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Cidade> listarTodas() {
		String comando = "select * from cidades where estado_cod = 24";
		List<Cidade> listCidade = new ArrayList<Cidade>();

		Cidade cidade = null;

		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				cidade = new Cidade();
				String nomeCidade = rs.getString("nome");
				int codcidade = rs.getInt("id");

				cidade.setCod(codcidade);
				cidade.setCidade(nomeCidade);
				listCidade.add(cidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCidade;
	}

}
