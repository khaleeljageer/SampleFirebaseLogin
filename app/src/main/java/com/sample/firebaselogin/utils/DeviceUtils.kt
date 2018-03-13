package com.sample.firebaselogin.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.WindowManager

/**
 * Created by khaleeljageer on 24/1/18.
 */
class DeviceUtils {

    companion object {
        fun setStatusBarColor(context: Context, colorId: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = (context as Activity).window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = fetchColor(context, colorId)
            }
        }

        private fun fetchColor(context: Context, colorType: Int): Int {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(colorType, typedValue, true)
            return typedValue.data
        }
    }

}