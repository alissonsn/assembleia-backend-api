package br.com.assembleia.backendapi.exception;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public class VotoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public VotoException(String message) {
		this.message = message;
	}
	
    public String getMessage() {
        return message;
    }

}
