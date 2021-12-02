package br.com.estoque.api;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estoque.DAO.UsuarioRepository;
import br.com.estoque.modelo.Estado;
import br.com.estoque.modelo.Estoque;
import br.com.estoque.modelo.HistoricoEmprestimo;
import br.com.estoque.modelo.Usuario;
import br.com.estoque.service.EstoqueService;
import br.com.estoque.service.UsuarioService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

	private final EstoqueService estoqueService;
	private final UsuarioRepository usuarioRepository;

	@Autowired
	public EstoqueController(EstoqueService estoqueService, UsuarioRepository usuarioService) {
		this.estoqueService = estoqueService;
		this.usuarioRepository = usuarioService;
	}
	
	@PostMapping
	public void addProduto(@RequestBody Estoque produto) {
		estoqueService.saveProduto(produto);
	}
	
	@PostMapping(path = "/empresta/{usuario}/{id}")
	public void emprestaProduto(@PathVariable String usuario,@PathVariable("id") int produtoId) {
		Estoque produto = estoqueService.repository().getById(produtoId);
		Usuario usuarioDB = usuarioRepository.loadByUsuario(usuario).get();
	}

}
