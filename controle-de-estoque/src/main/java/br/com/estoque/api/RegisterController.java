package br.com.estoque.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.DAO.UsuarioRepository;
import br.com.estoque.modelo.Usuario;
import br.com.estoque.modelo.security.PasswordConfig;

@RestController
@RequestMapping("/register")
public class RegisterController {
	
	private final PasswordConfig passwordConfig;
	private final UsuarioRepository repository;
	
	@Autowired
	public RegisterController(PasswordConfig passwordConfig, UsuarioRepository repository) {
		this.passwordConfig = passwordConfig;
		this.repository = repository;
	}
	
	@PostMapping(path = "/{usuario}/{senha}")
	public void addUsuario(@PathVariable("usuario") String usuario,@PathVariable("senha") String senha) {
		repository.save(new Usuario(usuario, passwordConfig.passwordEncoder().encode(senha)));
	}
}
