package br.com.cast.castapi.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cast.castapi.DTO.EnderecoDTO;
import br.com.cast.castapi.DTO.PessoaDTO;
import br.com.cast.castapi.entidade.Endereco;
import br.com.cast.castapi.entidade.Pessoa;
import br.com.cast.castapi.persistencia.PessoaDAO;

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


	@Transactional
	public void excluir(String cpf) {
		Pessoa pessoa = pessoadao.buscarPorCPF(cpf);
		pessoadao.excluir(pessoa);
	}

	public List<PessoaDTO> buscarTodos() {
	
	  List<Pessoa> pessoas = pessoadao.buscarTodos(); 
	  List<PessoaDTO> pessoasdto = new ArrayList<>();
	  
	  for (Pessoa pessoa : pessoas) { 
		  PessoaDTO pessoadto = new PessoaDTO();
		  pessoadto.setCpf(pessoa.getCpf()); 
		  pessoadto.setEmail(pessoa.getEmail());
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
	  
	  
	  pessoasdto.add(pessoadto); 
	  } 
	  
	  return pessoasdto; 
	  
	}
	
	
	public PessoaDTO buscarPorCPF(String cpf) {
		Pessoa pessoa = pessoadao.buscarPorCPF(cpf);
		
		  PessoaDTO pessoadto = new PessoaDTO();
		  pessoadto.setCpf(pessoa.getCpf()); 
		  pessoadto.setEmail(pessoa.getEmail());
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
		return pessoadto;
	}
	 

}
