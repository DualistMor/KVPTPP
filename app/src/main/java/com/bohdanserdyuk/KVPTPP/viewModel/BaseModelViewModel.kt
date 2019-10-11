package com.bohdanserdyuk.KVPTPP.viewModel

import android.arch.lifecycle.ViewModel

import com.bohdanserdyuk.KVPTPP.contract.BaseContract


class BaseModelViewModel<M : BaseContract.Models> : ViewModel() {

    private var models: M? = null

    fun setModels(models: M) {
        if (this.models == null) {
            this.models = models
        }
    }

    fun getModels(): M? {
        return this.models
    }

    override fun onCleared() {
        super.onCleared()
        for (m in models!!.models) {
            m.onModelDestroy()
        }
        models = null
    }
}