package br.com.cast.castapitempo.DTO;

public class ResultWeatherDTO {

	private String temperaturaMin;
	private String temperaturaMax;
	private String data;
	private String umidade;
	private String descricao;

	public String getTemperaturaMin() {
		return temperaturaMin;
	}

	public void setTemperaturaMin(String temperaturaMin) {
		this.temperaturaMin = temperaturaMin;
	}

	public String getTemperaturaMax() {
		return temperaturaMax;
	}

	public void setTemperaturaMax(String temperaturaMax) {
		this.temperaturaMax = temperaturaMax;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUmidade() {
		return umidade;
	}

	public void setUmidade(String umidade) {
		this.umidade = umidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
