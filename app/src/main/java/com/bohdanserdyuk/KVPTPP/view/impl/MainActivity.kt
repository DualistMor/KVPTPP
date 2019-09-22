package com.bohdanserdyuk.KVPTPP.view.impl

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.view.BaseActivity
import com.bohdanserdyuk.KVPTPP.view.adapters.ServicesRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : BaseActivity<BaseContract.MainView, BaseContract.MainPresenter>(), BaseContract.MainView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton.setOnClickListener(this)
        servicesRecycler.layoutManager = LinearLayoutManager(this)
    }

    override fun setAdapter(array: Array<Any>) {
        servicesRecycler.adapter = ServicesRecyclerAdapter(this, array, layoutInflater)
    }

    override fun <T : Activity> startActivity(c: Class<T>) {
        startActivity(Intent(this, c))
    }

    override fun itemClick(i: Int) {
        presenter.itemClick(i)
    }

    override fun onClick(v: View?) {
        presenter.editClick()
    }

    override fun getServices(): Array<String> {
         return resources.getStringArray(R.array.services_array)
    }

    override fun initPresenter(): BaseContract.MainPresenter {
        val sharedPreferences = dependencyInjector.sharedPreferences(this)
        return dependencyInjector.mainPresenter(PreferencesModel(this, sharedPreferences, sharedPreferences.edit()))

    }
}
