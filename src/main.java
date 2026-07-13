import model.*;
import plugins.*;
import kernel.PaymentKernel;
public class main {
    public static void main(String [] args) {
        PaymentKernel kernel= new PaymentKernel();

        // register plugins
        kernel.registerPlugin(new CreditCardPlugin());
        kernel.registerPlugin(new CryptoPlugin());
        kernel.registerPlugin(new PayPalPlugin());

        System.out.println();

        PaymentRequest ccRequest = new PaymentRequest(150.0, "USD")
            .withDetail("cardNumber", "4111111111111111");
        PaymentResult ccResult = kernel.execute("CREDIT_CARD", ccRequest);
        System.out.println("Credit card payment successful: " + ccResult);

        PaymentRequest ppRequest = new PaymentRequest(75.50, "USD")
            .withDetail("emailAddress", "victor@gmail.com");
        PaymentResult ppResult = kernel.execute("PAYPAL", ppRequest);
        System.out.println("Paypal payment successful: "+ ppResult);

        PaymentRequest cryptoRequest = new PaymentRequest(0.005, "BTC")
            .withDetail("walletAddress", "1BvBMSEYstWetqTFn5Au4m4GFg7xJaNVN2");
        PaymentResult cryptoResult = kernel.execute("CRYPTO", cryptoRequest);
        System.out.println("crypto payment successful" + cryptoResult);

        // Unknown plugin — kernel handles gracefully, no crash
        PaymentResult unknownResult = kernel.execute("BANK_TRANSFER", ccRequest);
        System.out.println(unknownResult);

    }
    
}
