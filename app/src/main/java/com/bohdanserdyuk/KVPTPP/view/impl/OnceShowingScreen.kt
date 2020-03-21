package com.bohdanserdyuk.KVPTPP.view.impl

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bohdanserdyuk.KVPTPP.KVPTPPAplication
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.view.BaseActivity
import kotlinx.android.synthetic.main.once_showing_screen.*

class OnceShowingScreen : BaseActivity<BaseContract.OnceView, BaseContract.OncePresenter>(), BaseContract.OnceView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as KVPTPPAplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.once_showing_screen)

        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        presenter.buttonClicked(editText.text.toString())
    }

    override fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
