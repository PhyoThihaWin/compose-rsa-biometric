package com.pthw.biometricwithasymmetric

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.pthw.biometricwithasymmetric.navigation.MainNavHost
import com.pthw.biometricwithasymmetric.ui.theme.BiometricWithAsymmetricTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BiometricWithAsymmetricTheme {
                Scaffold(
                    modifier = Modifier.background(color = Color.White)
                ) {
                    MainNavHost(
                        navController = rememberNavController(),
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }

}
