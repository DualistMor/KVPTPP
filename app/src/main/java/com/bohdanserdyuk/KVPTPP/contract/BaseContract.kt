package com.bohdanserdyuk.KVPTPP.contract

import android.app.Activity
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
     * Splash screen contract
     */
    interface SplashView: View {
        fun <T : Activity> startActivity(c: Class<T>)
        fun resolve(id: Int): Int
    }
    interface SplashPresenter: Presenter<SplashView>
    /**
     * Once screen contract
     */
    interface OnceView: View {
        fun startMainActivity()
    }
    interface OncePresenter: Presenter<OnceView> {
        fun buttonClicked(pib: String)
    }
    /**
     * Main screen contract
     */
    interface MainView: View {
        fun <E> setAdapter(array: Array<E>)

        fun changeToolbarTitle(id: Int)

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