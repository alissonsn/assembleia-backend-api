package br.com.assembleia.backendapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.assembleia.backendapi.model.Pauta;
import br.com.assembleia.backendapi.model.SessaoVotacao;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

	SessaoVotacao findByPautaEquals(Pauta Pauta);
	
	List<SessaoVotacao> findByAtivaIsTrue();
	
}
