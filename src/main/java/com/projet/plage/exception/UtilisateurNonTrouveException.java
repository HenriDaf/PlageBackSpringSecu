package com.projet.plage.exception;

public class UtilisateurNonTrouveException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UtilisateurNonTrouveException() {
		super();		
	}

	public UtilisateurNonTrouveException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);		
	}

	public UtilisateurNonTrouveException(String message, Throwable cause) {
		super(message, cause);		
	}

	public UtilisateurNonTrouveException(String message) {
		super(message);		
	}

	public UtilisateurNonTrouveException(Throwable cause) {
		super(cause);		
	}	

}
