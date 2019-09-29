package com.bohdanserdyuk.KVPTPP.view.impl

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bohdanserdyuk.KVPTPP.KVPTPPAplication
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.entity.ServiceData
import com.bohdanserdyuk.KVPTPP.model.repository.ServicesModel
import com.bohdanserdyuk.KVPTPP.model.repository.impl.PreferencesModelImpl
import com.bohdanserdyuk.KVPTPP.presenter.entity.Service
import com.bohdanserdyuk.KVPTPP.view.BaseActivity
import com.bohdanserdyuk.KVPTPP.view.adapters.ServicesRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<BaseContract.MainView, BaseContract.MainPresenter>(), BaseContract.MainView, View.OnClickListener {
    @Inject
    lateinit var servicesModel: ServicesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as KVPTPPAplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton.setOnClickListener(this)
        servicesRecycler.layoutManager = LinearLayoutManager(this)
    }

    override fun setAdapter(list: List<Service>) {
        servicesRecycler.adapter = ServicesRecyclerAdapter(this, list, layoutInflater)
    }

    override fun <T : Activity> startActivity(c: Class<T>) {
        startActivity(Intent(this, c))
    }

    override fun itemClick(s: Service) {
        presenter.itemClick(s)
    }

    override fun onClick(v: View?) {
        presenter.editClick()
    }

    override fun initPresenter(): BaseContract.MainPresenter {
        val sharedPreferences = dependencyInjector.sharedPreferences(this)
        return dependencyInjector.mainPresenter(PreferencesModelImpl(this, sharedPreferences, sharedPreferences.edit()), servicesModel)

    }
}
