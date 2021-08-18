package br.com.assembleia.backendapi.exception;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public class CpfJaAssociadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public CpfJaAssociadoException(String message) {
		this.message = message;
	}
	
    public String getMessage() {
        return message;
    }

}
