package br.com.hamburgueria.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.hamburgueria.exception.NoResultException;
import br.com.hamburgueria.exception.ValueZException;
import br.com.hamburgueria.jdbcinterface.UsuarioDAO;
import br.com.hamburgueria.objs.Usuario;

public class JDBCUsuarioDAO implements UsuarioDAO {
	private Connection conexao;

	public JDBCUsuarioDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public List<Usuario> buscarPorNome(String nome) throws NoResultException  {
		String comando = "select * from cliente  ";
		if (!nome.equals("")) {
			comando += "where nomecliente like '" + nome + "%'";
		}
		List<Usuario> list = new ArrayList<Usuario>();
		Usuario user = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				user = new Usuario();
				user.setBairro(rs.getInt("bairro"));
				user.setCidade(rs.getInt("cidade"));
				user.setCod(rs.getInt("codcliente"));
				user.setComplemento(rs.getString("complemento"));
				user.setCpf(rs.getInt("cpf"));
				user.setData_nascimento(rs.getDate("data_nascimento"));
				user.setEmail(rs.getString("email"));
				user.setNome(rs.getString("nomecliente"));
				user.setNumero(rs.getInt("numero"));
				user.setData_cadastro(rs.getDate("data_cadastro"));
				user.setRg(rs.getInt("rg"));
				user.setRua(rs.getString("rua"));
				user.setSenha(rs.getString("senha"));
				user.setTelefone(rs.getDouble("telefone"));
				user.setCep(rs.getInt("cep"));
				list.add(user);
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
	public boolean deletarUsuario(int cod) throws NoResultException{
		if(cod == 0){
			throw new NoResultException("Erro ao deletar Usuario");
		}
		String comando = "delete from cliente where codcliente = "
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
	public boolean atualizar(Usuario user) throws ValueZException{
		if(user == null){
			throw new ValueZException("Erro ao atualizar os dados do Usuário");
		}
		boolean editSenha = false;
		String comando = "UPDATE cliente SET nomecliente=?, data_nascimento=?, rg=?, cpf=?,"
				+ "cidade=?, bairro=?, rua=?, numero=?, complemento=?, cep=?, telefone=?, email=?";
		if (user.getSenha() == null || user.getSenha().isEmpty()) {
			comando += " WHERE codcliente = ";
		} else {
			editSenha = true;
			comando += ", senha=? where codcliente = ";
		}
		comando += user.getCod() + ";";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, user.getNome());
			p.setDate(2, new java.sql.Date( user.getData_nascimento().getTime()));
			p.setInt(3, user.getRg());
			p.setDouble(4, user.getCpf());
			p.setInt(5, user.getCidade());
			p.setInt(6, user.getBairro());
			p.setString(7, user.getRua());
			p.setInt(8, user.getNumero());
			p.setString(9, user.getComplemento());
			p.setInt(10, user.getCep());
			p.setDouble(11, user.getTelefone());
			p.setString(12, user.getEmail());
			if (editSenha) {
				p.setString(13, user.getSenha());
			};
			p.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean inserir(Usuario user) throws ValueZException {
		if(user == null){
			throw new ValueZException("Valores não foram encontrados.");
		}
		String comando = "insert into cliente (nomecliente, data_nascimento, rg, cpf, cidade"
				+ ", bairro, rua, numero, complemento, cep, telefone, data_cadastro, email"
				+ ", senha) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement p;
		Date d = new Date();
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, user.getNome());
			p.setDate(2, new java.sql.Date( user.getData_nascimento().getTime()));
			p.setInt(3, user.getRg());
			p.setInt(4, user.getCpf());
			p.setInt(5, user.getCidade());
			p.setInt(6, user.getBairro());
			p.setString(7, user.getRua());
			p.setInt(8, user.getNumero());
			p.setString(9, user.getComplemento());
			p.setInt(10, user.getCep());
			p.setDouble(11, user.getTelefone());
			p.setDate(12, new java.sql.Date( d.getTime()));
			p.setString(13, user.getEmail());
			p.setString(14, user.getSenha());
			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Usuario buscarPorId(int cod) throws NoResultException {
		String comando = "select * from cliente where codcliente = "
				+ cod;
		Usuario user = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				user = new Usuario();
				user.setBairro(rs.getInt("bairro"));
				user.setCidade(rs.getInt("cidade"));
				user.setCod(rs.getInt("codcliente"));
				user.setComplemento(rs.getString("complemento"));
				user.setCpf(rs.getInt("cpf"));
				user.setData_nascimento(rs.getDate("data_nascimento"));
				user.setEmail(rs.getString("email"));
				user.setNome(rs.getString("nomecliente"));
				user.setNumero(rs.getInt("numero"));
				user.setData_cadastro(rs.getDate("data_cadastro"));
				user.setRg(rs.getInt("rg"));
				user.setRua(rs.getString("rua"));
				user.setTelefone(rs.getDouble("telefone"));
				user.setCep(rs.getInt("cep"));
			}
			if(user == null){
				throw new NoResultException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean buscarEmail(Usuario user, HttpServletRequest request) throws NoResultException {
		String comando = "select * from cliente where email ='" + user.getEmail() + "'";
		boolean retun = false;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				if((user.getEmail().equals(rs.getString("email"))) && (user.getSenha().equals(rs.getString("senha")))){
					HttpSession sessao = request.getSession(true);
					sessao.setAttribute("nome", rs.getString("nomecliente"));
					sessao.setAttribute("cod", rs.getString("codcliente"));
					sessao.setAttribute("administrador", "0");
					retun = true;
				}				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(retun){
			return true;
		}else{
			return false;
		}
	}
}
