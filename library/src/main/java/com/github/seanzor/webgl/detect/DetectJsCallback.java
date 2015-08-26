package com.github.seanzor.webgl.detect;

import android.webkit.ValueCallback;

class DetectJsCallback implements ValueCallback<String>{

    private final OnReceiveDetectJsResult mResult;
    private final OnFinishListener mFinishListener;

    DetectJsCallback(OnReceiveDetectJsResult result, OnFinishListener finishListener) {
        mResult = result;
        mFinishListener = finishListener;
    }

    @Override
    public void onReceiveValue(String valueOfSupportCheck) {

        // The default value of the function is unknown
        // It can happen if we fail to get some legit response
        WebGLSupportLevel result = WebGLSupportLevel.UNKNOWN;

        if (valueOfSupportCheck != null) {
            switch (valueOfSupportCheck) {
                case "1":
                    result = WebGLSupportLevel.SUPPORTED;
                    break;
                case "0":
                    result = WebGLSupportLevel.SUPPORTED_DISABLED;
                    break;
                case "-1":
                    result = WebGLSupportLevel.NOT_SUPPORTED;
                    break;
            }
        }

        mResult.onReceiveDetectJsResult(result);
        mFinishListener.finishedJsDetection();
    }
}
