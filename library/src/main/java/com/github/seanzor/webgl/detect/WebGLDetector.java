package com.github.seanzor.webgl.detect;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.webkit.WebView;

/***
 * Wraps the WebGL detection process. To start the detection call
 * {@link WebGLDetector#detect(Context, OnReceiveDetectJsResult)}
 */
public class WebGLDetector {

    /***
     * Use this method to start the detection of WebGL on the running device.
     * Behind the scenes this methods inflates a WebView in order to run in a web context,
     * it will then run the needed JavaScript methods to evaluate the WebGL State
     * <p/>
     * <p>The result will be received in an Async manner
     *
     * @param activityContext The context of the {@link android.app.Activity},
     *                        will be used to inflate a WebView
     * @param detectResult    The result of the detection
     */
    public static void detect(@NonNull Context activityContext,
                              @NonNull OnReceiveDetectJsResult detectResult) {

        // If this is an older API than Lollipop, it means there's no official
        // support for WebGL.
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            detectResult.onReceiveDetectJsResult(WebGLSupportLevel.NOT_SUPPORTED);
        }
        // Else, this is ran by Lollipop (21) and above
        else {
            final WebView webView = new WebView(activityContext);

            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadData(BLANK_HTML_PAGE, "text/html; charset=UTF-8", null);

            // This web view client is going to fire the JS detect as soon as the web finish loading
            webView.setWebViewClient(new WebViewClientChecker(detectResult, new OnFinishListener() {
                @Override
                public void finishedJsDetection() {
                    // Kill the webview
                    webView.clearHistory();
                    webView.clearCache(true);
                    webView.loadUrl("about:blank");
                    webView.pauseTimers();
                    webView.setWebViewClient(null);
                }
            }));
        }
    }

    static final private String BLANK_HTML_PAGE = "<!DOCTYPE html><html><head></head></html>";
}