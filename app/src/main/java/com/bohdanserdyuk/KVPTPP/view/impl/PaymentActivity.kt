package com.bohdanserdyuk.KVPTPP.view.impl

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.view.BaseActivity

class PaymentActivity : BaseActivity<BaseContract.PaymentView, BaseContract.PaymentPresenter>(), BaseContract.PaymentView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_view)
    }

    override fun loadPage(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editPage(url: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initPresenter(): BaseContract.PaymentPresenter {
        val sharedPreferences = dependencyInjector.sharedPreferences(this)
        return dependencyInjector.paymentPresenter(PreferencesModel(sharedPreferences, sharedPreferences.edit()))
    }
}
