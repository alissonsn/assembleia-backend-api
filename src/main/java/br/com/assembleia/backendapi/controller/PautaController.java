package br.com.assembleia.backendapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.assembleia.backendapi.model.Pauta;
import br.com.assembleia.backendapi.repository.PautaRepository;
import br.com.assembleia.backendapi.service.PautaService;

/**
 * 
 * @author Alisson Nascimento
 *
 */

@RestController
@RequestMapping("pauta")
public class PautaController extends AbstractController<Pauta, PautaRepository, PautaService> {

	public PautaController(PautaService service) {
		super(service);
	}


}
