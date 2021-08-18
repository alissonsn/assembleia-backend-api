package br.com.assembleia.backendapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.assembleia.backendapi.model.Pauta;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
