package com.example.emergencysystem

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity


@Composable
fun MainContent() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("GFG | WebView", color = Color.White) }, backgroundColor = Color(0xff0f9d58)) },
        content = { MyContent() }
    )
}

// Creating a composable
// function to create WebView
// Calling this function as
// content in the above function
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MyContent(modifier: Modifier = Modifier){

    // Declare a string that contains a url
    val mUrl = "https://www.google.com/maps/search/doctors/@-1.3541635,36.6603235,12z"

    var backEnabled by remember { mutableStateOf(false) }
    var webView: WebView? = null
    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                        backEnabled = view.canGoBack()
                    }
                }
                settings.javaScriptEnabled = true

                loadUrl(mUrl)
                webView = this

                webView!!.webViewClient = object : WebViewClient() {
                    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                        backEnabled = view.canGoBack()


                        if(url!!.startsWith("tel:")){
                            webView!!.loadUrl(mUrl)
                            val _intent = Intent(Intent.ACTION_DIAL)
                            _intent.data = Uri.parse(url)
                            getContext().startActivity(_intent)

                        }
//                        webView?.loadUrl(url.toString())


                    }
                }
            }
        }, update = {
            webView = it
        })
//    webView.webViewClient = object : WebViewClient() {
//        @Deprecated("Deprecated in Java")
//        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//            Log.d("TAGGGGGGGGG", "shouldOverrideUrlLoading: "+ url)
//            if(url != null) {
//                if (url.startsWith("tel:")) {
//                    view?.loadUrl(url.toString())
//                    Log.d("TAGGGGGGGGG", "shouldOverrideUrlLoading: "+ url)
//                }
//            }
//            return true
//        }
//    }

    BackHandler(enabled = backEnabled) {
        webView?.goBack()
    }
}

// For displaying preview in
// the Android Studio IDE emulator
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainContent()
}