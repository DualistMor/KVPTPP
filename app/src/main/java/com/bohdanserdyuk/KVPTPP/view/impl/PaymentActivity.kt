package com.bohdanserdyuk.KVPTPP.view.impl

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.view.BaseActivity
import kotlinx.android.synthetic.main.payment_view.*



class PaymentActivity : BaseActivity<BaseContract.PaymentView, BaseContract.PaymentPresenter>(), BaseContract.PaymentView {

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
        web_view.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                presenter.pageFinished(getString(R.string.users_pattern), getString(R.string.js_insert_pattern))
                super.onPageFinished(view, url)
            }
        }
    }

    override fun setDisplayHomeAsUpEnabled(b: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(b)
    }

    override fun setDisplayShowHomeEnabled(b: Boolean) {
        supportActionBar?.setDisplayShowHomeEnabled(b)
    }

    override fun loadPage(url: String) {
        web_view.loadUrl(url)
    }

    override fun loadPage(urlResID: Int) {
        web_view.loadUrl(getString(urlResID))
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
