package com.springproject.gamestore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name="tb_produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotBlank(message= "O atributo 'Nome' é obrigatório!")
    @Size(min=20, max=150, message="O atributo 'Nome' deve possuir no mínimo 20 e no máximo 150 caracteres!")
	private String nome;
	
    @NotBlank (message= "O atributo 'Categoria' é obrigatório!")
    @Size (min=20, max=150, message="O atributo 'Categoria' deve possuir no mínimo 20 e no máximo 150 caracteres!")
	private String categoria;
	
    @NotBlank (message= "O atributo 'Descrição' é obrigatório!")
    @Size (min= 25, max = 2000, message="O atributo 'Descrição' deve possuir no mínimo 25 e no máximo 2000 carzcteres!")
	private String descricao;
	
	@NotBlank (message= "O atributo 'Valor' precisa ser declarado!")
	@DecimalMin(value= "0.01", message="O valor mínimo permitido é R$0.01.")
    private BigDecimal valor;
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	@UpdateTimestamp
	private LocalDateTime data;
}
