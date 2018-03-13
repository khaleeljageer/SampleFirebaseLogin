package com.sample.firebaselogin

import android.app.Application
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by khaleeljageer on 24/1/18.
 */
class SampleLoginApp : Application() {

    override fun onCreate() {
        super.onCreate()

        mAuth = FirebaseAuth.getInstance()
    }

    companion object {
        private var mAuth: FirebaseAuth? = null

        fun getAuth(): FirebaseAuth? {
            if (mAuth == null) {
                mAuth = FirebaseAuth.getInstance()
            }
            return mAuth
        }
    }
}