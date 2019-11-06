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
		
		Connection conexao = Conexao.conecta();
		conexao.setAutoCommit(false);//desabilita persistencias automaticas
		
		try {
			
			String sql = "insert into produto(nome,descricao) values(?,?)";
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
			adiciona("TV LCD", "32 polegadas", statement);
			adiciona("Bluray", "Full HDMI", statement);
			conexao.commit();
			statement.close();			
			
		} catch (Exception e) {
			conexao.rollback();
		}
		

		conexao.close();
	}

	private static ResultSet adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {
		statement.setString(1, nome);
		statement.setString(2, descricao);
		
		 
		boolean resultado = statement.execute();
		
		ResultSet rs = statement.getGeneratedKeys();
		
		while(rs.next()) {
			System.out.println("Gerou o id: "+rs.getString("id"));
		}
		return rs;
	}
}
