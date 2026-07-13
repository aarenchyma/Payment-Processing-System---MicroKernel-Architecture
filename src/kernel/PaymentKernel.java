package kernel;

import model.PaymentRequest;
import model.PaymentResult;
import plugins.PaymentPlugin;

import java.util.HashMap;
import java.util.Map;


public class PaymentKernel {

    private final Map<String, PaymentPlugin> plugins = new HashMap<>();

    public void registerPlugin(PaymentPlugin plugin) {
        plugins.put(plugin.getName(), plugin);
        System.out.println("[Kernel] Registered plugin: " + plugin.getName());
    }

    public void unregisterPlugin(String name) {
        plugins.remove(name);
        System.out.println("[Kernel] Unregistered plugin: " + name);
    }

    public PaymentResult execute(String pluginName, PaymentRequest request) {
        PaymentPlugin plugin = plugins.get(pluginName);
        if (plugin == null) {
            return PaymentResult.failure("No plugin registered for: " + pluginName);
        }
        return plugin.process(request);
    }
}