package com.bohdanserdyuk.KVPTPP.view.impl

import android.content.Intent
import android.os.Bundle
import com.bohdanserdyuk.KVPTPP.KVPTPPAplication
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.view.BaseActivity


class SplashScreen : BaseActivity<BaseContract.SplashView, BaseContract.SplashPresenter>(), BaseContract.SplashView {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as KVPTPPAplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
    }

    override fun resolve(id: Int): Int {
        return resources.getInteger(id)
    }

    override fun <T> startActivity(c: Class<T>) {
        startActivity(Intent(this, c))
        finish()
    }
}
