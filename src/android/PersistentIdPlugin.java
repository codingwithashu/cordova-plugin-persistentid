package com.example.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import android.provider.Settings;

public class PersistentIdPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getAndroidId")) {
            this.getAndroidId(callbackContext);
            return true;
        }
        return false;
    }

    private void getAndroidId(CallbackContext callbackContext) {
        try {
            String androidId = Settings.Secure.getString(
                    this.cordova.getActivity().getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            callbackContext.success(androidId);
        } catch (Exception e) {
            callbackContext.error("Error retrieving Android ID: " + e.getMessage());
        }
    }
}