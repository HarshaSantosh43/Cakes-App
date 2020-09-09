package com.cavista.leaveragesapp.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnectionDetector {

    private static InternetConnectionDetector internetConnectionDetector;

    private InternetConnectionDetector() {

    }

    public static InternetConnectionDetector getInstance() {
        if (internetConnectionDetector == null) {
            internetConnectionDetector = new InternetConnectionDetector();
        }
        return internetConnectionDetector;
    }

    /*Check for internet connectivity*/
    public boolean isConnected(Context mContext) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                    connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
        }
        return connected;
    }
}
