package br.com.assembleia.backendapi.exception;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public class SessaoVotacaoException extends Exception {

	private static final long serialVersionUID = 1L;
	
    public String getMessage() {
        return "Está sessão de votação se encontra finalizada.";
    }

}
