package com.pthw.biometricwithasymmetric.feature.setup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.pthw.biometricwithasymmetric.ui.theme.BiometricWithAsymmetricTheme
import com.pthw.biometricwithasymmetric.ui.theme.Dimens

/**
 * Created by P.T.H.W on 08/03/2024.
 */

@Composable
fun BiometricSetupPage(
    modifier: Modifier,
    onClick: () -> Unit,
    viewmodel: BiometricSetupViewModel? = hiltViewModel()
) {
    Text(
        text = "This is Biometric setup page!!",
        modifier = modifier
            .fillMaxSize()
            .clickable {
//                viewmodel?.createBiometric("deviceId", "public key")
                onClick()
            },
        style = TextStyle(
            fontSize = Dimens.TEXT_BIG,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    )
}


@Preview
@Composable
private fun BiometricSetupPagePreview() {
    BiometricWithAsymmetricTheme {
        BiometricSetupPage(
            modifier = Modifier,
            onClick = {},
            viewmodel = null
        )
    }
}