package com.bohdanserdyuk.KVPTPP.view.impl

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bohdanserdyuk.KVPTPP.R
import android.content.Intent
import android.support.v4.os.HandlerCompat.postDelayed
import android.os.Handler


class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, OnceShowingScreen::class.java))
            finish()
        }, resources.getInteger(R.integer.splash_delay).toLong())
    }
}
