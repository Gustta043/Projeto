package br.com.estoque.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.estoque.modelo.Estado;
import br.com.estoque.modelo.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
	
	@Query("SELECT produto FROM Estoque produto WHERE produto.id = ?1")
    List<Estoque> getProdutoById(int id);
	
	@Query("SELECT produto FROM Estoque produto WHERE produto.estado = ?1 OR produto.estado = ?2")
	List<Estoque> getAll(Estado estado, Estado estado2);
}
