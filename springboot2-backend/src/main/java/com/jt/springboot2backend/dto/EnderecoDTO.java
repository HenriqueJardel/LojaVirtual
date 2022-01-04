package com.jt.springboot2backend.dto;

public class EnderecoDTO {
    private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String cep;
	
    private Integer cidadeId;

    private String nomeCidade;

    public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

    public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

    public String getnomeCidade(){
        return nomeCidade;
    }

    public void setnomeCidade(String nomeCidade){
        this.nomeCidade = nomeCidade;
    }
}
