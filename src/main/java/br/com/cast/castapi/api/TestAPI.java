package br.com.cast.castapi.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.castapi.DTO.PessoaDTO;
import br.com.cast.castapi.business.PessoaBusiness;

@RestController
@RequestMapping(path = "pessoa")
public class TestAPI {

	@Autowired
	private PessoaBusiness pessoabusines;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<PessoaDTO> buscarTodos() {
		/*PessoaDTO pessoadto = new PessoaDTO();
		List<PessoaDTO> lstpessoa =new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			pessoadto.setNome("Thamires" + i);
			pessoadto.setEmail("thamires@zamai.com" + i);
			pessoadto.setCpf("123456789-" + i);
			lstpessoa.add(pessoadto);
		}
		return lstpessoa;*/
		return pessoabusines.buscarTodos();
	}

	@RequestMapping(method = RequestMethod.POST)
	public void inserir(@RequestBody PessoaDTO pessoadto) {
		//return "Testando método inserir " + pessoadto.getNome();
		pessoabusines.salvar(pessoadto);
	}
	
	@RequestMapping(path="/{cpf}", method=RequestMethod.GET)
	public PessoaDTO buscarPorCpf(@PathVariable("cpf") String cpf) {
		//return "cpf que veio por parametro: " + cpf;
		return pessoabusines.buscarPorCPF(cpf);
	}

	@RequestMapping(path="/{cpf}", method=RequestMethod.DELETE)
	public void excluir(@PathVariable("cpf") String cpf) {
		//return "Excluiu pessoa com cfp = "+ pessoadto.getCpf();
		pessoabusines.excluir(cpf);
	}
}
