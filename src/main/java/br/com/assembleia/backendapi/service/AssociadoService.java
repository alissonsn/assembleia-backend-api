package br.com.assembleia.backendapi.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.assembleia.backendapi.exception.CpfJaAssociadoException;
import br.com.assembleia.backendapi.model.Associado;
import br.com.assembleia.backendapi.repository.AssociadoRepository;
import reactor.core.publisher.Mono;

/**
 * 
 * @author Alisson Nascimento
 *
 */

@Service
@Transactional
public class AssociadoService extends AbstractService<Associado, AssociadoRepository> {
	
	public AssociadoService(AssociadoRepository repository) {
		super(repository);
	}
	
	@Override
	public Mono<Associado> save(Associado entity) {
		
		if (repository.existsByCpf(entity.getCpf()))
			throw new CpfJaAssociadoException("O CPF informado já está cadastro em algum associado.");
		
		return super.save(entity);
		
	}

}
