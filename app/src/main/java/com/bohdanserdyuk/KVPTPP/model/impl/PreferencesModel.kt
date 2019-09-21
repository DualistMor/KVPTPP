package com.bohdanserdyuk.KVPTPP.model.impl

import android.content.SharedPreferences
import com.bohdanserdyuk.KVPTPP.contract.BaseContract

class PreferencesModel(val pref: SharedPreferences, val editor: SharedPreferences.Editor): BaseContract.Model {

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}