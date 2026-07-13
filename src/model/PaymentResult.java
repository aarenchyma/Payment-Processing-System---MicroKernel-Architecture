package model;

import java.time.LocalDateTime;

public class PaymentResult {
    private final boolean success;
    private final String message;
    private final String transactionId;
    private final LocalDateTime createdAt; 

    private PaymentResult (boolean success, String message, String transactionId) {
        this.success = success;
        this.message = message;
        this.transactionId = transactionId;
        this.createdAt = LocalDateTime.now();
    }

    public static PaymentResult success(String transactionId, String message) {
        return new PaymentResult(true, message, transactionId);
    }

    public static PaymentResult failure(String message) {
        return new PaymentResult(false, message, null);
    }

    public boolean isSuccess() { return success; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return success 
        ? "SUCCESS [txId=" + transactionId + "] " + message 
        : "FAILED: " + message;
    }
}