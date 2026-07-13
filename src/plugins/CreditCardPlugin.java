package plugins;
import model.*;
import java.util.UUID;

public class CreditCardPlugin implements PaymentPlugin {

    @Override
    public String getName() {
        return "CREDIT CARD";
    }

    @Override
    public boolean validate(PaymentRequest request) {
        String cardNumber = request.getDetail("cardNumber");
        return cardNumber != null && cardNumber.replace("\\s","").length() == 16;
    }

    @Override
    public PaymentResult process(PaymentRequest request) {
        if (!validate(request)) {
            return PaymentResult.failure("Invalid Card Number");
        }

        String txId = "CC-" + UUID.randomUUID().toString().substring(0,8);

        return PaymentResult.success(txId,
            "Charged " + request.getAmount() + " " + request.getCurrency() + " to card ending in " 
            + request.getDetail("cardNumber").substring(12)
        );
    }
}
