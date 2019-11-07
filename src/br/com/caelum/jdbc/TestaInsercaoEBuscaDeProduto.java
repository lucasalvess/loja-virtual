package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.dao.ProdutoDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaInsercaoEBuscaDeProduto {
	
	public static void main(String[] args) throws SQLException {
		Produto mesa = new Produto("Mesa de vidro","Mesa de vidro temperado");
		ProdutoDAO pdao = new ProdutoDAO();
		
		List<Produto> produtos = new ArrayList<Produto>(); 
		
		try(Connection conexao = Conexao.conecta()){
			pdao.salva(mesa);
			produtos = pdao.lista();
		}
		
		for (Produto produto : produtos) {
			System.out.println("Existe o produto: "+produto.getNome());
		}	
		
		System.out.println("O produto foi insetida com sucesso com o id:"+mesa.getId());
	}

}
