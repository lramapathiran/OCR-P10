package com.lavanya.api.error;

public class ExtendDueDateFailed extends RuntimeException{
    private static final long serialVersionUID = 5861310537366287163L;

    public ExtendDueDateFailed() {
        super();
    }

    public ExtendDueDateFailed(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ExtendDueDateFailed(final String message) {
        super(message);
    }

    public ExtendDueDateFailed(final Throwable cause) {
        super(cause);
    }
}
