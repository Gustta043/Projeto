package br.com.estoque.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.estoque.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.nome = ?1")
	Optional<Usuario> loadByUsuario(String usuario);
}
