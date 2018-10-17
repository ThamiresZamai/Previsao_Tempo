package br.com.cast.castapitempo.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherMainDTO {

	@JsonProperty("temp_max")
	private String tempmax;
	@JsonProperty("temp_min")
	private String tempmin;
	@JsonProperty("humidity")
	private String umidade;
	
	
	public String getTempmax() {
		return tempmax;
	}
	public void setTempmax(String tempmax) {
		this.tempmax = tempmax;
	}
	public String getTempmin() {
		return tempmin;
	}
	public void setTempmin(String tempmin) {
		this.tempmin = tempmin;
	}
	public String getUmidade() {
		return umidade;
	}
	public void setUmidade(String umidade) {
		this.umidade = umidade;
	}
	
	
	
}
