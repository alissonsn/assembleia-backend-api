package br.com.assembleia.backendapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.assembleia.backendapi.model.Associado;
import br.com.assembleia.backendapi.model.SessaoVotacao;
import br.com.assembleia.backendapi.model.Voto;
import br.com.assembleia.backendapi.repository.VotoRepository;

/**
 * 
 * @author Alisson Nascimento
 *
 */

@Service
@Transactional
public class VotoService extends AbstractService<Voto, VotoRepository> {

	public VotoService(VotoRepository repository) {
		super(repository);
	}

	public Voto findByAssociadoEquals(Associado associado) {
		return repository.findByAssociadoEquals(associado);
	}
	
	public Boolean existsByAssociadoAndSessao(Long idAssociado, Long idSessao) {
		return repository.existsByAssociadoEqualsAndSessaoEquals(new Associado(idAssociado), new SessaoVotacao(idSessao));
	}
}
