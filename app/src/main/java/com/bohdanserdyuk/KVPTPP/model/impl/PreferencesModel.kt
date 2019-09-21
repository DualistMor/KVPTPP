package com.bohdanserdyuk.KVPTPP.model.impl

import android.content.Context
import android.content.SharedPreferences
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract

class PreferencesModel(val context: Context, val pref: SharedPreferences, val editor: SharedPreferences.Editor) : BaseContract.Model {

    var isNewUser: Boolean
        get() = get(context.getString(R.string.is_new_user_key), true)
        set(v) = set(context.getString(R.string.is_new_user_key), v)

    var pib: String
        get() = get(context.getString(R.string.pib_key), "")
        set(v) = set(context.getString(R.string.pib_key), v)

    fun set(k: String, v: Long) {
        editor.putLong(k, v).commit()
    }

    fun get(k: String, def: Long): Long {
        return pref.getLong(k, def)
    }

    fun set(k: String, v: Int) {
        editor.putInt(k, v).commit()
    }

    fun get(k: String, def: Int): Int {
        return pref.getInt(k, def)
    }

    fun set(k: String, v: Boolean) {
        editor.putBoolean(k, v).commit()
    }

    fun get(k: String, def: Boolean): Boolean {
        return pref.getBoolean(k, def)
    }

    fun set(k: String, v: String) {
        editor.putString(k, v).commit()
    }

    fun get(k: String, def: String): String {
        return pref.getString(k, def)
    }

    fun set(k: String, v: Float) {
        editor.putFloat(k, v).commit()
    }

    fun get(k: String, def: Float): Float {
        return pref.getFloat(k, def)
    }

    fun remove(k: String) {
        editor.remove(k)
    }

    override fun onModelDestroy() {
        editor.commit()
    }
}