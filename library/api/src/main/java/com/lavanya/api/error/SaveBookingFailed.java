package com.lavanya.api.error;

/**
 * Custom Exceptions to throw when a new pre-booking process failed.
 * Exception required when saving a new pre-booking failed to be recorded in database.
 * @author lavanya
 */
public class SaveBookingFailed extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public SaveBookingFailed() {
        super();
    }

    public SaveBookingFailed(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SaveBookingFailed(final String message) {
        super(message);
    }

    public SaveBookingFailed(final Throwable cause) {
        super(cause);
    }
}
