package com.pthw.biometricwithasymmetric.feature.verify

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import co.onenex.biometric.NexBiometric
import co.onenex.biometric.model.dataclasses.VerifySuccess
import co.onenex.biometric.utils.DataState
import com.pthw.biometricwithasymmetric.R
import com.pthw.biometricwithasymmetric.di.requireBiometricSetupPageEntryPoint
import com.pthw.biometricwithasymmetric.feature.setup.BiometricViewModel
import com.pthw.biometricwithasymmetric.ui.composable.LoadingDialog
import com.pthw.biometricwithasymmetric.ui.theme.BiometricWithAsymmetricTheme
import com.pthw.biometricwithasymmetric.ui.theme.Dimens
import com.pthw.shared.extension.showShortToast
import kotlinx.coroutines.launch

/**
 * Created by P.T.H.W on 08/03/2024.
 */

@Composable
fun BiometricVerifyPage(
    modifier: Modifier = Modifier.background(color = Color.White),
    nexBiometric: NexBiometric = requireBiometricSetupPageEntryPoint().nexBiometric,
    context: Context = LocalContext.current,
    viewModel: BiometricViewModel = hiltViewModel(),
) {

    val scope = rememberCoroutineScope()

    val title = stringResource(R.string.prompt_info_verify_title)
    val bntText = stringResource(R.string.prompt_info_cancel)

    val biometricVerifyParams = remember {
        NexBiometric.VerifyParams(
            fragment = context as FragmentActivity,
            title = title,
            btnText = bntText,
            getChallenge = viewModel::getBiometricChallenge
        )
    }

    val uiState = viewModel.validateBiometric.collectAsState(initial = com.pthw.appbase.viewstate.ObjViewState.Idle()).value
    var loadingState by remember { mutableStateOf(Pair<Boolean?, Boolean?>(null, null)) }

    Box {
        UiContent(modifier = modifier) {
            nexBiometric.verify(
                biometricVerifyParams,
                object : DataState.Renderer<VerifySuccess>() {
                    override fun success(data: VerifySuccess) {
                        viewModel.validateBiometric(data.biometricId.value, data.signature)
                    }

                    override fun error(message: DataState.ErrorMessage) {
                        loadingState = Pair(false, false)
                        scope.launch {
                            context.showShortToast("Biometric verification failed.")
                        }
                    }

                })
        }

        com.pthw.appbase.viewstate.RenderCompose(
            state = uiState,
            loading = {
                loadingState = Pair(true, true)
            },
            success = {
                if (loadingState.first != false && loadingState.second != false) {
                    loadingState = Pair(false, false)
                    context.showShortToast(it)
                }
            },
            error = {
                loadingState = Pair(false, false)
            }
        )

        LoadingDialog(
            loadingState = loadingState, {}
        )
    }
}

@Composable
private fun UiContent(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = Dimens.MARGIN_20, vertical = Dimens.MARGIN_XXLARGE),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Verify your Biometric",
                modifier = modifier,
                style = TextStyle(
                    fontSize = Dimens.TEXT_HEADING_2,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            )

            Text(
                text = "To verify, place your finger on the biometric sensor and movie",
                style = TextStyle(
                    fontSize = Dimens.TEXT_REGULAR,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            )
        }

        Image(
            painter = painterResource(id = R.drawable.img_biometric_verify),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.padding(top = Dimens.MARGIN_XXLARGE))

        Button(onClick = onClick) {
            Text(text = "Verify Biometric")
        }


    }
}

@Preview
@Composable
private fun UiContextPreview() {
    BiometricWithAsymmetricTheme {
        UiContent(
            modifier = Modifier.background(color = Color.White),
            onClick = {},
        )
    }
}