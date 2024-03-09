package com.pthw.biometricwithasymmetric.appbase.utils

/**
 * Created by P.T.H.W on 05/03/2024.
*/

fun String.isInDev(): Boolean = (this == "debug") || (this == "uat")
