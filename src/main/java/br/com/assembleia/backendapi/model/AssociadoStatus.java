package br.com.assembleia.backendapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Alisson Nascimento
 *
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssociadoStatus {

	private String status;
	
}
