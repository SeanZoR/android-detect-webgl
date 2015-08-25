package com.github.seanzor.webgl.detect;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.webkit.WebView;

public class WebGLDetector {

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
                    if (webView != null) {
                        webView.clearHistory();
                        webView.clearCache(true);
                        webView.loadUrl("about:blank");
                        webView.pauseTimers();
                        webView.setWebViewClient(null);
                    }
                }
            }));
        }
    }

    public static final String BLANK_HTML_PAGE = "<!DOCTYPE html><html><head></head></html>";
}