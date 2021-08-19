package br.com.assembleia.backendapi.tasks;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.assembleia.backendapi.model.SessaoVotacao;
import br.com.assembleia.backendapi.service.NotificationService;
import br.com.assembleia.backendapi.service.SessaoVotacaoService;

/**
 * 
 * Task responsável por finalizar as sessões criadas
 * 
 * @author Alisson Nascimento
 *
 */
@Component
public class FimSessaoTask {

	private static final Logger log = LoggerFactory.getLogger(FimSessaoTask.class);

	private final SessaoVotacaoService sessaoVotacaoService;
	
	private final NotificationService notificationService;
	
	public FimSessaoTask(SessaoVotacaoService sessaoVotacaoService, NotificationService notificationService) {
		this.sessaoVotacaoService = sessaoVotacaoService;
		this.notificationService = notificationService;
	}

	@Scheduled(fixedRate = 60000)
	public void reportCurrentTime() {
		log.info("Finalizando votações: ", LocalDateTime.now());
		
		List<SessaoVotacao> sessoesAtivas = sessaoVotacaoService.findSessoesAtivas();
		
		sessoesAtivas.stream().filter(s -> s.hasNotTimedOut()).forEach(s -> {
			s.setAtiva(false);
			sessaoVotacaoService.save(s);
			notificationService.sendVotacaoResultadoAssembleia(s.getVotosTotal());
		});
	}
	
}
