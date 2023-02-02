package br.com.assembleia.backendapi.repository;

import br.com.assembleia.backendapi.model.Associado;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public interface AssociadoRepository extends ReactiveCrudRepository<Associado, Long> {

	boolean existsByCpf(String cpf);
	
}
