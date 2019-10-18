package com.bohdanserdyuk.KVPTPP.view.impl

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bohdanserdyuk.KVPTPP.KVPTPPAplication
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.broker.ScrolledDown
import com.bohdanserdyuk.KVPTPP.broker.ScrolledUp
import com.bohdanserdyuk.KVPTPP.broker.StartPayment
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.view.BaseActivity
import com.google.android.material.navigation.NavigationView
import com.lucky_apps.RainViewer.viewLayer.viewModel.BasePresenterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : BaseActivity<BaseContract.MainView, BaseContract.MainPresenter>(),
    BaseContract.MainView,
    NavigationView.OnNavigationItemSelectedListener,
    View.OnClickListener,
    Observer<Any> {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as KVPTPPAplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        floatingActionButton.setOnClickListener(this)
        resyncActionBarDrawerToggle()
        nav_view.setNavigationItemSelectedListener(this)
        ViewModelProviders.of(this).get(BasePresenterViewModel::class.java).getMessageContainer().observe(this, this)

        if (savedInstanceState == null) {
            presenter.startMainFragment()
        }
    }

    private fun resyncActionBarDrawerToggle() {
        val toggle = ActionBarDrawerToggle(this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onChanged(t: Any?) {
        when (t) {
            is StartPayment -> presenter.startPayment()
            is ScrolledDown -> presenter.scrolledDown()
            is ScrolledUp -> presenter.scrolledUp()
        }
    }

    override fun showFab() {
        if (floatingActionButton.visibility != View.VISIBLE) {
            floatingActionButton.show()
        }
    }

    override fun hideFab() {
        if (floatingActionButton.visibility == View.VISIBLE) {
            floatingActionButton.hide()
        }
    }

    override fun startMainFragment(id: Int) {
        supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_from_right,
            R.anim.slide_to_left,
            R.anim.slide_from_left,
            R.anim.slide_to_right)
            .replace(R.id.container, ServicesFragment())
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(false)
            resyncActionBarDrawerToggle()

            supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onClick(v: View?) = presenter.itemSelected(floatingActionButton.id)

    override fun launchMainWebsite() {
        startActivity(Intent(Intent.ACTION_VIEW,
            Uri.parse(getString(R.string.main_website))))
    }

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

    override fun animateChangeFragment(id: Int) {
        animateChangeFragment(
            when (id) {
                floatingActionButton.id -> {
                    SettingsFragment()
                }
                R.id.nav_request -> {
                    RequestFragment()
                }
                R.id.nav_services -> {
                    ServicesFragment()
                }
                R.id.nav_about_us -> {
                    AboutFragment()
                }
                else -> SettingsFragment()
            }
        )
    }

    private fun animateChangeFragment(f: androidx.fragment.app.Fragment) {
        if (f::class.java.simpleName != getVisibleFragment()!!::class.java.simpleName) {
            supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_from_right,
                R.anim.slide_to_left,
                R.anim.slide_from_left,
                R.anim.slide_to_right)
                .replace(R.id.container, f)
                .addToBackStack(null)
                .commit()

            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun getVisibleFragment(): androidx.fragment.app.Fragment? {
        val fragmentManager = this@MainActivity.supportFragmentManager
        val fragments = fragmentManager.fragments
        if (fragments != null) {
            for (fragment in fragments) {
                if (fragment != null && fragment.isVisible)
                    return fragment
            }
        }
        return null
    }

    override fun startPayment() {
        animateChangeFragment(PaymentFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_services -> {
                presenter.itemSelected(R.id.nav_services)
            }
            R.id.nav_request -> {
                presenter.itemSelected(R.id.nav_request)
            }
            R.id.nav_about_us -> {
                presenter.itemSelected(R.id.nav_about_us)
            }
            R.id.nav_website -> {
                presenter.itemSelected(R.id.nav_website)
            }
            R.id.nav_feedback -> {
                presenter.itemSelected(R.id.nav_feedback)
            }
            R.id.nav_share -> {
                presenter.itemSelected(R.id.nav_share)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
