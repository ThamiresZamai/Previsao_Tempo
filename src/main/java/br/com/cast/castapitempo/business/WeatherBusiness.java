package br.com.cast.castapitempo.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cast.castapitempo.DAO.TempoDAO;
import br.com.cast.castapitempo.DTO.ResultWeatherDTO;
import br.com.cast.castapitempo.DTO.WeatherDTO;
import br.com.cast.castapitempo.DTO.WeatherDataDTO;
import br.com.cast.castapitempo.entidade.Tempo;
import br.com.cast.castapitempo.weather.Weather;

@Service
public class WeatherBusiness {

	@Autowired
	private Weather weather;
	@Autowired
	private TempoDAO tempodao;

	@Transactional
	public List<ResultWeatherDTO> buscar(String cidade) {

		// buscar registros no banco
		List<ResultWeatherDTO> previsoes = buscarCidade(cidade);

		// verificar se a quantidade é >= 5
		if(previsoes.size() >= 5) {
			// sim:
			//      converter a List<Tempo> em List<TempoDTO>
			return previsoes;
		}
		// não:
		//      busca os dados da api
		//      remove os dados do banco
		//      seta os dados de WeatherDTO na entidade Tempo e insere
		//      retornar a List<TempoDTO>
		tempodao.remover(cidade);
		WeatherDTO dto = weather.getPrevisao(cidade);
		List<ResultWeatherDTO> weaterddto = new ArrayList<>();

		Map<String, WeatherDataDTO> mapa = new HashMap<>();

		for (WeatherDataDTO dataDTO : dto.getList()) {
			String data = getDataComoString(dataDTO.getData(), "yyyy-MM-dd");
			if (!mapa.containsKey(data)) {
				mapa.put(data, dataDTO);
				
				Tempo tempo = inserir(dataDTO, cidade);
				
				ResultWeatherDTO result = convertEntidadeParaResultDTO(tempo);
				
				weaterddto.add(result);
			}
		}
		
		return weaterddto;
	}

	private ResultWeatherDTO convertEntidadeParaResultDTO(Tempo tempo) {
		ResultWeatherDTO result = new ResultWeatherDTO();
		result.setTemperaturaMax(tempo.getTempmax());
		result.setTemperaturaMin(tempo.getTempmin());
		result.setData(getDataComoString(tempo.getDttxt(), "dd/MM/yyyy"));
		result.setUmidade(tempo.getUmidade());
		result.setDescricao(tempo.getDescricao());
		return result;
	}

	public Tempo inserir(WeatherDataDTO weatherdto, String cidade) {
		Tempo tempo = new Tempo();
		tempo.setCidade(cidade.toLowerCase());
		tempo.setDttxt(weatherdto.getData());
		tempo.setDescricao(weatherdto.getWeather().get(0).getDescription());
		tempo.setTempmax(weatherdto.getMain().getTempmax());
		tempo.setTempmin(weatherdto.getMain().getTempmin());
		tempo.setUmidade(weatherdto.getMain().getUmidade());
		tempodao.inserir(tempo);
		return tempo;
	}

	public String getDataComoString(Date data, String formato) {
		SimpleDateFormat df = new SimpleDateFormat(formato);
		return df.format(data);
	}
	
	
	public List<ResultWeatherDTO> buscarCidade(String cidade){
		List<Tempo> lsttempo = tempodao.buscarCidade(cidade);
		List<ResultWeatherDTO> lstrwdto = new ArrayList<>();
		for(Tempo tempo : lsttempo) {
			ResultWeatherDTO result = new ResultWeatherDTO();
			result.setTemperaturaMax(tempo.getTempmax());
			result.setTemperaturaMin(tempo.getTempmin());
			result.setData(getDataComoString(tempo.getDttxt(), "dd/MM/yyyy"));
			result.setUmidade(tempo.getUmidade());
			result.setDescricao(tempo.getDescricao());
			lstrwdto.add(result);
		}
		
		return lstrwdto;
	}
	
	
	

}
