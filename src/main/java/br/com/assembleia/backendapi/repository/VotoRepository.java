package br.com.assembleia.backendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.assembleia.backendapi.model.Associado;
import br.com.assembleia.backendapi.model.SessaoVotacao;
import br.com.assembleia.backendapi.model.Voto;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public interface VotoRepository extends JpaRepository<Voto, Long> {

	Boolean existsByAssociadoEqualsAndSessaoEquals(Associado associado, SessaoVotacao sessao);
	
}
