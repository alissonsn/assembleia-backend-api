package br.com.assembleia.backendapi.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final RabbitService rabbitService;

    private final String ASSEMBLEIA_VOTACAO_RESULTADO = "assembleia.votacao.resultado";
    private final String INICIO_SISTEMA_ASSEMBLEIA = "assembleia.inicio.sistema";

    @Autowired
    public NotificationService(RabbitService rabbitService) {
        this.rabbitService = rabbitService;
    }
    
    public void sendInicioSistemaAssembleia() {
        this.sendNotification(INICIO_SISTEMA_ASSEMBLEIA, "Início do Sistema de Assembleia " + LocalDateTime.now().toString());
    }

    public void sendVotacaoResultadoAssembleia(String msg) {
        this.sendNotification(ASSEMBLEIA_VOTACAO_RESULTADO, msg);
    }

    private void sendNotification(String routingKey, String message) {
        rabbitService.sendMessage(routingKey, message);
    }
}
