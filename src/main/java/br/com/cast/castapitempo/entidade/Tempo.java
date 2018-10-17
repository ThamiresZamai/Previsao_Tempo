package br.com.cast.castapitempo.entidade;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tempo", schema = "catapitempo")
public class Tempo {

	@Id
	@SequenceGenerator(name = "gerador_tempo_seq", sequenceName = "catapitempo.tempo_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gerador_tempo_seq")
	private Integer id;
	private String tempmax;
	private String tempmin;
	private String descricao;
	private String umidade;
	private String cidade;
	private Date dttxt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUmidade() {
		return umidade;
	}

	public void setUmidade(String umidade) {
		this.umidade = umidade;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Date getDttxt() {
		return dttxt;
	}

	public void setDttxt(Date dttxt) {
		this.dttxt = dttxt;
	}

	
}
