package com.bohdanserdyuk.KVPTPP.view.impl

import android.os.Bundle
import android.support.v7.preference.EditTextPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.widget.Toast
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract

class MyPreferenceFragment() : PreferenceFragmentCompat() {
    lateinit var presenter: BaseContract.SettingsPresenter

    constructor(presenter: BaseContract.SettingsPresenter) : this() {
        this.presenter = presenter
    }

    override fun onCreatePreferences(p0: Bundle?, p1: String?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences_screen)

        var editPref: EditTextPreference = preferenceScreen.findPreference(getString(R.string.pib_key)) as EditTextPreference
        editPref.onPreferenceChangeListener = Preference.OnPreferenceChangeListener {_, newValue ->
            if (presenter.isFullNameCorrectUA(newValue.toString()))
                true
            else {
                Toast.makeText(activity, getString(R.string.wrong_pib), Toast.LENGTH_LONG).show()
                false
            }
        }
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {
        when (preference!!.key) {
            getString(R.string.website_key) -> presenter.startWebsiteClicked()
            getString(R.string.feedback_key) -> presenter.sendFeedbackClicked()
        }
        return super.onPreferenceTreeClick(preference)
    }
}