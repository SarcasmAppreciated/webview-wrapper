package com.sarcasmappreciated.moxfield

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sarcasmappreciated.moxfield.databinding.ActivityMainBinding
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

        val wV: WebView = findViewById(R.id.webview)
        val swipeLayout: SwipeRefreshLayout = this.findViewById(R.id.swipe);
        swipeLayout.setOnRefreshListener {
            wV.reload();
            swipeLayout.isRefreshing = false
        }
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