package br.com.cast.castapitempo.DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.cast.castapitempo.entidade.Tempo;

@Repository
public class TempoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void inserir(Tempo tempo) {
		em.persist(tempo);
	}
	
	@SuppressWarnings("unchecked")
	public List<Tempo> buscarCidade(String cidade) {
		Date data = new Date();
		return em.createQuery(" FROM " + Tempo.class.getName() + 
				" where lower(cidade)= lower(:cidade) and dttxt >= :data")
				.setParameter("cidade", cidade)
				.setParameter("data", data)
				.getResultList();
	}
	
	public void remover(String cidade) {
		em.createQuery("DELETE FROM " + Tempo.class.getName() + 
				" WHERE lower(cidade) = lower(:cidade) ")
			.setParameter("cidade", cidade)
			.executeUpdate();
	}
}
