package br.com.assembleia.backendapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.assembleia.backendapi.model.Pauta;
import br.com.assembleia.backendapi.repository.PautaRepository;

/**
 * 
 * @author Alisson Nascimento
 *
 */

@Service
@Transactional
public class PautaService extends AbstractService<Pauta, PautaRepository> {

	public PautaService(PautaRepository repository) {
		super(repository);
	}
	
}
