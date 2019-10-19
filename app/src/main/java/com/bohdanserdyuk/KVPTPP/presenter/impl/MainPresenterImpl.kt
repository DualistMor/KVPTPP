package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(model: BaseContract.MainModel) : BasePresenter<BaseContract.MainView, BaseContract.MainModel>(model), BaseContract.MainPresenter {
    override fun itemSelected(id: Int) {
        when (id) {
            R.id.nav_website -> view.launchMainWebsite()
            R.id.nav_feedback -> view.sendFeedback()
            R.id.nav_share -> view.shareApp()
            else -> view.animateChangeFragment(id)
        }
    }

    override fun scrolledDown() {
        view.hideFab()
    }

    override fun scrolledUp() {
        view.showFab()
    }

    override fun startMainFragment() {
        view.animateChangeFragment(R.id.nav_services)
    }

    override fun startPayment() {
        view.startPayment()
    }
}