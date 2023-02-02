package br.com.assembleia.backendapi.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.assembleia.backendapi.model.AbstractEntity;
import br.com.assembleia.backendapi.service.AbstractService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author Alisson Nascimento
 *
 * @param <T>
 * @param <R>
 * @param <S>
 */
public class AbstractController<T extends AbstractEntity, R extends ReactiveCrudRepository<T,Long>, S extends AbstractService<T, R>> {

	protected final S service;
	
	public AbstractController(S service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<Flux<T>> findAll() {
		Flux<T> entities = service.findAll();
		
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Mono<T>> save(@Valid @RequestBody T entity) {
		Mono<T> created = service.save(entity);
		
		if(created == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
		}
		
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Mono<T>> update(@Valid @RequestBody T entity) {
		Mono<T> updated = service.save(entity);
		
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mono<T>> getById(@PathVariable Long id){
		Mono<T> entity = service.findById(id);
		
		if(entity == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);			
		}
		
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Mono<T>> deleteById(@PathVariable Long id) {
		Mono<T> mono = service.findById(id);

		if (Objects.isNull(mono))
			return new ResponseEntity<>(Mono.empty(), HttpStatus.BAD_REQUEST);

		Mono<T> result = mono.
				switchIfEmpty(Mono.empty()).
				filter(Objects::nonNull).
				flatMap(studentToBeDeleted -> service.delete(studentToBeDeleted).then(Mono.just(studentToBeDeleted)));

		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
}
