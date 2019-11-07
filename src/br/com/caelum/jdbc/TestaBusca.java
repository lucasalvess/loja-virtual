package br.com.caelum.jdbc;

import br.com.caelum.jdbc.dao.ProdutoDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaBusca {
	public static void main(String[] args) {
		
		Produto produto = new Produto();
		produto.setId(46);
		
		try {
			ProdutoDAO produtoDao = new ProdutoDAO();
			produto = produtoDao.busca(produto);
			
			produto.setId(46);
			
			System.out.println("----------------------------------");
			System.out.println("Id: "+produto.getId());
			System.out.println("Nome: "+produto.getNome());
			System.out.println("Descrição: "+produto.getDescricao());
			System.out.println("----------------------------------");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
