package com.bohdanserdyuk.KVPTPP.contract

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.os.Bundle

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
    interface SplashView : View {
        fun <T> startActivity(c: Class<T>)
        fun resolve(id: Int): Int
    }

    interface SplashPresenter : Presenter<SplashView>
    /**
     * Once screen contract
     */
    interface OnceView : View {
        fun startMainActivity()
        fun showWrongPibToast()
    }

    interface OncePresenter : Presenter<OnceView> {
        fun buttonClicked(pib: String)
    }

    /**
     * Main screen contract
     */
    interface MainView : View {
        fun setAdapter(array: Array<Any>)

        fun <T : Activity> startActivity(c: Class<T>)

        fun itemClick(i: Int)

        fun getServices(): Array<String>
    }

    interface MainPresenter : Presenter<MainView> {
        fun itemClick(i: Int)

        fun editClick()
    }

    /**
     * Payment screen contract
     */
    interface PaymentView : View {
        fun setDisplayHomeAsUpEnabled(b: Boolean)

        fun setDisplayShowHomeEnabled(b: Boolean)

        fun loadPage(url: String)

        fun loadPage(urlResID: Int)

        fun goBack()
        fun hideProgressBar()
    }

    interface PaymentPresenter : Presenter<PaymentView> {
        fun pageFinished(usersPattern: String, jsPattern: String)

        fun onBackButtonPressed()
    }

    /**
     * Settings screen contract
     */
    interface PreferencesView : View {

        fun setDisplayHomeAsUpEnabled(b: Boolean)

        fun setDisplayShowHomeEnabled(b: Boolean)

        fun startWebsite()

        fun sendFeedback()

        fun setVersionName()
        fun goBack()
    }

    interface PreferencesPresenter : Presenter<PreferencesView> {
        fun startWebsiteClicked()

        fun sendFeedbackClicked()
        fun onBackButtonPressed()
        fun isFullNameCorrectUA(toString: String): Boolean
    }
}