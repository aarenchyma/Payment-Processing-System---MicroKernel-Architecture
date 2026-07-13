package plugins;
import model.PaymentRequest;
import model.PaymentResult;

import java.util.UUID;

public class CryptoPlugin implements PaymentPlugin {

    @Override
    public String getName() {
        return "CRYPTO";
    }

    @Override
    public boolean validate(PaymentRequest request) {
        String wallet = request.getDetail("walletAddress");
        return wallet != null && wallet.length() >= 26;
    }

    @Override
    public PaymentResult process(PaymentRequest request) {
        if (!validate(request)) {
            return PaymentResult.failure("Invalid Crypto wallet address");
        }

        String txId = "CC-" + UUID.randomUUID().toString().substring(0,8);

        return PaymentResult.success(txId,
            "Charged " + request.getAmount() + " " + request.getCurrency() + " to wallet ending in " 
            + request.getDetail("walletAddress")
        );
    }
}
