package eu.webcitron.services.proxy;

public class NoApiAvailableException extends RuntimeException {

    private final int retryAfter;
    private static final int DEFAULT_RETRY_AFTER = 5;

    public NoApiAvailableException(String message) {
        this(message, DEFAULT_RETRY_AFTER);
    }

    public NoApiAvailableException(String message, int retryAfter) {
        super(message);
        this.retryAfter = retryAfter;
    }

    public int getRetryAfter() {
        return retryAfter;
    }
}
