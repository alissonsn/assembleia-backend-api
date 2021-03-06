package br.com.assembleia.backendapi.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.assembleia.backendapi.exception.SessaoVotacaoException;
import br.com.assembleia.backendapi.exception.VotoException;
import br.com.assembleia.backendapi.model.Associado;
import br.com.assembleia.backendapi.model.Pauta;
import br.com.assembleia.backendapi.model.SessaoVotacao;
import br.com.assembleia.backendapi.model.Voto;
import br.com.assembleia.backendapi.repository.SessaoVotacaoRepository;

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

	public List<SessaoVotacao> findSessoesAtivas() {
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
	 * @throws UnableToVoteException
	 */
	public Voto votar(Long idAssociado, Long idPauta, Boolean voto) throws SessaoVotacaoException, VotoException {

		Associado associado = associadoService.findById(idAssociado);
		
		if(userInfoService.verificarAssociadoVotante(associado.getCpf())) {

			SessaoVotacao sv = repository.findByPautaEquals(new Pauta(idPauta));

			if(!sv.hasNotTimedOut()) {
				throw new SessaoVotacaoException();
			}

			Boolean existeVoto = votoService.existsByAssociadoAndSessao(associado.getId(), sv.getId());

			if(existeVoto) {
				throw new VotoException("Associado já registrou voto nesta sessão.");
			}

			Voto novoVoto = new Voto();
			novoVoto.setAssociado(associado);
			novoVoto.setVoto(voto);
			novoVoto.setSessao(sv);

			novoVoto = votoService.save(novoVoto);
			sv.getVotos().add(novoVoto);
			update(sv);

			return novoVoto;

		} else {
			throw new VotoException("Associado não habilitado para votação.");
		}

	}

}
