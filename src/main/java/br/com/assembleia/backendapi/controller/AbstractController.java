package br.com.assembleia.backendapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
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

/**
 * 
 * @author Alisson Nascimento
 *
 * @param <T>
 * @param <R>
 * @param <S>
 */
public class AbstractController<T extends AbstractEntity, R extends JpaRepository<T,Long>, S extends AbstractService<T, R>> {

	protected final S service;
	
	public AbstractController(S service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<T>> findAll() {
		List<T> entities = service.findAll();
		
		return new ResponseEntity<List<T>>(entities, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<T> save(@Valid @RequestBody T entity) {
		T created = (T) service.save(entity);
		
		if(created == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
		}
		
		return new ResponseEntity<T>(created, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<T> update(@Valid @RequestBody T entity) {
		T updated = (T) service.save(entity);
		
		return new ResponseEntity<T>(updated, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<T> getById(@PathVariable Long id){
		T entity = (T) service.findById(id);
		
		if(entity == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);			
		}
		
		return new ResponseEntity<T>(entity, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<T> deleteById(@PathVariable Long id) {
		T entity = (T) service.findById(id);
		
		if(entity == null || entity.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		service.delete(entity);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
