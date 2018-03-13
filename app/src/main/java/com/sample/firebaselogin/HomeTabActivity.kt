package com.sample.firebaselogin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class HomeTabActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HomeTabActivity::class.java)
        }
    }
}
