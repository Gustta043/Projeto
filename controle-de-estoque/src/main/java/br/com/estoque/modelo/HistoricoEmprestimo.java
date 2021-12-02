package br.com.estoque.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class HistoricoEmprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Estoque produto;
	@ManyToOne
	private Usuario usuario;
	private String emprestimo;
	private String devolução;

	public HistoricoEmprestimo() {
	}

	public HistoricoEmprestimo(Estoque produto, Usuario usuario) {
		this.produto = produto;
		this.usuario = usuario;
		this.emprestimo = Data.getDataAtual();
		this.devolução = "7 dias uteis";
	}

	public Estoque getProduto() {
		return produto;
	}

	public void setProduto(Estoque produto) {
		this.produto = produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(String emprestimo) {
		this.emprestimo = emprestimo;
	}

	public String getDevolução() {
		return devolução;
	}

	public void setDevolução(String devolução) {
		this.devolução = devolução;
	}

}
