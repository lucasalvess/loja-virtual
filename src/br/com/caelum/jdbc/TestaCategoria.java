package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.jdbc.dao.CategoriaDAO;
import br.com.caelum.jdbc.modelo.Categoria;

public class TestaCategoria {

	public static void main(String[] args) throws SQLException {
		
		List<Categoria> listaDeCategorias = new ArrayList<Categoria>();
		Connection conexao = new Conexao().conecta();
		//INSERE NOVA CATEGORIA
		try {
			Categoria ca = new Categoria();
			CategoriaDAO cdao = new CategoriaDAO(conexao);

			ca.setNome("ferramentas");
			cdao.salva(ca);

			listaDeCategorias = cdao.lista();

			System.out.println("------CATEGORIAS--------");
			for (Categoria c : listaDeCategorias) {
				System.out.println(c.getNome());
			}

		} catch (Exception e) {

		}finally {
			conexao.close();
		} 

	}
}
