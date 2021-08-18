package br.com.assembleia.backendapi.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.assembleia.backendapi.model.AbstractEntity;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public class AbstractService<T extends AbstractEntity, R extends JpaRepository<T, Long>>  {
	
	protected final R repository;
	
	public AbstractService(R repository) {
		this.repository = repository;
	}
	
	public List<T> findAll() {
		return repository.findAll();
	}
	
	public T save(T entity) {		
		return repository.save(entity);
	}
	
	public T update(T entity) {
		return repository.save(entity); 
	}
	
	public T findById(Long id) {
		return repository.findById(id).get();
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public void delete(T entity) {
		repository.delete(entity);
	}

	public long count() {
		return repository.count();
	}
	
}
