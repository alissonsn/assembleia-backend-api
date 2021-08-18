package br.com.assembleia.backendapi.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author Alisson Nascimento
 *
 */
@Getter
@AllArgsConstructor
public class ErrorObject {

	private final String message;
	private final String field;
	private final Object parameter;

}
