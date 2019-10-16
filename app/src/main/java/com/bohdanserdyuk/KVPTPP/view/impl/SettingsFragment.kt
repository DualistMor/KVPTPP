package com.bohdanserdyuk.KVPTPP.view.impl

import android.os.Bundle
import android.support.v7.preference.EditTextPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bohdanserdyuk.KVPTPP.KVPTPPAplication
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.view.BaseFragment
import javax.inject.Inject

class SettingsFragment : BaseFragment<BaseContract.SettingsView, BaseContract.SettingsPresenter>(), BaseContract.SettingsView {

    override fun onCreate(savedInstanceState: Bundle?) {
        (activity!!.application as KVPTPPAplication).appComponent.inject(this)

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter.onCreateView()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}