package com.lavanya.api.error;

/**
 * Custom Exceptions to throw when a new lending process failed.
 * Exception required when saving a new lending failed to be recorded in database. 
 * @author lavanya
 */
public class SaveLendingFailed extends RuntimeException{

	private static final long serialVersionUID = 5861310537366287163L;

    public SaveLendingFailed() {
        super();
    }

    public SaveLendingFailed(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SaveLendingFailed(final String message) {
        super(message);
    }

    public SaveLendingFailed(final Throwable cause) {
        super(cause);
    }
}
