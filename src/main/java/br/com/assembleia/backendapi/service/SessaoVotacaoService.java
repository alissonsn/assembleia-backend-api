package br.com.assembleia.backendapi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.assembleia.backendapi.exception.SessaoVotacaoException;
import br.com.assembleia.backendapi.exception.VotoException;
import br.com.assembleia.backendapi.model.Pauta;
import br.com.assembleia.backendapi.model.SessaoVotacao;
import br.com.assembleia.backendapi.model.Voto;
import br.com.assembleia.backendapi.repository.SessaoVotacaoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author Alisson Nascimento
 *
 */

@Service
@Transactional
public class SessaoVotacaoService extends AbstractService<SessaoVotacao, SessaoVotacaoRepository> {

	
	private final VotoService votoService;

	private final AssociadoService associadoService;
	
	private final UserInfoService userInfoService;

	public SessaoVotacaoService(SessaoVotacaoRepository repository, VotoService votoService, AssociadoService associadoService, UserInfoService userInfoService) {
		super(repository);
		this.votoService = votoService;
		this.associadoService = associadoService;
		this.userInfoService = userInfoService;
	}

	public Flux<SessaoVotacao> findSessoesAtivas() {
		return repository.findByAtivaIsTrue();
	}

	/**
	 * 
	 * @param idAssociado
	 * @param idPauta
	 * @param voto
	 * @return
	 * @throws SessaoVotacaoException
	 * @throws VotoException
	 */
	public Mono<Voto> votar(Long idAssociado, Long idPauta, Boolean voto) throws SessaoVotacaoException, VotoException {

		var sv = repository.findByPautaEquals(new Pauta(idPauta));

		if(Boolean.FALSE.equals(sv.hasNotTimedOut())) {
			throw new SessaoVotacaoException();
		}

		var existeVoto = votoService.existsByAssociadoAndSessao(idAssociado, sv.getId());

		if(Boolean.TRUE.equals(existeVoto)) {
			throw new VotoException("Associado já registrou voto nesta sessão.");
		}

		var associadoMono = associadoService.findById(idAssociado);

		return associadoMono.
				filter(associado -> userInfoService.verificarAssociadoVotante(associado.getCpf())).
				flatMap(associado -> {
					var novoVoto = new Voto();
					novoVoto.setAssociado(associado);
					novoVoto.setVoto(voto);
					novoVoto.setSessao(sv);

					Mono<Voto> monoVoto = votoService.save(novoVoto);
					sv.getVotos().add(novoVoto);
					update(sv);

					return monoVoto;
				});

	}

}
