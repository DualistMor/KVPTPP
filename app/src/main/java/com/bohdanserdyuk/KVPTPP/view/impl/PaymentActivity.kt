package com.bohdanserdyuk.KVPTPP.view.impl

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.view.BaseActivity
import com.bohdanserdyuk.KVPTPP.view.heplers.PermissionChecker
import kotlinx.android.synthetic.main.payment_view.*
import android.webkit.WebChromeClient


class PaymentActivity : BaseActivity<BaseContract.PaymentView, BaseContract.PaymentPresenter>(), BaseContract.PaymentView {
    var PERMISSIONS = arrayOf(Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE)
    var PERMISSION_ALL = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_view)

        web_view.settings.javaScriptEnabled = true
        web_view.settings.domStorageEnabled = true
        web_view.settings.loadWithOverviewMode = true
        web_view.settings.useWideViewPort = true
        web_view.settings.builtInZoomControls = true
        web_view.settings.displayZoomControls = false
        web_view.settings.setSupportZoom(true)
        web_view.settings.defaultTextEncodingName = "utf-8"
        cover.visibility = VISIBLE
        web_view.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                Handler().postDelayed({
                    presenter.pageFinished(getString(R.string.users_pattern), getString(R.string.js_insert_pattern))

                    super.onPageFinished(view, url)
                }, 1000)
            }
        }
    }

    override fun setDisplayHomeAsUpEnabled(b: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(b)
    }

    override fun setDisplayShowHomeEnabled(b: Boolean) {
        supportActionBar?.setDisplayShowHomeEnabled(b)
    }

    override fun hideProgressBar() {
        cover.visibility = GONE
    }

    override fun loadPage(url: String) {
        loadPageWithPermissions(url)
    }

    private fun loadPageWithPermissions(url: String) {
        if(PermissionChecker().hasPermissions(this, PERMISSIONS[0], PERMISSIONS[1])) {
            web_view.loadUrl(url)
        }
        else {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL)
        }
    }

    override fun loadPage(urlResID: Int) {
        loadPageWithPermissions(getString(urlResID))
    }

    override fun onSupportNavigateUp(): Boolean {
        presenter.onBackButtonPressed()
        return true
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun initPresenter(): BaseContract.PaymentPresenter {
        val sharedPreferences = dependencyInjector.sharedPreferences(this)
        return dependencyInjector.paymentPresenter(PreferencesModel(this, sharedPreferences, sharedPreferences.edit()))
    }
}
