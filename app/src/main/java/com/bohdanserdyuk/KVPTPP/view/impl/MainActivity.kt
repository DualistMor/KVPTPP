package com.bohdanserdyuk.KVPTPP.view.impl

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bohdanserdyuk.KVPTPP.KVPTPPAplication
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.broker.ScrolledDown
import com.bohdanserdyuk.KVPTPP.broker.ScrolledUp
import com.bohdanserdyuk.KVPTPP.broker.StartPayment
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.view.BaseActivity
import com.bohdanserdyuk.KVPTPP.viewModel.BasePresenterViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY
import android.app.Activity
import android.view.inputmethod.InputMethodManager


class MainActivity : BaseActivity<BaseContract.MainView, BaseContract.MainPresenter>(),
    BaseContract.MainView,
    NavigationView.OnNavigationItemSelectedListener,
    View.OnClickListener,
    Observer<Any> {

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as KVPTPPAplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initializeActivity() {
        setSupportActionBar(toolbar)
        floatingActionButton.setOnClickListener(this)
        resyncActionBarDrawerToggle()
        nav_view.setNavigationItemSelectedListener(this)
        ViewModelProviders.of(this).get(BasePresenterViewModel::class.java).getMessageContainer().observe(this, this)
        presenter.startMainFragment()
    }

    private fun resyncActionBarDrawerToggle() {
        toggle = ActionBarDrawerToggle(this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggleNavigationDrawer(true)
        toggle.syncState()
    }

    override fun showFab() = floatingActionButton.show()

    override fun hideFab() = floatingActionButton.hide()

    override fun toggleNavigationDrawer(v: Boolean) {
        drawer_layout.setDrawerLockMode(if (v) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        toggle.isDrawerIndicatorEnabled = v
    }

    override fun launchMainWebsite() = startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.main_website))))


    override fun startPayment() = animateChangeFragment(PaymentFragment(), true)

    override fun sendFeedback() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.feedback_email)))
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_subject))
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.feedback_mail_body))
        startActivity(Intent.createChooser(intent, ""))
    }

    override fun shareApp() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "plain/text"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.apps_url))
        startActivity(Intent.createChooser(sharingIntent, ""))
    }

    override fun animateChangeFragment(id: Int, addToStack: Boolean) {
        animateChangeFragment(
            when (id) {
                floatingActionButton.id -> SettingsFragment()
                R.id.nav_request -> RequestFragment()
                R.id.nav_services -> ServicesFragment()
                R.id.nav_about_us -> AboutFragment()
                else -> ServicesFragment()
            }
            , addToStack)
    }

    private fun animateChangeFragment(f: Fragment, addToStack: Boolean) {
        if (getVisibleFragment() == null || f::class.java.simpleName != getVisibleFragment()!!::class.java.simpleName) {
            val trans = supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_from_right,
                R.anim.slide_to_left,
                R.anim.slide_from_left,
                R.anim.slide_to_right)
                .replace(R.id.container, f)
            if (addToStack) {
                trans.addToBackStack(null)
                toolbar.setNavigationOnClickListener {
                    onBackPressed()
                }
                toggleNavigationDrawer(false)
            }
            trans.commit()
        }
    }

    override fun onChanged(t: Any?) {
        when (t) {
            is StartPayment -> presenter.startPayment()
            is ScrolledDown -> presenter.scrolledDown()
            is ScrolledUp -> presenter.scrolledUp()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(false)
            resyncActionBarDrawerToggle()
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onClick(v: View?) = presenter.itemSelected(floatingActionButton.id)

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_services -> presenter.itemSelected(R.id.nav_services)
            R.id.nav_request -> presenter.itemSelected(R.id.nav_request)
            R.id.nav_about_us -> presenter.itemSelected(R.id.nav_about_us)
            R.id.nav_website -> presenter.itemSelected(R.id.nav_website)
            R.id.nav_feedback -> presenter.itemSelected(R.id.nav_feedback)
            R.id.nav_share -> presenter.itemSelected(R.id.nav_share)
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun getVisibleFragment(): Fragment? {
        supportFragmentManager.fragments.forEach { if (it != null && it.isVisible) return it }
        return null
    }
}
