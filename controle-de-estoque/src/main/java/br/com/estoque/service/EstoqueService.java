package br.com.estoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estoque.DAO.EstoqueRepository;
import br.com.estoque.modelo.Estoque;

@Service
public class EstoqueService {
	
	private final EstoqueRepository estoqueRepository;
	
	@Autowired
	public EstoqueService(EstoqueRepository estoqueRepository) {
		this.estoqueRepository = estoqueRepository;
	}
	
	public void saveProduto(Estoque produto) {
		estoqueRepository.save(produto);
	}
	
	public EstoqueRepository repository() {
		return estoqueRepository;
	}
	
}
