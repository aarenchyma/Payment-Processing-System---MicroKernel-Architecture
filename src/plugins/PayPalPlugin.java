package plugins;
import model.*;
import java.util.UUID;

public class PayPalPlugin implements PaymentPlugin {

    @Override
    public String getName() {
        return "PAYPAL";
    }

    @Override
    public boolean validate(PaymentRequest request) { 
        String email = request.getDetail("emailAddress");
        return email != null && email.endsWith("@gmail.com");
    }

    @Override
    public PaymentResult process(PaymentRequest request) {
        if (!validate(request)) {
            return PaymentResult.failure("Invalid email address");
        }

        String txId = "PP-" + UUID.randomUUID().toString().substring(0,8);

        return PaymentResult.success(txId,
            "Charged " + request.getAmount() + " " + request.getCurrency() + " to email ending in " 
            + request.getDetail("emailAddress")
        );
    }   
}
