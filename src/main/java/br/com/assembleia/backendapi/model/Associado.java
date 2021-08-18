package br.com.assembleia.backendapi.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
@Entity
@Table(name = "associado", schema = "associado")
@AttributeOverride(name = "id", column = @Column(name = "id_associado"))
public class Associado extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(min = 3, max = 120)
	@Column(length = 120, nullable = false)
	private String nome;
	
	@Size(min = 11)
	@Column(length = 11, nullable = false)
	private String cpf;
	
	public Associado(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object other) {
		Associado associado = (Associado) other;
		return this.id == associado.id;
	}

}
