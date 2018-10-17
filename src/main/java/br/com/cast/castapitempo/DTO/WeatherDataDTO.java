package br.com.cast.castapitempo.DTO;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherDataDTO {

	@JsonProperty("dt_txt")
	@JsonFormat(pattern="yyyy-MM-dd hh:mm")
	private Date data;
	private WeatherMainDTO main;
	private List<WeatherWeatherDTO> weather;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public WeatherMainDTO getMain() {
		return main;
	}

	public void setMain(WeatherMainDTO main) {
		this.main = main;
	}

	public List<WeatherWeatherDTO> getWeather() {
		return weather;
	}

	public void setWweather(List<WeatherWeatherDTO> weather) {
		this.weather = weather;
	}
	
	
}
