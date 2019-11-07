package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.Conexao;
import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.Produto;

public class CategoriaDAO {

	private Connection con;

	public CategoriaDAO(Connection conexao) throws SQLException {
		this.con = conexao;
	}

	// INSERE CATEGORIA
	public void salva(Categoria categoria) throws SQLException {
		String sql = "INSERT INTO categoria(nome) values(?)";
		try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, categoria.getNome());
			stmt.execute();
			
			//recupera id criado
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {// se tiver o proximo valor pra ver
					int id = rs.getInt("id");
					categoria.setId(id);
				}

			}
		}
	}

	// LISTA CATEGORIA
	public List<Categoria> lista() {
		
		try {

			Statement stmt = con.createStatement();// Statement prepara a query
			boolean resultado = stmt.execute("select * from categoria");

			List<Categoria> listaCat = new ArrayList<Categoria>();// Lista que vai ser retornada com os produtos

			if (resultado) {
				ResultSet rs = stmt.getResultSet();
	
				while (rs.next()) { // monta lista de produtos
					Categoria c = new Categoria(); 
					c.setId(rs.getInt("id"));
					c.setNome(rs.getString("nome"));
					listaCat.add(c);
				}
			}
			
			return listaCat;
		} catch (SQLException e) {

		} 
		return null;

	}
	
	//DELETA PRODUTO
	public boolean deleta(Produto produto) {
		
		try {
			PreparedStatement stmt =  con.prepareStatement("delete from produto where id = ?");
			stmt.setInt(1, produto.getId());
			if(stmt.execute()) {
				System.out.println("Deu certo!");
				return true;
				
			}
			
		} catch (Exception e) {
			System.out.println(e);
			
		}
		return false;		
	}
	

}
