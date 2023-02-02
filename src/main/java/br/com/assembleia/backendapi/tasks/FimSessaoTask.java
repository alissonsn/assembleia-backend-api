package br.com.assembleia.backendapi.tasks;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.assembleia.backendapi.model.SessaoVotacao;
import br.com.assembleia.backendapi.service.NotificationService;
import br.com.assembleia.backendapi.service.SessaoVotacaoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
		log.info("Finalizando votações: {}", LocalDateTime.now());
		
		Flux<SessaoVotacao> sessoesAtivas = sessaoVotacaoService.findSessoesAtivas();
		
		sessoesAtivas.filter(SessaoVotacao::hasNotTimedOut).flatMap(s -> {
			s.setAtiva(false);
			sessaoVotacaoService.save(s);
			notificationService.sendVotacaoResultadoAssembleia(s.getVotosTotal());
			return Mono.just(s);
		});
	}
	
}
