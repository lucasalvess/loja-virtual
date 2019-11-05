package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {

		
	public static void main(String[] args) {
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:12345/lojavirtual","budapeste","budapeste-dev");
			
			System.out.println("Abrindo uma conexao com sucesso");
			
			Statement statement = connection.createStatement();//Statement prepara a query
			
			
			boolean resultado = statement.execute("select * from produto");
			
			
			if(resultado) {
				ResultSet rs = statement.getResultSet();
				while (rs.next()) {
					System.out.println("----------------------------------");
					System.out.println("Id: "+rs.getString("id"));
					System.out.println("Nome: "+rs.getString("nome"));
					System.out.println("Descrição: "+rs.getString("descricao"));
					System.out.println("----------------------------------");
				}
				rs.close();
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Deu ruim: "+e);
		}
		
	}
}
