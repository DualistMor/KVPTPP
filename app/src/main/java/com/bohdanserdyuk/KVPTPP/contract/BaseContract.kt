package com.bohdanserdyuk.KVPTPP.contract

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import com.bohdanserdyuk.KVPTPP.presenter.entity.Service

interface BaseContract {
    interface Model {
        fun onModelDestroy()
    }

    interface Models {
        val models: Array<Model>
    }

    interface View {
        fun fillSupportActionBar(stringId: Int, isBackArrow: Boolean)
    }

    interface Presenter<V : View> {
        val view: V

        val models: Array<Model>

        val isViewAttached: Boolean

        val stateBundle: Bundle

        fun attachLifecycle(lifecycle: Lifecycle)

        fun detachLifecycle(lifecycle: Lifecycle)

        fun attachView(view: V)

        fun detachView()

        fun <T : Model> getModel(modelClass: Class<T>): Model

        fun detachModels()

        fun onPresenterDestroy()
    }

    /**
     * Main screen contract
     */
    interface MainView : View {
        fun toggleNavigationDrawer(v: Boolean)

        fun animateChangeFragment(id: Int)

        fun launchMainWebsite()

        fun sendFeedback()

        fun startPayment()

        fun shareApp()

        fun showFab()

        fun hideFab()
    }

    interface MainPresenter : Presenter<MainView> {
        fun itemSelected(id: Int)

        fun startPayment()

        fun startMainFragment()

        fun scrolledDown()

        fun scrolledUp()
    }

    interface MainModel : Models
    /**
     * Splash screen contract
     */
    interface SplashView : View {
        fun <T> startActivity(c: Class<T>)
        fun resolve(id: Int): Int
    }

    interface SplashPresenter : Presenter<SplashView>

    interface SplashModel : Models
    /**
     * About fragment contract
     */
    interface AboutView : View {
        fun showAppsVersion()

        fun saveToClipboard(text: CharSequence?)

        fun showToast(stringId: Int)
    }

    interface AboutPresenter : Presenter<AboutView> {
        fun onCreateView()

        fun saveToClipboard(text: CharSequence?)
    }

    interface AboutModel : Models
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

    interface OnceModel : Models
    /**
     * Services screen contract
     */
    interface ServicesView : View {
        fun setAdapter(list: List<Service>)

        fun <T : Activity> startActivity(c: Class<T>)

        fun itemClick(s: Service)

        fun sendMessage(m: Any)
    }

    interface ServicesPresenter : Presenter<ServicesView> {
        fun onCreateView()

        fun itemClick(s: Service)

        fun scrolledDown()

        fun scrolledUp()

        fun saveServices()
    }

    interface ServicesModel : Models
    /**
     * Payment screen contract
     */
    interface PaymentView : View {
        fun loadPage(url: String)

        fun loadPage(urlResID: Int)

        fun hideProgressBar()
    }

    interface PaymentPresenter : Presenter<PaymentView> {
        fun onCreateView()

        fun pageFinished(vararg patterns: String)
    }

    interface PaymentModel : Models
    /**
     * Request screen contract
     */
    interface RequestView : View {
        fun loadPage(urlResID: Int)

        fun hideProgressBar()
    }

    interface RequestPresenter : Presenter<RequestView> {
        fun onCreateView()

        fun pageFinished()
    }

    interface RequestModel : Models
    /**
     * Settings screen contract
     */
    interface SettingsView : View

    interface SettingsPresenter : Presenter<SettingsView> {
        fun isFullNameCorrectUA(toString: String): Boolean

        fun onCreateView()
    }

    interface SettingsModel : Models
}