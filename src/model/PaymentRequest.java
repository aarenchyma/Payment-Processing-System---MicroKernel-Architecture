package model;
import java.util.*;

public class PaymentRequest {
    private final double amount;
    private final String currency;
    private final Map<String, String> details = new HashMap<>();

    public PaymentRequest(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public PaymentRequest withDetail(String key, String value) {
        details.put(key, value);
        return this;
    }

    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }

    public String getDetail(String key) {
        return details.get(key);
    }
}
