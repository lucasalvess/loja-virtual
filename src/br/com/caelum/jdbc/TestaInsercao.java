package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.Conexao;

public class TestaInsercao {

	 public static void main(String[] args) throws SQLException {
		 
		String nome = "Produto tal";
		String descricao = "Um produto com tais caracteristicas";
		 
		Connection conexao = Conexao.conecta();
		
		String sql = "insert into produto(nome,descricao) values(?,?)";
		PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, nome);
		statement.setString(2, descricao);
		
		 
		boolean resultado = statement.execute();
		
		ResultSet rs = statement.getGeneratedKeys();
		
		while(rs.next()) {
			System.out.println("Gerou o id: "+rs.getString("id"));
		}
		
		rs.close();
		statement.close();
		conexao.close();
	}
}
