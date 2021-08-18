package br.com.assembleia.backendapi.handler;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Alisson Nascimento
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

	private final String message;
	private final int code;
	private final String status;
	private final String objectName;
	private final String user;
	private final List<ErrorObject> errors;

}
