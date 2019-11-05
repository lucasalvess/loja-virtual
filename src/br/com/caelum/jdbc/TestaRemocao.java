package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.Conexao;

public class TestaRemocao {
	
	public static void main(String[] args) throws SQLException {
		Connection conexao = Conexao.conecta();
		
		Statement statement = conexao.createStatement();
		boolean resultado = statement.execute("delete from produto where id= 4");
		
		int count = statement.getUpdateCount();//pega linhas atualizadas
		
		System.out.println(count +" linhas atualizadas!");
		
		conexao.close();
	}

}
