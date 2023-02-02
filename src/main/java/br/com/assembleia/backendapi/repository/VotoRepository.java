package br.com.assembleia.backendapi.repository;

import br.com.assembleia.backendapi.model.Associado;
import br.com.assembleia.backendapi.model.SessaoVotacao;
import br.com.assembleia.backendapi.model.Voto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public interface VotoRepository extends ReactiveCrudRepository<Voto, Long> {

	Boolean existsByAssociadoEqualsAndSessaoEquals(Associado associado, SessaoVotacao sessao);
	
}
