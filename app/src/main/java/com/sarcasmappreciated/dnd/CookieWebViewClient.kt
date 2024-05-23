package com.sarcasmappreciated.dnd

import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient

class CookieWebViewClient : WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().acceptCookie()
        CookieManager.getInstance().flush()
    }
}