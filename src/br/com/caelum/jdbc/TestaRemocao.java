package br.com.caelum.jdbc;

import br.com.caelum.jdbc.dao.ProdutoDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaRemocao {
	
	public static void main(String[] args){
		try {
			ProdutoDAO pdao = new ProdutoDAO();
			Produto produto = new Produto();
			
			produto.setId(47);
			if (pdao.deleta(produto)) {
				System.out.println("Excluiu de boa");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}

}
