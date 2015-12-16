package com.paypal.android.sdk.onetouch.core.sdk;

import android.content.Context;
import android.content.Intent;

import java.util.Collection;
import java.util.HashSet;

public class V1WalletHelper {

    private static final String V1_TOUCH_SDK_ACTION =
            "com.paypal.android.lib.authenticator.activity.v1.TouchActivity";
    private static final String V1_TOUCH_SDK_COMPONENT =
            "com.paypal.android.p2pmobile/com.paypal.android.lib.authenticator.activity.v1.TouchActivity";

    private static final Collection<String> V1_AUTHENTICATOR_SCOPES = new HashSet<String>() {{
        add(PayPalScope.FUTURE_PAYMENTS.getScopeUri());
        add(PayPalScope.EMAIL.getScopeUri());
        add(PayPalScope.ADDRESS.getScopeUri());
        add(PayPalScope.PHONE.getScopeUri());
        add(PayPalScope.OPENID.getScopeUri());
    }};

    public Intent getPayPalTouchIntent() {
        return WalletAppHelper.createSdkIntent(V1_TOUCH_SDK_ACTION, V1_TOUCH_SDK_COMPONENT);
    }

    public boolean isValidV1Scope(String scope) {
        return !V1_AUTHENTICATOR_SCOPES.contains(scope);
    }

    public boolean isValidV1TouchAuthenticatorInstalled(Context context,
            boolean isAuthenticatorSecurityEnabled) {

        boolean isConfiguredToAcceptIntent = WalletAppHelper.isWalletIntentSafe(context,
                V1_TOUCH_SDK_ACTION, V1_TOUCH_SDK_COMPONENT);

        return WalletAppHelper.isValidP2pMobileAuthenticatorInstalled(context, isAuthenticatorSecurityEnabled)
                && isConfiguredToAcceptIntent;
    }
}
