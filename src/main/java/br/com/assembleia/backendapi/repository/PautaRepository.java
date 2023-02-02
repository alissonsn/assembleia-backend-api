package br.com.assembleia.backendapi.repository;

import br.com.assembleia.backendapi.model.Pauta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public interface PautaRepository extends ReactiveCrudRepository<Pauta, Long> {

}
