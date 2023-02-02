package br.com.assembleia.backendapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.assembleia.backendapi.controller.dto.RequestVoto;
import br.com.assembleia.backendapi.controller.dto.ResponseVoto;
import br.com.assembleia.backendapi.exception.SessaoVotacaoException;
import br.com.assembleia.backendapi.exception.VotoException;
import br.com.assembleia.backendapi.model.SessaoVotacao;
import br.com.assembleia.backendapi.repository.SessaoVotacaoRepository;
import br.com.assembleia.backendapi.service.SessaoVotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Mono;

/**
 * @author Alisson Nascimento
 *
 */

@RestController
@RequestMapping("sessao-votacao")
@Api(value = "sessao-votacao")
@CrossOrigin(origins = "*")
public class SessaoVotacaoController extends AbstractController<SessaoVotacao, SessaoVotacaoRepository, SessaoVotacaoService> {
	
	public SessaoVotacaoController(SessaoVotacaoService service) {
		super(service);
	}

	/**
	 * Método responsável por registrar o voto na sessão de votação
	 *
	 * @param votoDTO
	 * @return
	 * @throws SessaoVotacaoException
	 * @throws VotoException
	 */
	@ApiOperation(value = "Método responsável por registrar o voto na sessão de votação", code = 200)
	@PostMapping("/votar")
	public ResponseEntity<Mono<ResponseVoto>> votar(@RequestBody RequestVoto votoDTO)
			throws SessaoVotacaoException, VotoException {
		var monoVoto = service.votar(votoDTO.getIdAssociado(), votoDTO.getIdPauta(), votoDTO.getVoto());

		var monoResponseVoto = monoVoto.switchIfEmpty(Mono.empty()).flatMap(v -> {
			var responseVoto = new ResponseVoto();
			responseVoto.setNome(v.getAssociado().getNome());
			responseVoto.setPauta(v.getSessao().getPauta().getNome());
			responseVoto.setVoto(v.getVoto());
			return Mono.just(responseVoto);
		});

		return new ResponseEntity<>(monoResponseVoto, HttpStatus.OK);
	}

}
