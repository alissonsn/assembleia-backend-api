package br.com.assembleia.backendapi.controller.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVoto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome; 
	private String pauta; 
	private Boolean voto;
	
}
