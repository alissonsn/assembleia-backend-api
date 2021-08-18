package br.com.assembleia.backendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.assembleia.backendapi.model.Associado;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public interface AssociadoRepository extends JpaRepository<Associado, Long>{

	boolean existsByCpf(String cpf);
	
}
