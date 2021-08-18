package br.com.assembleia.backendapi.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Alisson Nascimento
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pauta", schema = "pauta")
@AttributeOverride(name = "id", column = @Column(name = "id_pauta"))
public class Pauta extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(min = 10, max = 240)
	@Column(length = 240, nullable = false)
	private String nome;
	
	public Pauta(Long id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object other) {
		Pauta pauta = (Pauta) other;
		return this.id == pauta.id;
	}
	
}
