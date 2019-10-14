package com.bohdanserdyuk.KVPTPP.view.impl

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bohdanserdyuk.KVPTPP.KVPTPPAplication
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.view.BaseFragment
import com.bohdanserdyuk.KVPTPP.view.heplers.PermissionChecker
import kotlinx.android.synthetic.main.web_view.*
import kotlinx.android.synthetic.main.web_view.view.*


class PaymentFragment : BaseFragment<BaseContract.PaymentView, BaseContract.PaymentPresenter>(), BaseContract.PaymentView {
    var PERMISSIONS = arrayOf(Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE)
    var PERMISSION_ALL = 1
    lateinit var webview: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity!!.application as KVPTPPAplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.web_view,container, false)
        webview = v.web_view
        webview.settings.javaScriptEnabled = true
        webview.settings.domStorageEnabled = true
        webview.settings.loadWithOverviewMode = true
        webview.settings.useWideViewPort = true
        webview.settings.builtInZoomControls = true
        webview.settings.displayZoomControls = false
        webview.settings.setSupportZoom(true)
        webview.settings.defaultTextEncodingName = "utf-8"
        v.cover.visibility = VISIBLE
        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                Handler().postDelayed({
                    if(presenter.isViewAttached) {
                        presenter.pageFinished(getString(R.string.users_pattern), getString(R.string.js_insert_pattern))
                        super.onPageFinished(view, url)
                    }

                }, 1000)
            }
        }
        presenter.onCreateView()
        return v
    }

    override fun hideProgressBar() {
        cover.visibility = GONE
    }

    override fun loadPage(url: String) {
        loadPageWithPermissions(url)
    }

    private fun loadPageWithPermissions(url: String) {
        if(PermissionChecker().hasPermissions(activity, PERMISSIONS[0], PERMISSIONS[1])) {
            webview.loadUrl(url)
        }
        else {
//            ActivityCompat.requestPermissions(activity, PERMISSIONS, PERMISSION_ALL)
        }
    }

    override fun loadPage(urlResID: Int) {
        loadPageWithPermissions(resources.getString(urlResID))
    }
}
