package br.com.assembleia.backendapi.model;

/**
 * 
 * @author Alisson Nascimento
 *
 */
public enum StatusEnum {

	ABLE_TO_VOTE("ABLE_TO_VOTE"),
	UNABLE_TO_VOTE("UNABLE_TO_VOTE");
	
	
	StatusEnum(String status) {
		this.status = status;
	}
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
