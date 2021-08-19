package br.com.assembleia.backendapi.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "sessao_votacao", schema = "pauta")
@AttributeOverride(name = "id", column = @Column(name = "id_sessao_votacao"))
public class SessaoVotacao extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	private static final int DEFAULT_SESSION_TIME = 1;

	private String tempoHoras;

	private String tempoMinutos;

	private Boolean ativa = true;

	@OneToOne
	@JoinColumn(name = "id_pauta")
	private Pauta pauta;

	@OneToMany
	@JoinColumn(name = "id_sessao_votacao")
	private List<Voto> votos;
	
	public SessaoVotacao() {
		tempoHoras = "";
		tempoMinutos = String.valueOf(DEFAULT_SESSION_TIME);
		votos = new ArrayList<Voto>();
	}
	
	public SessaoVotacao(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object other) {
		SessaoVotacao sessao = (SessaoVotacao) other;
		return this.id == sessao.id;
	}

	/**
	 * Contabiliza o total de votos registrados na Pauta 
	 * @return String
	 */
	public String getVotosTotal() {
		long favoravel = votos.stream().filter(v -> Boolean.TRUE.equals(v.getVoto())).count();
		long desfavoravel = votos.stream().filter(v -> !Boolean.TRUE.equals(v.getVoto())).count();

		return "A pauta teve um total de "+ favoravel +" votos favoráveis e " + desfavoravel +" votos desfavoráveis.";
	}

	public void setTempoMinutos(String tempoMinutos) {
		if(tempoMinutos.equals("") || tempoMinutos == null || tempoMinutos.equals("0")) {
			this.tempoMinutos = "1";
		} else {			
			this.tempoMinutos = tempoMinutos;
		}
	}
	
	/**
	 * 
	 * Verifica se a sessão de votação está ativa
	 * 
	 */
	public Boolean hasNotTimedOut() {
		LocalDateTime inicioSessao = creationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();;
		LocalDateTime fimSessao = inicioSessao;

		Optional<String> optTempoHoras = Optional.ofNullable(tempoHoras);
		if(optTempoHoras.isPresent() && !optTempoHoras.get().isBlank()) {			
			fimSessao = inicioSessao.plusHours(Long.parseLong(optTempoHoras.get()));
		}

		Optional<String> optTempoMinutos = Optional.ofNullable(tempoMinutos);
		if(optTempoMinutos.isPresent() && !optTempoMinutos.get().isBlank()) {					
			fimSessao = inicioSessao.plusMinutes(Long.parseLong(optTempoMinutos.get()));
		}

		return LocalDateTime.now().isBefore(fimSessao);
	}

}
