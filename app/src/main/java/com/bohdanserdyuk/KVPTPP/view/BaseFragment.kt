package com.bohdanserdyuk.KVPTPP.view

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceFragmentCompat
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.view.helpers.FragmentGetActivityHelper
import com.bohdanserdyuk.KVPTPP.viewModel.BasePresenterViewModel
import javax.inject.Inject


abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : PreferenceFragmentCompat(), BaseContract.View, LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

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

        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        super.onCreate(savedInstanceState)
    }

    override fun getLifecycle(): LifecycleRegistry = lifecycleRegistry

    override fun fillSupportActionBar(stringId: Int, isBackArrow: Boolean) {
        val actionBar = (fragmentGetActivityHelper.getActivity(this) as AppCompatActivity).supportActionBar
        actionBar?.title = this.getString(stringId)
        actionBar?.setDisplayHomeAsUpEnabled(isBackArrow)
        actionBar?.setHomeButtonEnabled(isBackArrow)
    }

    //--------------------------------------------------------------------------
    // region Lifecycle.
    // TODO: this is only needed because of this bug: https://issuetracker.google.com/issues/62160522
    //--------------------------------------------------------------------------

    override fun onStart() {
        super.onStart()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onResume() {
        super.onResume()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        super.onPause()
    }

    override fun onStop() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        super.onStop()
    }

    // endregion
    @CallSuper
    override fun onDestroy() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        super.onDestroy()
        presenter.detachLifecycle(lifecycle)
        presenter.detachView()
        presenter.detachModels()
    }
}
