package com.pthw.biometricwithasymmetric.appbase.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by P.T.H.W on 05/03/2024.
*/

fun String.isInDev(): Boolean = (this == "debug") || (this == "uat")

fun Context.showShortToast(message: CharSequence) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}