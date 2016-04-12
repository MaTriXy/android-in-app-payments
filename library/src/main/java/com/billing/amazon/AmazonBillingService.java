package com.billing.amazon;

import android.app.Activity;
import android.content.Context;

import com.amazon.device.iap.PurchasingService;
import com.billing.BillingService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AmazonBillingService extends BillingService {

    private List<String> iapkeys;
    private Context context;

    public AmazonBillingService(Context context, List<String> iapkeys) {
        this.context = context;
        this.iapkeys = iapkeys;
    }

    @Override
    public void init(String key) {
        AmazonBillingListener amazonBillingListener = new AmazonBillingListener(this);
        PurchasingService.registerListener(context, amazonBillingListener);

        final Set<String> productSkus = new HashSet<String>(iapkeys);
        PurchasingService.getProductData(productSkus);

        PurchasingService.getPurchaseUpdates(false);
    }

    @Override
    public void buy(Activity activity, String sku, int id) {
        PurchasingService.purchase(sku);
    }

    @Override
    public void close() {
    }
}