package br.com.assembleia.backendapi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.assembleia.backendapi.repository.SessaoVotacaoRepository;

@ExtendWith(MockitoExtension.class)
public class SessaoVotacaoServiceTest {
	
	@Mock
	private VotoService votoService;

	@Mock
	private AssociadoService associadoService;
	
	@Mock
	private UserInfoService userInfoService;
	
	@Mock
	private SessaoVotacaoRepository repository;
	
	@InjectMocks
	private SessaoVotacaoService sessaoVotacaoService;

    @Test
    void testVotar() {

    }

}
