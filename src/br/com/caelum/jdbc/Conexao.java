package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;

public class Conexao {
	
	public Conexao() throws SQLException {
		this.conecta();
	}
	
	public static Connection conecta() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:12345/lojavirtual","budapeste","budapeste-dev");
	}
}
