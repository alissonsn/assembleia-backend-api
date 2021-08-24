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
import br.com.assembleia.backendapi.model.Voto;
import br.com.assembleia.backendapi.repository.SessaoVotacaoRepository;
import br.com.assembleia.backendapi.service.SessaoVotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
	 * 
	 * @param idAssociado
	 * @param idPauta
	 * @param voto
	 * @return
	 * @throws SessaoVotacaoException
	 * @throws VotoException
	 */
	@ApiOperation(value = "Método responsável por registrar o voto na sessão de votação", code = 200)
	@PostMapping("/votar")
	public ResponseEntity<ResponseVoto> votar(@RequestBody RequestVoto votoDTO) 
			throws SessaoVotacaoException, VotoException {
		Voto v = service.votar(votoDTO.getIdAssociado(), votoDTO.getIdPauta(), votoDTO.getVoto());
		
		ResponseVoto responseVoto = new ResponseVoto();
		responseVoto.setNome(v.getAssociado().getNome());
		responseVoto.setPauta(v.getSessao().getPauta().getNome());
		responseVoto.setVoto(v.getVoto());
		
		return new ResponseEntity<ResponseVoto>(responseVoto, HttpStatus.OK);
	}

}
