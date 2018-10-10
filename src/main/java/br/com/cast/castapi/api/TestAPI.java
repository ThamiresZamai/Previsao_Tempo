package br.com.cast.castapi.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.projgson.DTO.PessoaDTO;

@RestController
@RequestMapping(path = "pessoa")
public class TestAPI {

	
	@RequestMapping(method = RequestMethod.GET)
	public List<PessoaDTO> buscarTodos() {
		PessoaDTO pessoadto = new PessoaDTO();
		List<PessoaDTO> lstpessoa =new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			pessoadto.setNome("Thamires" + i);
			pessoadto.setEmail("thamires@zamai.com" + i);
			pessoadto.setCpf("123456789-" + i);
			lstpessoa.add(pessoadto);
		}
		return lstpessoa;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String inserir(@RequestBody PessoaDTO pessoadto) {
		return "Testando método inserir " + pessoadto.getNome();
	}
	
	@RequestMapping(path="/{cpf}", method=RequestMethod.GET)
	public String buscarPorCpf(@PathVariable("cpf") String cpf) {
		return "cpf que veio por parametro: " + cpf;
	}

	@RequestMapping(method=RequestMethod.DELETE)
	public String excluir(@RequestBody PessoaDTO pessoadto) {
		return "Excluiu pessoa com cfp = "+ pessoadto.getCpf();
	}
}
