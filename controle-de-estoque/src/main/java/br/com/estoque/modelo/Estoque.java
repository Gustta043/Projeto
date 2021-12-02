package br.com.estoque.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private Estado estado;
	private String descricao;
	@Column(name = "codigo_de_barras")
	private int codigoDeBarras;
	@Column(name = "historico_emprestimo")
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<HistoricoEmprestimo> historicoEmprestimo = new ArrayList<>();
	@Column(name = "data_aquisicao")
	private String dataAquisicao;

	public Estoque() {
	}

	public Estoque(@JsonProperty String nome, @JsonProperty String descricao, @JsonProperty int codigoDeBarras) {
		this.nome = nome;
		this.descricao = descricao;
		this.codigoDeBarras = codigoDeBarras;
		this.estado = Estado.ESTOQUE;
		this.dataAquisicao = Data.getDataAtual();
	}

	public Estoque(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
		this.estado = Estado.ESTOQUE;
		this.dataAquisicao = Data.getDataAtual();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(int codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public List<HistoricoEmprestimo> getHistoricoEmprestimo() {
		return historicoEmprestimo;
	}

	public void setHistoricoEmprestimo(HistoricoEmprestimo historicoEmprestimo) {
		this.historicoEmprestimo.add(historicoEmprestimo);
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Estoque [id=" + id + ", nome=" + nome + ", estado=" + estado + ", descricao=" + descricao
				+ ", codigoDeBarras=" + codigoDeBarras + ", historicoEmprestimo=" + historicoEmprestimo
				+ ", dataAquisicao=" + dataAquisicao + "]";
	}

	
}
