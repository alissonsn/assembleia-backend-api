package br.com.assembleia.backendapi.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
	private List<Voto> votos = new ArrayList<Voto>();
	
	public SessaoVotacao() {
		tempoHoras = "";
		tempoMinutos = String.valueOf(DEFAULT_SESSION_TIME);
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
	public Boolean hasTimedOut() {
		LocalDateTime dataAtual = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime dataCriacao = LocalDateTime.parse(getCreationDate().toString(), formatter);
		LocalDateTime dataEncerramento = dataCriacao;

		// Verifica se há horas informada para encerramento da sessão
		if(!getTempoHoras().isBlank() || getTempoHoras() != null) {			
			dataEncerramento = dataCriacao.plusHours(Long.parseLong(getTempoHoras()));
		}

		// Verifica se há minutos informada para encerramento da sessão
		if(!getTempoMinutos().isBlank() || getTempoMinutos() != null) {			
			dataEncerramento = dataCriacao.plusMinutes(Long.parseLong(getTempoMinutos()));
		}

		return dataAtual.isBefore(dataEncerramento);
	}

}
