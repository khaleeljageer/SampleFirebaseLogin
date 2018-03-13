package com.sample.firebaselogin.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.sample.firebaselogin.utils.DeviceUtils

import java.lang.ref.WeakReference
import com.firebase.ui.auth.AuthUI
import java.util.*
import com.google.firebase.auth.FirebaseAuth
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseUser
import com.sample.firebaselogin.HomeTabActivity
import com.sample.firebaselogin.SampleLoginApp
import com.sample.firebaselogin.R


/**
 * Created by khaleeljageer on 23/1/18.
 */
class SplashActivity : Activity() {
    private val RC_SIGN_IN = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DeviceUtils.setStatusBarColor(this, R.attr.colorPrimaryDark)
        setContentView(R.layout.activity_splash)

        val mHandler = RTHandler(this@SplashActivity)
        mHandler.sendEmptyMessageDelayed(1, 1500)
    }

    private class RTHandler internal constructor(splashScreen: SplashActivity) : Handler() {
        internal var splash: WeakReference<SplashActivity> = WeakReference(splashScreen)

        override fun handleMessage(msg: Message) {
            val activity = this.splash.get()
            if (!(activity == null || msg.what != 1 || activity.isDestroyed)) {
                activity.launchNextScreen()
            }
        }
    }

    private fun launchNextScreen() {

        val auth: FirebaseAuth? = SampleLoginApp.getAuth()
        val user: FirebaseUser? = auth?.currentUser
        if (user == null) {
            val providers = Arrays.asList(
                    AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                    AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build())

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setAllowNewEmailAccounts(true)
                            .setLogo(R.mipmap.ic_launcher_round)
                            .setTheme(R.style.LoginTheme)
                            .build(),
                    RC_SIGN_IN)
        } else {

            startActivity(HomeTabActivity.newIntent(this))
            this.finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            if(response != null) {
                Log.d("Splash", "Response : Token : ${response.idpToken}\nSecret : ${response.idpSecret}")
                if (resultCode == Activity.RESULT_OK) {
                    FirebaseAuth.getInstance().currentUser!!.reload()
                    startActivity(HomeTabActivity.newIntent(this))
                    this.finish()
                } else {
                    // Sign in failed, check response for error code
                    // ...
                }
            }
        }
    }
}