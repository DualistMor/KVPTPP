package com.bohdanserdyuk.KVPTPP.viewModel

import android.arch.lifecycle.ViewModel

import com.bohdanserdyuk.KVPTPP.contract.BaseContract


class BaseModelViewModel<M : BaseContract.Model> : ViewModel() {

    private var models: Array<M>? = null

    fun setModels(models: Array<M>) {
        if (this.models == null) {
            this.models = models
        }
    }

    fun getModels(): Array<M>? {
        return this.models
    }

    override fun onCleared() {
        super.onCleared()
        for (m in models!!) {
            m.onModelDestroy()
        }
        models = null
    }
}