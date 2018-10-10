package br.com.cast.projgson.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.cast.projgson.entidade.Pessoa;

@Repository
public class PessoaDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	/*public PessoaDAO() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoWeb");
		em = emf.createEntityManager();
	}*/
	
	
	public void inserir(Pessoa pessoa) {
			em.persist(pessoa);
	}
	
	public Pessoa buscarPorCPF(String cpf) {
		return em.find(Pessoa.class, cpf);
	}
	
	public void alterar(Pessoa pessoa) {
		em.getTransaction().begin();
		try {
			em.merge(pessoa);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	
	public void excluir(Pessoa pessoa) {
		em.getTransaction().begin();
		try {
			em.remove(pessoa);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> buscarTodos() {
		return em.createQuery( "SELECT p FROM Pessoa p join fetch p.endereco").getResultList();
		
	}

}
