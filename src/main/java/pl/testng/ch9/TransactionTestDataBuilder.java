package pl.testng.ch9;

/**
 * Test data builder
 */
public class TransactionTestDataBuilder {

    private long id;
    private String state;
    private boolean retryAllowed;
    private String message;

    TransactionTestDataBuilder setId(long id) {
        this.id = id;
        return  this;
    }
    TransactionTestDataBuilder setState(String state) {
        this.state = state;
        return this;
    }
    TransactionTestDataBuilder setRellyAllowed(boolean allowed) {
        this.retryAllowed = allowed;
        return this;
    }
    TransactionTestDataBuilder seMessage(String message) {
        this.message = message;
        return this;
    }

    public Transaction build() {
        Transaction transaction = new Transaction();

        transaction.setId(id);
        transaction.setState(state);
        transaction.setRetryAllowed(retryAllowed);
        transaction.setMessage(message);

        return transaction;
    }
}
