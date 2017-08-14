package br.com.hamburgueria.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hamburgueria.exception.NoResultException;
import br.com.hamburgueria.exception.ValueZException;
import br.com.hamburgueria.jdbcinterface.DuvidaDAO;
import br.com.hamburgueria.objs.Duvida;

public class JDBCDuvidaDAO implements DuvidaDAO {
	private Connection conexao;

	public JDBCDuvidaDAO(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public List<Duvida> buscarNome(String nome) throws NoResultException {
		String comando = "select * from sugestao_criticas  ";
		if (!nome.equals("")) {
			comando += "where sugestao_criticas like '" + nome + "%'";
		}
		List<Duvida> list = new ArrayList<Duvida>();
		Duvida duv = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				duv = new Duvida();
				duv.setCod(rs.getInt("codsugestao_criticas"));
				duv.setNome(rs.getString("nome"));
				duv.setMensagem(rs.getString("mensagem"));
				duv.setTelefone(rs.getInt("telefone"));
				duv.setEmail(rs.getString("email"));
				list.add(duv);
			}
			if (list.isEmpty()) {
				throw new NoResultException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean inserir(Duvida duv) throws ValueZException {
		if (duv == null) {
			throw new ValueZException("Erro, valores negativos.");
		}
		String comando = "insert into sugestoes_criticas ("
				+ "mensagem, nome, email, telefone)"
				+ " values"
				+ "(?, ?, ?, ?)";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, duv.getMensagem());
			p.setString(2, duv.getNome());
			p.setString(3, duv.getEmail());
			p.setInt(4, duv.getTelefone());
			p.execute();			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
