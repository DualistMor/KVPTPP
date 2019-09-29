package com.bohdanserdyuk.KVPTPP.presenter.impl

import android.os.Handler
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import com.bohdanserdyuk.KVPTPP.view.impl.MainActivity
import com.bohdanserdyuk.KVPTPP.view.impl.OnceShowingScreen

class SplashPresenterImpl(val preferencesModelImpl: PreferencesModelImpl): BasePresenter<BaseContract.SplashView>(), BaseContract.SplashPresenter {

    override fun onCreate() {
        super.onCreate()
        Handler().postDelayed({
            view.startActivity(if (getModel(PreferencesModelImpl::class.java).isNewUser)
                OnceShowingScreen::class.java
            else
                MainActivity::class.java)
        }, view.resolve(R.integer.splash_delay).toLong())
    }

    override fun initModels(): Array<BaseContract.Model> {
        return dependencyInjector.preferencesModelArray(preferencesModelImpl)
    }
}