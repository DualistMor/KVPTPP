package com.bohdanserdyuk.KVPTPP.view.impl

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bohdanserdyuk.KVPTPP.R
import com.bohdanserdyuk.KVPTPP.contract.BaseContract
import com.bohdanserdyuk.KVPTPP.model.impl.PreferencesModel
import com.bohdanserdyuk.KVPTPP.view.BaseActivity
import kotlinx.android.synthetic.main.once_showing_screen.*

class OnceShowingScreen : BaseActivity<BaseContract.OnceView, BaseContract.OncePresenter>(), BaseContract.OnceView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.once_showing_screen)

        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        presenter.buttonClicked(editText.text.toString())
    }

    override fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun showWrongPibToast() {
        Toast.makeText(this, getString(R.string.wrong_pib), Toast.LENGTH_LONG).show()
    }

    override fun initPresenter(): BaseContract.OncePresenter {
        val sharedPreferences = dependencyInjector.sharedPreferences(this)
        return dependencyInjector.oncePresenter(PreferencesModel(this, sharedPreferences, sharedPreferences.edit()))
    }
}
