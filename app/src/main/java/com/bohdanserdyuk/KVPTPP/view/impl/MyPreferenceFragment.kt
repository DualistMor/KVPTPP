package com.bohdanserdyuk.KVPTPP.view.impl

import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.widget.Toast
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract

class MyPreferenceFragment() : PreferenceFragment() {
    lateinit var presenter: BaseContract.SettingsPresenter

    constructor(presenter: BaseContract.SettingsPresenter) : this() {
        this.presenter = presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences_screen)

        var editPref: EditTextPreference = preferenceScreen.findPreference(getString(R.string.pib_key)) as EditTextPreference
        editPref.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
            if (presenter.isFullNameCorrectUA(newValue.toString()))
                true
            else {
                Toast.makeText(activity, getString(R.string.wrong_pib), Toast.LENGTH_LONG).show()
                false
            }
        }
    }

    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {
        when (preference!!.key) {
            getString(R.string.website_key) -> presenter.startWebsiteClicked()
            getString(R.string.feedback_key) -> presenter.sendFeedbackClicked()
        }
        return true
    }
}