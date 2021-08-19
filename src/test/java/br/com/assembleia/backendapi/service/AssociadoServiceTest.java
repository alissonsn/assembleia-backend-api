package br.com.assembleia.backendapi.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.assembleia.backendapi.exception.CpfJaAssociadoException;
import br.com.assembleia.backendapi.model.Associado;
import br.com.assembleia.backendapi.repository.AssociadoRepository;

@ExtendWith(MockitoExtension.class)
public class AssociadoServiceTest {
	
	@Mock
	private AssociadoRepository repository;
	
	@InjectMocks
	private AssociadoService service;
	
    @Test
    void whenCpfIsNotUsed_thenReturnAssociado() {
    	Associado associado = new Associado();
    	associado.setCpf("27863857090");
    	associado.setNome("Associado Testes da Silva");
    	associado.setId(1l);
    	
    	when(repository.existsByCpf(associado.getCpf())).thenReturn(false);
    	when(service.save(associado)).thenReturn(associado);
    	
    	assertTrue(service.save(associado).equals(associado));
    }
    
    @Test
    void whenCpfIsUsed_thenThrowException() {
    	Associado associado = new Associado();
    	associado.setCpf("27863857090");
    	associado.setNome("Associado Testes da Silva");
    	associado.setId(1l);
    	
    	when(repository.existsByCpf(associado.getCpf())).thenReturn(true);
    	
    	assertThrows(CpfJaAssociadoException.class, () -> service.save(associado));
    }

}
