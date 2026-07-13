package plugins;

import model.*;

public interface PaymentPlugin {
    /**
     * Unique identifier used to
     * register/look up this plugin in the kernel, e.g. "CREDIT_CARD".
     */
    String getName();

    /**
     * Validate whatever the plugin needs
     * before processing (card number, wallet address, etc.).
     */
    boolean validate(PaymentRequest request);

    /** Execute the payment. Returns a result describing success/failure. */
    PaymentResult process(PaymentRequest request);
}
