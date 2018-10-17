package br.com.cast.castapitempo.weather;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.cast.castapitempo.DTO.WeatherDTO;

@Component
public class Weather {

	private static final String URL_TEMPO = "http://api.openweathermap.org/data/2.5/forecast?q={cidade},br&units=metric&mode=json&appid={appid}&lang=pt";
	private static final String appid = "2a1ed974a4e86642a69ac57828175631";
	
	private RestTemplate resttamplete;
	
	public Weather(RestTemplateBuilder builder) {
		this.resttamplete = builder.build();
	}
	
	public WeatherDTO getPrevisao(String cidade){
		WeatherDTO resposta = this.resttamplete.getForObject(URL_TEMPO, WeatherDTO.class, cidade, appid);
		return resposta;
	}
}
