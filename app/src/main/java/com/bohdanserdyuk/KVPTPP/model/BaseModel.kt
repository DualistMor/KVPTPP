package com.bohdanserdyuk.KVPTPP.model

import com.bohdanserdyuk.KVPTPP.contract.BaseContract

class BaseModel<M> : BaseContract.Model {
    internal var model: M? = null

    override fun onModelDestroy() {
        model = null
    }
}
