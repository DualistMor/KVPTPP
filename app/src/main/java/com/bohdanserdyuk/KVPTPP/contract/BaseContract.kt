package com.bohdanserdyuk.KVPTPP.contract

import android.arch.lifecycle.Lifecycle
import android.content.SharedPreferences
import android.os.Bundle
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel

interface BaseContract {
    interface Model {
        fun onModelDestroy()
    }

    interface View

    interface Presenter<V : View> {
        val view: V

        val models: Array<Model>

        val isViewAttached: Boolean

        val stateBundle: Bundle

        fun attachLifecycle(lifecycle: Lifecycle)

        fun detachLifecycle(lifecycle: Lifecycle)

        fun attachView(view: V)

        fun detachView()

        fun initModels(): Array<Model>

        fun <T : Model> getModel(modelClass: Class<T>): Model

        fun detachModels()

        fun onPresenterDestroy()
    }

    /**
     * Main screen contract
     */
    interface MainView: View {
        fun <E> setAdapter(array: Array<E>)

        fun navigateToPreferences()

        fun navigateToPayment()

        fun itemClick(i: Int)

        fun editClick()
    }
    interface MainPresenter: Presenter<MainView>{
        fun itemClick(i: Int)

        fun editClick()
    }
    /**
     * Payment screen contract
     */
    interface PaymentView: View {
        fun loadPage(url: String)

        fun editPage(url: String)
    }
    interface PaymentPresenter: Presenter<PaymentView>{
        fun pageFinished()
    }
}