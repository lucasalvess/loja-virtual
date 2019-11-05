package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestaConexao {

	public static void main(String[] args) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println("Deu ruim: "+e);
		}
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:12345/lojavirtual","budapeste","budapeste-dev"
					+ "");
			System.out.println("Abrindo uma conexao com sucesso");
			connection.close();
		} catch (Exception e) {
			System.out.println("Deu ruim: "+e);
		}
		
	}
}
