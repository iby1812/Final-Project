package com.cinema.cintix.home_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.cinema.cintix.R
import kotlinx.android.synthetic.main.web_movie.*
import kotlinx.android.synthetic.main.web_movie.view.*

class WebMovieFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.web_movie, container, false)
        var web : WebView = view.findViewById(R.id.webView)
        var str =  mutableListOf("SelectSeatPageRes")
     web.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                Log.d("urllll",url)
                if(url?.findAnyOf(str ,0)!=null){
                    fetchDataFromUrl(url)
                }
                return true
            }
        }
        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        web.settings.setSupportMultipleWindows(true)
        web.settings.loadWithOverviewMode = true
        web.settings.allowContentAccess = true
        web.settings.setGeolocationEnabled(true)
        var url = "https://rav-hen.co.il/films/the-lion-king"
        web.loadUrl(url)
        return view
    }
    fun fetchDataFromUrl(url:String){
        //TODO fetch data
    }
}