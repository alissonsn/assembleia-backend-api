package br.com.assembleia.backendapi.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Alisson Nascimento
 *
 */
@Getter
@Setter
@Entity
@Table(name = "voto", schema = "pauta")
@AttributeOverride(name = "id", column = @Column(name = "id_voto"))
public class Voto extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	private Boolean voto;
	
	@OneToOne
	@JoinColumn(name = "id_associado")
	private Associado associado;
	
	@OneToOne
	@JoinColumn(name = "id_sessao_votacao")
	private SessaoVotacao sessao;

}
