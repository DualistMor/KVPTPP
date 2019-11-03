package com.bohdanserdyuk.KVPTPP.model.repository.impl

import android.content.Context
import android.content.SharedPreferences
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.model.repository.PreferencesModel

class PreferencesModelImpl(val context: Context, val pref: SharedPreferences, val editor: SharedPreferences.Editor) : PreferencesModel {

    override var isNewUser: Boolean
        get() = get(context.getString(R.string.is_new_user_key), true)
        set(v) = set(context.getString(R.string.is_new_user_key), v)

    override var pib: String
        get() = get(context.getString(R.string.pib_key), "")
        set(v) = set(context.getString(R.string.pib_key), v)

    override var selectedService: Int
        get() = get(context.getString(R.string.selected_service_id_key), 0)
        set(v) = set(context.getString(R.string.selected_service_id_key), v)


    override fun set(k: String, v: Long) {
        editor.putLong(k, v).commit()
    }

    override fun get(k: String, def: Long): Long {
        return pref.getLong(k, def)
    }

    override fun set(k: String, v: Int) {
        editor.putInt(k, v).commit()
    }

    override fun get(k: String, def: Int): Int {
        return pref.getInt(k, def)
    }

    override fun set(k: String, v: Boolean) {
        editor.putBoolean(k, v).commit()
    }

    override fun get(k: String, def: Boolean): Boolean {
        return pref.getBoolean(k, def)
    }

    override fun set(k: String, v: String) {
        editor.putString(k, v).commit()
    }

    override fun get(k: String, def: String): String {
        return pref.getString(k, def)!!
    }

    override fun set(k: String, v: Float) {
        editor.putFloat(k, v).commit()
    }

    override fun get(k: String, def: Float): Float {
        return pref.getFloat(k, def)
    }

    override fun remove(k: String) {
        editor.remove(k)
    }

    override fun onModelDestroy() {
        editor.commit()
    }
}