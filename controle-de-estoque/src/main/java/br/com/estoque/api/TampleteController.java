package br.com.estoque.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.estoque.DAO.EstoqueRepository;
import br.com.estoque.DAO.UsuarioRepository;
import br.com.estoque.modelo.Estado;
import br.com.estoque.modelo.Estoque;
import br.com.estoque.modelo.HistoricoEmprestimo;
import br.com.estoque.modelo.Usuario;
import br.com.estoque.modelo.security.PasswordConfig;

@Controller
@RequestMapping("/")
public class TampleteController {

	private final EstoqueRepository estoqueRepository;
	private final UsuarioRepository usuarioRepository;
	private final PasswordConfig password;

	@Autowired
	public TampleteController(EstoqueRepository estoqueRepository, UsuarioRepository usuarioRepository,
			PasswordConfig passwordEncoder) {
		this.estoqueRepository = estoqueRepository;
		this.usuarioRepository = usuarioRepository;
		this.password = passwordEncoder;
	}

	@GetMapping("login")
	public String getLoginView() {
		return "login";
	}

	@GetMapping("estoque")
	public String getEstoqueView(Model model) {
		model.addAttribute("estoque", estoqueRepository.getAll(Estado.ESTOQUE, Estado.EMPRESTADO));
		return "estoque";
	}

	@GetMapping("register")
	public String getRegisterView() {
		return "register";
	}

	@ModelAttribute("usuario")
	public Usuario getUsuarioform(String nome, String senha) {
		return new Usuario(nome, senha);
	}

	@PostMapping("register")
	public String addUsuario(@ModelAttribute Usuario usuario) {
		usuario.setSenha(password.passwordEncoder().encode(usuario.getSenha()));
		usuarioRepository.save(usuario);
		return "usuarioPreview";
	}

	@GetMapping("produto/{id}")
	public String getProdutoView(Model model, @PathVariable int id) {
		model.addAttribute("estoque", estoqueRepository.getById(id));
		return "produto";
	}

	@PostMapping("produto/{id}")
	public String emprestaProduto(Model model,@PathVariable int id) {
		Estoque produto = estoqueRepository.getById(id);
		switch (produto.getEstado()) {
			case EMPRESTADO:
				produto.setEstado(Estado.ESTOQUE);
				break;
			case ESTOQUE:
				produto.setEstado(Estado.EMPRESTADO);
				break;
		}
		estoqueRepository.save(produto);
		model.addAttribute(produto);
		return "produto";
	}

	@ModelAttribute("estoque")
	public Estoque getEstoqueform(String nome, String descricao) {

		return new Estoque(nome, descricao);
	}
	
	@GetMapping("registraProduto")
	public String getSaveProdutoView() {
		return "registraProduto";
	}
	
	@PostMapping("registraProduto")
	public String saveProduto(@ModelAttribute("estoque") Estoque estoque) {
		int max = 999999999;
		int min = 111111111;
		estoque.setCodigoDeBarras((int) (Math.random()*(max-min+1)+min));
		estoqueRepository.save(estoque);
		return "produtoPreview";
	}

}
