package br.com.estoque.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.estoque.DAO.UsuarioRepository;
import br.com.estoque.modelo.Usuario;
import br.com.estoque.modelo.security.PasswordConfig;

@Service
public class UsuarioService implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordConfig passwordEncoder;
	private Usuario usuario;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository, PasswordConfig passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.loadByUsuario(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		this.usuario = usuario;

		return usuario;
	}

	public UsuarioRepository repository() {
		return usuarioRepository;

	}

	public Usuario getUsuario() {
		return usuario;
	}

}
