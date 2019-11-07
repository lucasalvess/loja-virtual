package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.Conexao;
import br.com.caelum.jdbc.modelo.Produto;

public class ProdutoDAO {

	private Connection con;

	public ProdutoDAO() throws SQLException {
		this.con = Conexao.conecta();
	}

	// INSERE PRODUTO
	public void salva(Produto produto) throws SQLException {
		String sql = "insert into produto(nome,descricao) values(?,?)";
		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
			stmt.execute();

			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {// se tiver o proximo valor pra ver
					int id = rs.getInt("id");
					produto.setId(id);
				}

			}
		}
	}

	// LISTA PRODUTO
	public List<Produto> lista() {

		try {

			Statement stmt = con.createStatement();// Statement prepara a query
			List<Produto> listaProd = new ArrayList<Produto>();// Lista que vai ser retornada com os produtos

			if (stmt.execute("select * from produto")) {
				ResultSet rs = stmt.getResultSet();

				while (rs.next()) { // monta lista de produtos
					Produto p = new Produto();
					p.setId(rs.getInt("id"));
					p.setNome(rs.getString("nome"));
					p.setDescricao(rs.getString("descricao"));
					listaProd.add(p);
				}
			}

			return listaProd;
		} catch (SQLException e) {

		}
		return null;

	}

	// Deleta Produto
	public boolean deleta(Produto produto) {
		try {
			String sql = "delete from produto where id= ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, produto.getId());
			statement.execute();

			int count = statement.getUpdateCount();// pega linhas atualizadas

			System.out.println(count + " linhas atualizadas!");
			return true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// ATUALIZA PRODUTO

	public boolean atualiza(Produto produto) throws SQLException {
		
		try {
			String sql = "UPDATE produto set nome=?,descricao=? WHERE id=?";
			PreparedStatement smtp = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			smtp.setString(1, produto.getNome());
			smtp.setString(2, produto.getDescricao());
			smtp.setInt(3, produto.getId());
			if (smtp.execute()) {
				System.out.println("Atualizou a linha: ");
			}
			return true;
		}catch(Exception e) {
			return false;
		}finally {
			con.close();
		}
		
	}
	
	//Busca produto by id
	
	public Produto busca(Produto produto) throws SQLException {
		try {
			String sql = "select * from produto where id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, produto.getId());
			
			if (stmt.execute()) {
				ResultSet rs = stmt.getResultSet();
				
				if (rs.next()) { // monta produto
					produto.setNome(rs.getString("nome"));
					produto.setDescricao(rs.getString("descricao"));
				}	
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			con.close();			
			return produto;
		}
		
	}

}
