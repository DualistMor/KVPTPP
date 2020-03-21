package com.bohdanserdyuk.KVPTPP.view

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceFragmentCompat
import com.bohdanserdyuk.KVPTPP.KVPTPPAplication
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.view.helpers.FragmentGetActivityHelper
import com.bohdanserdyuk.KVPTPP.viewModel.BasePresenterViewModel
import javax.inject.Inject
import com.squareup.leakcanary.RefWatcher


abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : PreferenceFragmentCompat(),
    BaseContract.View {

    @Inject
    lateinit var presenter: P

    @Inject
    lateinit var fragmentGetActivityHelper: FragmentGetActivityHelper

    /**
     * [BaseFragment] extends [PreferenceFragmentCompat]
     * cause [PreferenceFragmentCompat] extends [Fragment],
     * as a result [BaseFragment] could be parent for PreferenceFragment
     * and simple fragment too
     */
    override fun onCreatePreferences(p0: Bundle?, p1: String?) {}

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        val presenterViewModel = ViewModelProviders.of(this).get(BasePresenterViewModel<V, P>().javaClass)
        if (presenterViewModel.getPresenter() == null) {
            presenterViewModel.setPresenter(presenter)
        } else {
            presenter = presenterViewModel.getPresenter()!!
        }
        presenter.attachLifecycle(lifecycle)
        presenter.attachView(this as V)

        super.onCreate(savedInstanceState)
    }

    override fun fillSupportActionBar(stringId: Int, isBackArrow: Boolean) {
        val actionBar = (fragmentGetActivityHelper.getActivity(this) as AppCompatActivity).supportActionBar
        actionBar?.title = this.getString(stringId)
        actionBar?.setDisplayHomeAsUpEnabled(isBackArrow)
        actionBar?.setHomeButtonEnabled(isBackArrow)
    }

    // endregion
    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        val refWatcher = KVPTPPAplication.getRefWatcher(context!!)
        refWatcher.watch(this)

        presenter.detachLifecycle(lifecycle)
        presenter.detachView()
        presenter.detachModels()
    }
}
