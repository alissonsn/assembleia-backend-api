package br.com.assembleia.backendapi.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.assembleia.backendapi.exception.SessaoVotacaoException;
import br.com.assembleia.backendapi.exception.VotoException;
import br.com.assembleia.backendapi.model.Associado;
import br.com.assembleia.backendapi.model.Pauta;
import br.com.assembleia.backendapi.model.SessaoVotacao;
import br.com.assembleia.backendapi.model.Voto;
import br.com.assembleia.backendapi.repository.SessaoVotacaoRepository;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class SessaoVotacaoServiceTest {
	
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
    void whenAssociadoNaoHabilitado_thenThrowVotoException() {
    	Associado associado = new Associado();
    	associado.setCpf("27863857090");
    	associado.setNome("Associado Testes da Silva");
    	associado.setId(1l);
    	
    	Long idPauta = 1l;
    	Pauta pauta = new Pauta(idPauta);
    	
    	SessaoVotacao sessaoVotacao = new SessaoVotacao();
    	sessaoVotacao.setId(1l);
    	sessaoVotacao.setPauta(pauta);
    	sessaoVotacao.setTempoMinutos("-10");
    	
    	when(associadoService.findById(associado.getId())).thenReturn(Mono.just(associado));
    	when(userInfoService.verificarAssociadoVotante(associado.getCpf())).thenReturn(false);
    	
    	assertThrows(VotoException.class, () -> sessaoVotacaoService.votar(associado.getId(), idPauta, Boolean.TRUE));
    	
    }
	
	@Test
    void whenSessaoHasTimedOut_thenThrowSessaoVotacaoException() {
    	Associado associado = new Associado();
    	associado.setCpf("27863857090");
    	associado.setNome("Associado Testes da Silva");
    	associado.setId(1l);
    	
    	Long idPauta = 1l;
    	Pauta pauta = new Pauta(idPauta);
    	
    	SessaoVotacao sessaoVotacao = new SessaoVotacao();
    	sessaoVotacao.setId(1l);
    	sessaoVotacao.setPauta(pauta);
    	sessaoVotacao.setTempoMinutos("-10");
    	
    	when(associadoService.findById(associado.getId())).thenReturn(Mono.just(associado));
    	when(userInfoService.verificarAssociadoVotante(associado.getCpf())).thenReturn(true);
    	when(repository.findByPautaEquals(pauta)).thenReturn(sessaoVotacao);
    	
    	assertThrows(SessaoVotacaoException.class, () -> sessaoVotacaoService.votar(associado.getId(), idPauta, Boolean.TRUE));
    	
    }

    @Test
    void whenAssociadoJaVotou_thenThrowVotoException() {
    	Associado associado = new Associado();
    	associado.setCpf("27863857090");
    	associado.setNome("Associado Testes da Silva");
    	associado.setId(1l);
    	
    	Long idPauta = 1l;
    	Pauta pauta = new Pauta(idPauta);
    	
    	SessaoVotacao sessaoVotacao = new SessaoVotacao();
    	sessaoVotacao.setId(1l);
    	sessaoVotacao.setPauta(pauta);
    	sessaoVotacao.setTempoMinutos("10");
    	
    	when(associadoService.findById(associado.getId())).thenReturn(Mono.just(associado));
    	when(userInfoService.verificarAssociadoVotante(associado.getCpf())).thenReturn(true);
    	when(repository.findByPautaEquals(pauta)).thenReturn(sessaoVotacao);
    	when(votoService.existsByAssociadoAndSessao(associado.getId(), sessaoVotacao.getId())).thenReturn(true);
    	
    	assertThrows(VotoException.class, () -> sessaoVotacaoService.votar(associado.getId(), idPauta, Boolean.TRUE));
    	
    }
    
    @Test
    void whenAssociadoSemVoto_thenGravarVoto() throws SessaoVotacaoException, VotoException {
    	Associado associado = new Associado();
    	associado.setCpf("27863857090");
    	associado.setNome("Associado Testes da Silva");
    	associado.setId(1l);
    	
    	Long idPauta = 1l;
    	Pauta pauta = new Pauta(idPauta);
    	
    	SessaoVotacao sessaoVotacao = new SessaoVotacao();
    	sessaoVotacao.setId(1l);
    	sessaoVotacao.setPauta(pauta);
    	sessaoVotacao.setTempoMinutos("10");
    	
    	Voto novoVoto = new Voto();
    	novoVoto.setId(1l);
		novoVoto.setAssociado(associado);
		novoVoto.setSessao(sessaoVotacao);
		novoVoto.setVoto(Boolean.TRUE);
    	
    	when(associadoService.findById(associado.getId())).thenReturn(Mono.just(associado));
    	when(userInfoService.verificarAssociadoVotante(associado.getCpf())).thenReturn(true);
    	when(repository.findByPautaEquals(pauta)).thenReturn(sessaoVotacao);
    	when(votoService.existsByAssociadoAndSessao(associado.getId(), sessaoVotacao.getId())).thenReturn(false);
    	when(votoService.save(ArgumentMatchers.any(Voto.class))).thenReturn(Mono.just(novoVoto));
    	when(sessaoVotacaoService.update(sessaoVotacao)).thenReturn(Mono.just(sessaoVotacao));
    	
    	assertTrue(sessaoVotacaoService.votar(associado.getId(), idPauta, Boolean.TRUE).equals(novoVoto));
    	assertTrue(!sessaoVotacao.getVotos().isEmpty());
    }
    
}
