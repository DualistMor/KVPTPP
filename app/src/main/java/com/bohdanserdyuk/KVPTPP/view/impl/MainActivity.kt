package com.bohdanserdyuk.KVPTPP.view.impl

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.view.BaseActivity

class MainActivity : BaseActivity<BaseContract.MainView, BaseContract.MainPresenter>(), BaseContract.MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun <E> setAdapter(array: Array<E>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeToolbarTitle(id: Int) {
        supportActionBar?.title = getString(id)
    }

    override fun navigateToPreferences() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToPayment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun itemClick(i: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun editClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initPresenter(): BaseContract.MainPresenter {
        val sharedPreferences = dependencyInjector.sharedPreferences(this)
        return dependencyInjector.mainPresenter(PreferencesModel(this, sharedPreferences, sharedPreferences.edit()))

    }
}
