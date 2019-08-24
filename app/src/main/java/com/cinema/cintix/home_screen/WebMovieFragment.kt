package com.cinema.cintix.home_screen

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website.URL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.text.parseAsHtml
import androidx.fragment.app.Fragment
import com.cinema.cintix.R
import java.net.URL


class WebMovieFragment : Fragment() {
    lateinit var web : WebView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.web_movie, container, false)
        web  = view.findViewById(R.id.webView)
        var str =  mutableListOf("SelectSeatPageRes")
        var movie_name : String = arguments!!["movie_name"].toString()
     web.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
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
        var url = "https://rav-hen.co.il/films/" + movie_name
        web.loadUrl(url)
        return view
    }
    fun fetchDataFromUrl(url:String){
    }
}