package com.bohdanserdyuk.KVPTPP.view.impl

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bohdanserdyuk.KVPTPP.KVPTPPAplication
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.view.BaseFragment
import com.bohdanserdyuk.KVPTPP.view.heplers.PermissionChecker
import kotlinx.android.synthetic.main.web_view.view.*


class RequestFragment : BaseFragment<BaseContract.RequestView, BaseContract.RequestPresenter>(), BaseContract.RequestView {
    var PERMISSIONS = arrayOf(Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE)
    lateinit var v: View

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity!!.application as KVPTPPAplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.web_view, container, false)
        v.cover.visibility = View.VISIBLE
        v.web_view.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                if (presenter.isViewAttached) {
                    presenter.pageFinished()
                    super.onPageFinished(view, url)
                }
            }
        }
        presenter.onCreateView()
        return v
    }

    override fun hideProgressBar() {
        v.cover.visibility = View.GONE
    }

    override fun loadPage(urlResID: Int) = loadPageWithPermissions(resources.getString(urlResID))


    private fun loadPageWithPermissions(url: String) {
        if (PermissionChecker().hasPermissions(activity, PERMISSIONS[0], PERMISSIONS[1])) {
            v.web_view.loadUrl(url)
        } else {
//            ActivityCompat.requestPermissions(activity, PERMISSIONS, PERMISSION_ALL)
        }
    }
}
