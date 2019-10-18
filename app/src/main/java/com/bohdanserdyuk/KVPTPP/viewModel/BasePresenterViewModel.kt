package com.lucky_apps.RainViewer.viewLayer.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bohdanserdyuk.KVPTPP.contract.BaseContract

class BasePresenterViewModel<V : BaseContract.View, P : BaseContract.Presenter<V>> : ViewModel() {

    private var presenter: P? = null
    private var messageContainer = MutableLiveData<Any>()

    fun setPresenter(presenter: P) {
        if (this.presenter == null) {
            this.presenter = presenter
        }
    }

    fun getPresenter(): P? {
        return this.presenter
    }

    fun sendMessage(msg: Any) {
        messageContainer.value = msg
    }

    fun getMessageContainer(): LiveData<Any> {
        return messageContainer
    }

    override fun onCleared() {
        super.onCleared()
        messageContainer = MutableLiveData()
        presenter!!.onPresenterDestroy()
        presenter = null
    }
}