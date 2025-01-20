package cl.tenpo.tenpista.domain.error;

public class TransactionDeletionException extends RuntimeException {
    public TransactionDeletionException(String message) {
        super(message);
    }
}

