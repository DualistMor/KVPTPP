package com.bohdanserdyuk.KVPTPP.view.impl

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import com.bohdanserdyuk.KVPTPP.BuildConfig
import com.bohdanserdyuk.KVPTPP.KVPTPPAplication
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.broker.StartPayment
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.view.BaseActivity
import com.lucky_apps.RainViewer.viewLayer.viewModel.BasePresenterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.app_version_view.*


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

        presenter.startMainFragment()
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
            supportActionBar?.title = getString(R.string.menu_services)
            resyncActionBarDrawerToggle()
            supportFragmentManager.popBackStack()
        }
    }

    override fun onClick(v: View?) = presenter.itemSelected(floatingActionButton.id)

    override fun launchMainWebsite() {
        startActivity(Intent(Intent.ACTION_VIEW,
            Uri.parse(getString(R.string.main_website))))
    }

    override fun launchRequestWebsite() {
        startActivity(Intent(Intent.ACTION_VIEW,
            Uri.parse(getString(R.string.request_website))))
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
                    supportActionBar?.title = this.getString(R.string.editing)
                    MyPreferenceFragment()
                }
                R.id.nav_services -> {
                    supportActionBar?.title = this.getString(R.string.menu_services)
                    ServicesFragment()
                }
                R.id.nav_about_us -> {
                    supportActionBar?.title = this.getString(R.string.menu_about)
                    AboutFragment()
                }
                else -> MyPreferenceFragment()
            }
        )
    }

    private fun animateChangeFragment(f: Fragment) {
        supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_from_right,
            R.anim.slide_to_left,
            R.anim.slide_from_left,
            R.anim.slide_to_right)
            .replace(R.id.container, f)
            .addToBackStack(null)
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun startPayment() {
        supportActionBar?.title = this.getString(R.string.payment)
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
