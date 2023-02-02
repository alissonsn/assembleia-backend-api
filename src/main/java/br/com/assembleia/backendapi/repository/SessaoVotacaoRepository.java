package br.com.assembleia.backendapi.repository;

import br.com.assembleia.backendapi.model.Pauta;
import br.com.assembleia.backendapi.model.SessaoVotacao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public interface SessaoVotacaoRepository extends ReactiveCrudRepository<SessaoVotacao, Long> {

	SessaoVotacao findByPautaEquals(Pauta pauta);
	
	Flux<SessaoVotacao> findByAtivaIsTrue();
	
}
