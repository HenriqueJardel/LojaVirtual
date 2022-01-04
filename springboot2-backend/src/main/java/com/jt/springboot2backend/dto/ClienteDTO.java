package com.jt.springboot2backend.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.jt.springboot2backend.domain.Cliente;
import com.jt.springboot2backend.services.validation.ClienteUpdate;

import org.hibernate.validator.constraints.Length;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=100, message="O tamanho deve ser entre 3 e 100 caracteres")
	private String nome;

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=100, message="O tamanho deve ser entre 3 e 100 caracteres")
	private String sobreNome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	public ClienteDTO() {
	}

	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.sobreNome = obj.getSobreNome();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}