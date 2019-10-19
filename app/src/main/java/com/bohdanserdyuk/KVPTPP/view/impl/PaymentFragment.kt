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
import com.bohdanserdyuk.KVPTPP.view.helpers.PermissionChecker
import kotlinx.android.synthetic.main.web_view.view.*


class PaymentFragment : BaseFragment<BaseContract.PaymentView, BaseContract.PaymentPresenter>(), BaseContract.PaymentView {
    var PERMISSIONS = arrayOf(Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE)
    var PERMISSION_ALL = 1

    lateinit var v: View

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity!!.application as KVPTPPAplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        v = inflater.inflate(R.layout.web_view, container, false)
        v.web_view.settings.javaScriptEnabled = true
        v.web_view.settings.domStorageEnabled = true
        v.web_view.settings.loadWithOverviewMode = true
        v.web_view.settings.useWideViewPort = true
        v.web_view.settings.builtInZoomControls = true
        v.web_view.settings.displayZoomControls = false
        v.web_view.settings.setSupportZoom(true)
        v.web_view.settings.defaultTextEncodingName = "utf-8"
        v.cover.visibility = VISIBLE
        v.web_view.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                Handler().postDelayed({
                    if (presenter.isViewAttached) {
                        presenter.pageFinished(getString(R.string.js_ammount_pattern),
                            "%s", getString(R.string.js_insert_pattern),
                            getString(R.string.users_pattern)
                        )
                        super.onPageFinished(view, url)
                    }

                }, 1000)
            }
        }
        presenter.onCreateView()
        return v
    }

    override fun hideProgressBar() {
        v.cover.visibility = GONE
    }

    override fun loadPage(url: String) {
        loadPageWithPermissions(url)
    }

    private fun loadPageWithPermissions(url: String) {
        if (PermissionChecker().hasPermissions(activity, PERMISSIONS[0], PERMISSIONS[1])) {
            v.web_view.loadUrl(url)
        } else {
//            ActivityCompat.requestPermissions(activity, PERMISSIONS, PERMISSION_ALL)
        }
    }

    override fun loadPage(urlResID: Int) {
        loadPageWithPermissions(resources.getString(urlResID))
    }
}
