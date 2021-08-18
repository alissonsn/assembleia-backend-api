package br.com.assembleia.backendapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.assembleia.backendapi.service.NotificationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Runner implements CommandLineRunner {

	private final NotificationService rabbitService;

	public Runner(NotificationService rabbitService) {
		this.rabbitService = rabbitService;
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("");
		rabbitService.sendInicioSistemaAssembleia();
	}

}
