package com.sarcasmappreciated.scryfall

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import com.sarcasmappreciated.scryfall.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webview.webViewClient = CookieWebViewClient()
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.setBackgroundColor(Color.TRANSPARENT)
        binding.webview.loadUrl(resources.getString(R.string.url))
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val wV: WebView = findViewById(R.id.webview)
        if (keyCode == KeyEvent.KEYCODE_BACK && wV.canGoBack()) {
            wV.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}