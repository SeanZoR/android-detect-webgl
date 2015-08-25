package com.github.seanzor.webgl.detect;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewClientChecker extends WebViewClient{
    private OnReceiveDetectJsResult mDetectJsResult;
    private final OnFinishListener mOnFinishListener;
    public boolean noErrorRaised = true;
    private WebView mWebView;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public WebViewClientChecker(OnReceiveDetectJsResult detectJsResult,
                                OnFinishListener onFinishListener) {
        mDetectJsResult = detectJsResult;
        mOnFinishListener = onFinishListener;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        // Save the view for later, we will want to destroy it once we're finished
        mWebView = view;

        // If the page is ready
        if (noErrorRaised) {
            // Fire the js function
            view.evaluateJavascript(JS_CHECKER, new DetectJsCallback(mDetectJsResult,
                    mOnFinishListener));
        } else {
            // Else, this is a dead end, report Unknown status
            mDetectJsResult.onReceiveDetectJsResult(WebGLSupportLevel.UNKNOWN);
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
        noErrorRaised = false;
    }

    /**
     * Detects if WebGL is enabled.
     * Inspired from http://www.browserleaks.com/webgl#howto-detect-webgl
     * Source code avilable here: https://gist.github.com/SeanZoR/cfa7a6206983b775a858
     * <p/>
     * This code is in here, and not in Android Assets folder as expected because
     * libraries can't be wrapped with the assets within.
     * <p/>
     * returns a { number } -1 for not SUPPORTED,
     * 0 for disabled
     * 1 for enabled
     */
    String JS_CHECKER = "function detectWebGL()\n" +
            "{\n" +
            "    // Check for the WebGL rendering context\n" +
            "    if ( !! window.WebGLRenderingContext) {\n" +
            "        var canvas = document.createElement(\"canvas\"),\n" +
            "            names = [\"webgl\", \"experimental-webgl\", \"moz-webgl\", \"webkit-3d\"],\n" +
            "            context = false;\n" +
            "\n" +
            "        for (var i in names) {\n" +
            "            try {\n" +
            "                context = canvas.getContext(names[i]);\n" +
            "                if (context && typeof context.getParameter === \"function\") {\n" +
            "                    // WebGL is enabled.\n" +
            "                    return 1;\n" +
            "                }\n" +
            "            } catch (e) {}\n" +
            "        }\n" +
            "\n" +
            "        // WebGL is supported, but disabled.\n" +
            "        return 0;\n" +
            "    }\n" +
            "\n" +
            "    // WebGL not supported.\n" +
            "    return -1;\n" +
            "};" +
            "detectWebGL();";

}
