package br.com.caelum.jdbc;

import br.com.caelum.jdbc.dao.ProdutoDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaAtualiza {
	public static void main(String[] args) {
	
		try {
			Produto produto = new Produto();
			produto.setId(46);
			produto.setNome("Mesa de vidro(Alterada)");
			produto.setDescricao("Nova descricao");
			
			ProdutoDAO pdao = new ProdutoDAO();
			
			
			if(pdao.atualiza(produto)) {
				System.out.println("Atualizou o produto: "+ produto.getNome());
			}

			
			
		}catch(Exception e) {
			System.out.println("Não deu: "+e);
		} 
	}
	
}
