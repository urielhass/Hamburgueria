package br.com.hamburgueria.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.hamburgueria.exception.NoResultException;
import br.com.hamburgueria.exception.ValueZException;
import br.com.hamburgueria.jdbcinterface.FuncionarioDAO;
import br.com.hamburgueria.objs.Funcionario;

public class JDBCFuncionarioDAO implements FuncionarioDAO {
	private Connection conexao;

	public JDBCFuncionarioDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public List<Funcionario> buscarPorNome(String nome) throws NoResultException  {
		String comando = "select * from funcionario  ";
		if (!nome.equals("")) {
			comando += "where nomefuncionario like '" + nome + "%'";
		}
		List<Funcionario> listFunc = new ArrayList<Funcionario>();

		Funcionario func = null;

		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				func = new Funcionario();
				func.setAdministrador(rs.getInt("administrador"));
				func.setBairro(rs.getString("bairro"));
				func.setCidade(rs.getString("cidade"));
				func.setCodfuncionario(rs.getInt("codfuncionario"));
				func.setComplemento(rs.getString("complemento"));
				func.setCpf(rs.getString("cpf"));
				func.setDataNascimento(rs.getDate("data_nascimento"));
				func.setEmail(rs.getString("email"));
				func.setNomeFuncionario(rs.getString("nomefuncionario"));
				func.setNumero(rs.getInt("numero"));
				func.setRg(rs.getString("rg"));
				func.setRua(rs.getString("rua"));
				func.setFuncao(rs.getString("funcao"));
				func.setSenha(rs.getString("senha"));
				func.setFone(rs.getDouble("fone"));
				func.setCep(rs.getInt("cep"));
				listFunc.add(func);
			}
			if(listFunc.isEmpty()){
				throw new NoResultException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listFunc;
	}

	@Override
	public boolean deletarFuncionario(int cod) throws NoResultException{
		if(cod == 0){
			throw new NoResultException("Erro ao deletar funcionario");
		}
		String comando = "delete from funcionario where codfuncionario = "
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
	public boolean atualizar(Funcionario func) throws ValueZException{
		if(func == null){
			throw new ValueZException("Erro ao atualizar os dados do Funcionário");
		}
		boolean editSenha = false;
		String comando = "UPDATE funcionario SET nomefuncionario=?, cpf=?, rg=?, data_nascimento=?,"
				+ "fone=?, email=?, funcao=?, cidade=?, bairro=?, numero=?, rua=?, complemento=?,"
				+ "administrador=?, cep=? ";
		if (func.getSenha() == null || func.getSenha().isEmpty()) {
			comando += " WHERE codfuncionario = ";
		} else {
			editSenha = true;
			comando += ", senha=? where codfuncionario = ";
		}
		comando += func.getCodfuncionario();
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, func.getNomeFuncionario());
			p.setString(2, func.getCpf());
			p.setString(3, func.getRg());
			p.setDate(4, new java.sql.Date(func.getDataNascimento().getTime()));
			p.setDouble(5, func.getFone());
			p.setString(6, func.getEmail());
			p.setString(7, func.getFuncao());
			p.setString(8, func.getCidade());
			p.setString(9, func.getBairro());
			p.setFloat(10, func.getNumero());
			p.setString(11, func.getRua());
			p.setString(12, func.getComplemento());
			p.setInt(13, func.getAdministrador());
			p.setInt(14, func.getCep());
			if (editSenha) {
				p.setString(15, func.getSenha());
			};
			p.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean inserir(Funcionario func) throws ValueZException {
		if(func == null){
			throw new ValueZException("Algo errado com Funcionário.");
		}
		String comando = "insert into funcionario (nomefuncionario, cpf, rg"
				+ ", data_nascimento, fone, email, senha, funcao, cidade, bairro"
				+ ",numero, rua, complemento, administrador, cep) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement p;
		try {
			p = this.conexao.prepareStatement(comando);
			p.setString(1, func.getNomeFuncionario());
			p.setString(2, func.getCpf());
			p.setString(3, func.getRg());
			p.setDate(4, new java.sql.Date(func.getDataNascimento().getTime()));
			p.setDouble(5, func.getFone());
			p.setString(6, func.getEmail());
			p.setString(7, func.getSenha());
			p.setString(8, func.getFuncao());
			p.setString(9, func.getCidade());
			p.setString(10, func.getBairro());
			p.setFloat(11, func.getNumero());
			p.setString(12, func.getRua());
			p.setString(13, func.getComplemento());
			p.setInt(14, func.getAdministrador());
			p.setInt(15, func.getCep());

			p.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Funcionario buscarPorId(int cod) throws NoResultException {
		String comando = "select * from funcionario where codfuncionario = "
				+ cod;
		Funcionario func = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				func = new Funcionario();
				func.setAdministrador(rs.getInt("administrador"));
				func.setBairro(rs.getString("bairro"));
				func.setCidade(rs.getString("cidade"));
				func.setCodfuncionario(rs.getInt("codfuncionario"));
				func.setComplemento(rs.getString("complemento"));
				func.setCpf(rs.getString("cpf"));
				func.setDataNascimento(rs.getDate("data_nascimento"));
				func.setEmail(rs.getString("email"));
				func.setNomeFuncionario(rs.getString("nomefuncionario"));
				func.setNumero(rs.getInt("numero"));
				func.setRg(rs.getString("rg"));
				func.setRua(rs.getString("rua"));
				func.setFuncao(rs.getString("funcao"));
				func.setSenha(rs.getString("senha"));
				func.setFone(rs.getDouble("fone"));
				func.setCep(rs.getInt("cep"));
			}
			if(func == null){
				throw new NoResultException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return func;
	}

	public boolean buscarEmail(Funcionario func, HttpServletRequest request) throws NoResultException {
		String comando = "select * from funcionario where email ='" + func.getEmail() + "'";
		boolean retun = false;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				if((func.getEmail().equals(rs.getString("email"))) && (func.getSenha().equals(rs.getString("senha")))){
					HttpSession sessao = request.getSession(true);
					sessao.setAttribute("nome", rs.getString("nomefuncionario"));
					sessao.setAttribute("administrador", rs.getString("administrador"));
					sessao.setAttribute("cod", rs.getString("codfuncionario"));
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
