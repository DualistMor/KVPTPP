package com.bohdanserdyuk.KVPTPP.view.impl

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.view.View
import com.bohdanserdyuk.KVPTPP.KVPTPPAplication
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*



class MainActivity : BaseActivity<BaseContract.MainView, BaseContract.MainPresenter>(), BaseContract.MainView, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as KVPTPPAplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        floatingActionButton.setOnClickListener(this)
        val toggle = ActionBarDrawerToggle(this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
        supportFragmentManager.popBackStack()
    }

    override fun onClick(v: View?) {

        presenter.itemSelected(floatingActionButton.id)
    }

    override fun animateChangeFragment(id: Int) {
        animateChangeFragment(
        when(id) {
            floatingActionButton.id -> MyPreferenceFragment()
            R.id.nav_services ->
            R.id.nav_request ->
            R.id.nav_about_us ->
            R.id.nav_feedback ->
            R.id.nav_share ->
            else -> MyPreferenceFragment()
        }
        )
    }

    fun animateChangeFragment(f: Fragment) {
        supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_from_right,
            R.anim.slide_to_left,
            R.anim.slide_from_left,
            R.anim.slide_to_right)
            .replace(R.id.container, f)
            .commit()
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
