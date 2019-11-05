package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.Conexao;

public class TestaInsercao {

	 public static void main(String[] args) throws SQLException {
		 
		Connection conexao = Conexao.conecta();
		
		Statement statement = conexao.createStatement();
		
		boolean resultado = statement.execute("insert into produto(nome,descricao) values('Teste','um teste')",statement.RETURN_GENERATED_KEYS);
		
		ResultSet rs = statement.getGeneratedKeys();
		
		while(rs.next()) {
			System.out.println("Gerou o id: "+rs.getString("id"));
		}
		
		
		statement.close();
		conexao.close();
	}
}
