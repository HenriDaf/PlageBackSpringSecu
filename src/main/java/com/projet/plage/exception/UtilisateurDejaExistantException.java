package com.projet.plage.exception;

public class UtilisateurDejaExistantException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UtilisateurDejaExistantException() {
		super();

	}

	public UtilisateurDejaExistantException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public UtilisateurDejaExistantException(String message, Throwable cause) {
		super(message, cause);

	}

	public UtilisateurDejaExistantException(String message) {
		super(message);

	}

	public UtilisateurDejaExistantException(Throwable cause) {
		super(cause);

	}
	
	
	

}
