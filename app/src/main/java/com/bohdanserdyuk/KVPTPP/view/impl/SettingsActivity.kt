package com.bohdanserdyuk.KVPTPP.view.impl

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.widget.Toast
import com.bohdanserdyuk.KVPTPP.BuildConfig
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.view.BaseActivity
import kotlinx.android.synthetic.main.settings_screen.*


class SettingsActivity : BaseActivity<BaseContract.PreferencesView, BaseContract.PreferencesPresenter>(), BaseContract.PreferencesView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_screen)
        fragmentManager.beginTransaction().replace(R.id.content, MyPreferenceFragment(presenter)).commit()
    }

    override fun setDisplayHomeAsUpEnabled(b: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(b)
    }

    override fun setDisplayShowHomeEnabled(b: Boolean) {
        supportActionBar?.setDisplayShowHomeEnabled(b)
    }

    override fun startWebsite() {
        val uriUrl = Uri.parse(getString(R.string.main_website))
        val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
        startActivity(launchBrowser)
    }

    override fun sendFeedback() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.feedback_email)))
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.feedback_subject))
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.feedback_mail_body))
        startActivity(Intent.createChooser(intent, ""))
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        presenter.onBackButtonPressed()
        return true
    }

    override fun setVersionName() {
        appsVersion.text = String.format(getString(R.string.version_char), BuildConfig.VERSION_NAME)
    }

    override fun initPresenter(): BaseContract.PreferencesPresenter {
        val sharedPreferences = dependencyInjector.sharedPreferences(this)
        return dependencyInjector.settingsPresenter(PreferencesModel(this, sharedPreferences, sharedPreferences.edit()))

    }

}