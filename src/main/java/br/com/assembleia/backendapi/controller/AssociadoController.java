package br.com.assembleia.backendapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.assembleia.backendapi.model.Associado;
import br.com.assembleia.backendapi.repository.AssociadoRepository;
import br.com.assembleia.backendapi.service.AssociadoService;

/**
 * 
 * @author Alisson Nascimento
 *
 */

@RestController
@RequestMapping("associado")
public class AssociadoController extends AbstractController<Associado, AssociadoRepository, AssociadoService> {

	public AssociadoController(AssociadoService service) {
		super(service);
	}

}
