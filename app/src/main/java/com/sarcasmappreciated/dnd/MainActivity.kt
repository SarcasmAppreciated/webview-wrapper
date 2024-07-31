package com.sarcasmappreciated.dnd

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.URLUtil
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sarcasmappreciated.dnd.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private val tag = "SCRYFALL"
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webview.webViewClient = CookieWebViewClient()
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.setBackgroundColor(Color.TRANSPARENT)
        binding.webview.loadUrl(resources.getString(R.string.url))

        webView = findViewById(R.id.webview)
        val swipeLayout: SwipeRefreshLayout = this.findViewById(R.id.swipe)
        swipeLayout.setOnRefreshListener {
            webView.reload()
            swipeLayout.isRefreshing = false
        }

        webView.setOnLongClickListener {
            copyToClipboard()
            return@setOnLongClickListener true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun copyToClipboard() {
        webView.hitTestResult.let{
            val url = it.extra
            if (URLUtil.isValidUrl(url)) {
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText(tag, url)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(this, "Copied $url", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "There was a problem.", Toast.LENGTH_LONG).show()
            }
        }
    }
}