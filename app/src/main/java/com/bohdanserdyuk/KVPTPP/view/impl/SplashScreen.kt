package com.bohdanserdyuk.KVPTPP.view.impl

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.view.BaseActivity


class SplashScreen : BaseActivity<BaseContract.SplashView, BaseContract.SplashPresenter>(), BaseContract.SplashView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
    }

    override fun resolve(id: Int): Int {
        return resources.getInteger(id)
    }

    override fun <T : Activity> startActivity(c: Class<T>) {
        startActivity(Intent(this, c))
        finish()
    }

    override fun initPresenter(): BaseContract.SplashPresenter {
        val sharedPreferences = dependencyInjector.sharedPreferences(this)
        return dependencyInjector.splashPresenter(PreferencesModel(this, sharedPreferences, sharedPreferences.edit()))
    }
}
