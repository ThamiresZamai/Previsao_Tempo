package br.com.cast.projgson.persistencia;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.cast.projgson.entidade.Endereco;


public class EnderecoDAO {
	
private EntityManager em;
	
	public EnderecoDAO() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoWeb");
		em = emf.createEntityManager();
	}
	
	
	public void inserir(Endereco endereco) {
		em.getTransaction().begin();
		try {
			em.persist(endereco);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	
	public Endereco buscarPorCEP(String cep) {
		return em.find(Endereco.class, cep);
	}
	
	public void alterar(Endereco endereco) {
		em.getTransaction().begin();
		try {
			em.merge(endereco);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	
	public void excluir(Endereco endereco) {
		em.getTransaction().begin();
		try {
			em.remove(endereco);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	


}
