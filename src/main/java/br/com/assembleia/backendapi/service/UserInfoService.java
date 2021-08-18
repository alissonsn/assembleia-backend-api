package br.com.assembleia.backendapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.assembleia.backendapi.model.AssociadoStatus;
import br.com.assembleia.backendapi.model.StatusEnum;

/**
 * 
 * 
 * 
 * @author Alisson Nascimento
 *
 */
@Service
public class UserInfoService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${user-info.url}")
	private String urlUserInfo;

	/**
	 * Verifica se o associado está habilitado para registrar voto
	 * 
	 * @param cpf
	 * @return String
	 * @throws UnableToVoteException 
	 */
	public boolean verificarAssociadoVotante(String cpf) {
		return Optional.ofNullable(restTemplate.getForObject
				(urlUserInfo+cpf, AssociadoStatus.class)).
					map(u -> u.getStatus()).orElse("").equals(StatusEnum.ABLE_TO_VOTE.getStatus());
	}
	
}
