package com.seankatz.webgl.detect;

import android.content.Context;
import android.test.mock.MockContext;
import android.util.AttributeSet;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import junit.framework.TestCase;

public class WebViewClientCheckerTest extends TestCase {

    private WebViewClientChecker mTarget;
    private WebGLSupportLevel mReceiveJsResult;
    private boolean mHasFinishedJsDetection;


    public void setUp() throws Exception {
        super.setUp();

        mHasFinishedJsDetection = false;

        mTarget = new WebViewClientChecker(new OnReceiveDetectJsResult() {
            @Override
            public void onReceiveDetectJsResult(WebGLSupportLevel supportLevel) {
                mReceiveJsResult = supportLevel;
            }
        }, new OnFinishListener() {
            @Override
            public void finishedJsDetection() {
                mHasFinishedJsDetection = true;
            }
        });
    }

    public void testOnPageFinished() throws Exception {
        DummyWebView webViewMocked = new DummyWebView(new MockContext());
        mTarget.onPageFinished(webViewMocked, "dummy");

        assertTrue(mHasFinishedJsDetection);
    }

    public void testOnReceivedError() throws Exception {

    }

    class DummyWebView extends WebView{

        public DummyWebView(Context context) {
            super(context);
        }

        @Override
        public void evaluateJavascript(String script, ValueCallback<String> resultCallback) {
            return;
        }
    }
}