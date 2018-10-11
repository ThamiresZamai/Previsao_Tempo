package br.com.cast.castapi.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.cast.castapi.entidade.Pessoa;

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
		em.merge(pessoa);
	}
	
	public void excluir(Pessoa pessoa) {
		 em.remove(pessoa);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> buscarTodos() {
		return em.createQuery( "SELECT p FROM Pessoa p join fetch p.endereco").getResultList();
		
	}

}
