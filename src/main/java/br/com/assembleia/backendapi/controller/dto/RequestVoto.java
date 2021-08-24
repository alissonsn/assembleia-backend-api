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
public class RequestVoto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idAssociado; 
	private Long idPauta; 
	private Boolean voto;
	
}
