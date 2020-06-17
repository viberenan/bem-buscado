package com.bembuscado.bembuscado.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;

import com.bembuscado.bembuscado.entity.Usuario;

public class CadastrarEmpresaContato {

	private Long id;
	private String razaoSocial;
	private String cnpj;
	private String rua;
	private String numero;
	private String cep;
	private String bairro;
	private String cidade;
	private String uf;
	private String contato;
	private String tipocontato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "Razão Social não pode ser vazio.")
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	@NotEmpty(message = "CNPJ não pode ser vazio.")
	@CNPJ(message = "CNPJ inválido")
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@NotEmpty(message = "Rua não pode ser vazio.")
	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	@NotEmpty(message = "Numero não pode ser vazio.")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@NotEmpty(message = "CEP não pode ser vazio.")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@NotEmpty(message = "Bairro não pode ser vazio.")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@NotEmpty(message = "Cidade não pode ser vazio.")
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@NotEmpty(message = "uf não pode ser vazio.")
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@NotEmpty(message = "tipo de contato não pode ser vazio.")
	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	@NotEmpty(message = "tipo de contato não pode ser vazio.")
	public String getTipocontato() {
		return tipocontato;
	}

	public void setTipocontato(String tipocontato) {
		this.tipocontato = tipocontato;
	}
	

}
