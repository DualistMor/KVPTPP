package com.bohdanserdyuk.KVPTPP.presenter.impl

import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.presenter.BasePresenter
import javax.inject.Inject

class AboutPresenterImpl @Inject constructor(aboutModel: BaseContract.AboutModel) : BasePresenter<BaseContract.AboutView, BaseContract.AboutModel>(aboutModel), BaseContract.AboutPresenter {
    override fun onCreateView() {
        view.showAppsVersion()
    }

    override fun saveToClipboard(text: CharSequence?) {
        view.showToast(R.string.clipboard_saved)
        view.saveToClipboard(text)
    }
}