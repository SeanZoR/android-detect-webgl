package com.seankatz.webgl.detect;

/**
 * This enum holds the different possible support levels of WebGL
 */
public enum WebGLSupportLevel {
    UNKNOWN(-2),
    NOT_SUPPORTED(-1),
    SUPPORTED_BUT_DISABLED(0),
    SUPPORTED(1);

    private int mStatusCode;

    WebGLSupportLevel(int statusCode) {
        mStatusCode = statusCode;
    }

    public static WebGLSupportLevel findByCode(int code) {
        for (WebGLSupportLevel currEnumValue : values()) {
            if (currEnumValue.mStatusCode == code) {
                return currEnumValue;
            }
        }
        return null;
    }
}
