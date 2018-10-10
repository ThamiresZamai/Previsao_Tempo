package br.com.cast.projgson.business;

import java.util.ArrayList;
import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.projgson.DTO.EnderecoDTO;
import br.com.cast.projgson.DTO.PessoaDTO;
import br.com.cast.projgson.entidade.Endereco;
import br.com.cast.projgson.entidade.Pessoa;
import br.com.cast.projgson.persistencia.PessoaDAO;

@Service
public class PessoaBusiness {

	@Autowired
	private PessoaDAO pessoadao;

	/*public PessoaBusiness() {
		this.pessoadao = new PessoaDAO();
		this.enderecodao = new EnderecoDAO();
	}*/

	@Transactional
	public void salvar(PessoaDTO pessoadto) {
		
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(pessoadto.getCpf());
		pessoa.setEmail(pessoadto.getEmail());
		pessoa.setNome(pessoadto.getNome());
				
		EnderecoDTO enderecodto = pessoadto.getEnderecodto();
		
		Endereco endereco = new Endereco();		
		endereco.setCep(enderecodto.getCep());
		endereco.setBairro(enderecodto.getBairro());
		endereco.setCidade(enderecodto.getCidade());
		endereco.setComplemento(enderecodto.getComplemento());
		endereco.setLogradouro(enderecodto.getLogradouro());
		endereco.setNumero(enderecodto.getNumero());
		endereco.setUf(enderecodto.getUf());
		
		pessoa.setEndereco(endereco);
		
		

		if (pessoadao.buscarPorCPF(pessoadto.getCpf()) != null) {
			pessoadao.alterar(pessoa);
		} else {
			pessoadao.inserir(pessoa);
		}

	}



	public void excluir(String cpf) {
		Pessoa pessoa = pessoadao.buscarPorCPF(cpf);
		pessoadao.excluir(pessoa);
	}

	public List<PessoaDTO> buscarTodos() {
	
	  List<Pessoa> pessoas = pessoadao.buscarTodos(); 
	  List<PessoaDTO> pessoasdto = new ArrayList<>();
	  
	  for (Pessoa pessoa : pessoas) { PessoaDTO pessoadto = new PessoaDTO();
	  pessoadto.setCpf(pessoa.getCpf()); pessoadto.setEmail(pessoa.getEmail());
	  pessoadto.setNome(pessoa.getNome());
	  
	  Endereco endereco = pessoa.getEndereco(); 
	  if(endereco != null) { 
		  EnderecoDTO enderecodto = new EnderecoDTO(); 
		  enderecodto.setCep(endereco.getCep());
		  enderecodto.setBairro(endereco.getBairro());
		  enderecodto.setCidade(endereco.getCidade());
		  enderecodto.setComplemento(endereco.getComplemento());
		  enderecodto.setLogradouro(endereco.getLogradouro());
		  enderecodto.setNumero(endereco.getNumero());
		  enderecodto.setUf(endereco.getUf()); 
		  pessoadto.setEnderecodto(enderecodto);;
	  }
	  
	  
	  pessoasdto.add(pessoadto); } return pessoasdto; }
	 

}
