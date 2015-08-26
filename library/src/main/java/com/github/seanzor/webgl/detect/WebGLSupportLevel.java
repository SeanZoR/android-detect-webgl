package com.github.seanzor.webgl.detect;

/**
 * This enum holds the different possible support levels of WebGL, as detected by the library
 * The different values can be:
 * <ul>
 *     <li>{@link WebGLSupportLevel#UNKNOWN UNKNOWN} could not determine WebGL support state, due to limitations or errors that were raised in the process
 *     <li>{@link WebGLSupportLevel#NOT_SUPPORTED NOT_SUPPORTED} WebGL is not supported at all on this device
 *     <li>{@link WebGLSupportLevel#SUPPORTED_BUT_DISABLED SUPPORTED_BUT_DISABLED} WebGL is supported but disabled, meaning you can't use it
 *     <li>{@link WebGLSupportLevel#SUPPORTED SUPPORTED} WebGL is supported, go ahead.
 * </ul>
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
