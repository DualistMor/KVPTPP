package com.bohdanserdyuk.KVPTPP.view.impl


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bohdanserdyuk.KVPTPP.BuildConfig
import com.bohdanserdyuk.KVPTPP.KVPTPPAplication
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.view.BaseFragment
import kotlinx.android.synthetic.main.app_version_view.view.*
import kotlinx.android.synthetic.main.fragment_about.view.*

class AboutFragment : BaseFragment<BaseContract.AboutView, BaseContract.AboutPresenter>(), BaseContract.AboutView, View.OnClickListener {
    lateinit var v: View

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity!!.application as KVPTPPAplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_about, container, false)
        v.email.setOnClickListener(this)
        v.address.setOnClickListener(this)
        presenter.onCreateView()
        return v
    }

    override fun showAppsVersion() {
        v.appsVersion.text = String.format(getString(R.string.version_char), BuildConfig.VERSION_NAME)
    }

    override fun saveToClipboard(text: CharSequence?) {
        val clipboard = activity!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.primaryClip = ClipData.newPlainText("label", text)
    }

    override fun showToast(stringId: Int) {
        Toast.makeText(activity, getString(stringId), Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {
        if (v is TextView) {
            presenter.saveToClipboard(v.text)
        }
    }
}
