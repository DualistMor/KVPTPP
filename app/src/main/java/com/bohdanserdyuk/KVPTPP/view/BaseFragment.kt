package com.bohdanserdyuk.KVPTPP.view

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.PreferenceFragmentCompat
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.view.helpers.FragmentGetActivityHelper
import com.lucky_apps.RainViewer.viewLayer.viewModel.BasePresenterViewModel
import javax.inject.Inject


abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : PreferenceFragmentCompat(), BaseContract.View, LifecycleRegistryOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    @Inject
    lateinit var presenter: P

    @Inject
    lateinit var fragmentGetActivityHelper: FragmentGetActivityHelper

    override fun onCreatePreferences(p0: Bundle?, p1: String?) {}

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val presenterViewModel = ViewModelProviders.of(this).get(BasePresenterViewModel<V, P>().javaClass)
        if (presenterViewModel.getPresenter() == null) {
            presenterViewModel.setPresenter(presenter)
        } else {
            presenter = presenterViewModel.getPresenter()!!
        }
        presenter = presenterViewModel.getPresenter()!!
        presenter.attachLifecycle(lifecycle)
        presenter.attachView(this as V)
    }

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    override fun fillSupportActionBar(stringId: Int, isBackArrow: Boolean) {
        val actionBar = (fragmentGetActivityHelper.getActivity(this) as AppCompatActivity).supportActionBar
        actionBar?.title = this.getString(stringId)
        actionBar?.setDisplayHomeAsUpEnabled(isBackArrow)
        actionBar?.setHomeButtonEnabled(isBackArrow)
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        presenter.detachLifecycle(lifecycle)
        presenter.detachView()
        presenter.detachModels()
    }
}
