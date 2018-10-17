package br.com.cast.castapitempo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.castapitempo.DTO.ResultWeatherDTO;
import br.com.cast.castapitempo.business.WeatherBusiness;

@RestController
@RequestMapping(path = "/tempo")
public class TempoAPI {
	
	@Autowired
	private WeatherBusiness weaterb;
	
	@GetMapping(path = "/{cidade}")
	public List<ResultWeatherDTO> buscarPrevisao(@PathVariable("cidade") String cidade) {
		return weaterb.buscar(cidade);
	}
}
