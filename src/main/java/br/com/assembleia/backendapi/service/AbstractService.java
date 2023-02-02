package br.com.assembleia.backendapi.service;

import br.com.assembleia.backendapi.model.AbstractEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public class AbstractService<T extends AbstractEntity, R extends ReactiveCrudRepository<T, Long>>  {
	
	protected final R repository;
	
	public AbstractService(R repository) {
		this.repository = repository;
	}
	
	public Flux<T> findAll() {
		return repository.findAll();
	}
	
	public Mono<T> save(T entity) {
		return repository.save(entity);
	}
	
	public Mono<T> update(T entity) {
		return repository.save(entity); 
	}
	
	public Mono<T> findById(Long id) {
		return repository.findById(id);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public Mono<Void> delete(T entity) {
		return repository.delete(entity);
	}

	public Mono<Long> count() {
		return repository.count();
	}
	
}
