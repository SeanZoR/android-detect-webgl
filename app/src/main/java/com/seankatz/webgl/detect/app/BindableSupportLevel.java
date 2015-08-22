package com.seankatz.webgl.detect.app;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.seankatz.webgl.detect.WebGLSupportLevel;

public class BindableSupportLevel extends BaseObservable {

    private WebGLSupportLevel supportLevel;

    @Bindable
    public String getSupportLevel() {
        if (supportLevel == null) {
            return "Checking...";
        } else {
            switch (supportLevel) {
                case NOT_SUPPORTED:
                    return "Not supported :(";
                case SUPPORTED_BUT_DISABLED:
                    return "Supported, but disabled :(";
                case SUPPORTED:
                    return "Supported! :)";
                default:
                case UNKNOWN:
                    return "Unknown";
            }
        }
    }

    public void setSupportLevel(WebGLSupportLevel receivedSupportLevel){
        this.supportLevel = receivedSupportLevel;
        notifyPropertyChanged(com.seankatz.webgl.detect.app.BR.supportLevel);
    }
}