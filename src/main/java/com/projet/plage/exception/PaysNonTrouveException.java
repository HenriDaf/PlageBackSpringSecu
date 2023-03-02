package com.projet.plage.exception;

public class PaysNonTrouveException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaysNonTrouveException() {
		super();

	}

	public PaysNonTrouveException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public PaysNonTrouveException(String message, Throwable cause) {
		super(message, cause);

	}

	public PaysNonTrouveException(String message) {
		super(message);

	}

	public PaysNonTrouveException(Throwable cause) {
		super(cause);

	}

}
