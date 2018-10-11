package br.com.cast.castapi.DTO;

public class PessoaDTO {

	private String cpf;
	private String nome;
	private String email;
	private EnderecoDTO enderecodto;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EnderecoDTO getEnderecodto() {
		return enderecodto;
	}

	public void setEnderecodto(EnderecoDTO endereco) {
		this.enderecodto = endereco;
	}

}
