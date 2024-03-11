package com.pthw.biometricwithasymmetric.feature.setup

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import co.onenex.biometric.NexBiometric
import co.onenex.biometric.utils.DataState
import com.pthw.biometricwithasymmetric.R
import com.pthw.biometricwithasymmetric.appbase.utils.androidSecureId
import com.pthw.biometricwithasymmetric.appbase.viewstate.ObjViewState
import com.pthw.biometricwithasymmetric.appbase.viewstate.RenderCompose
import com.pthw.biometricwithasymmetric.di.requireBiometricSetupPageEntryPoint
import com.pthw.biometricwithasymmetric.ui.composable.LoadingDialog
import com.pthw.biometricwithasymmetric.ui.theme.BiometricWithAsymmetricTheme
import com.pthw.biometricwithasymmetric.ui.theme.Dimens
import timber.log.Timber

/**
 * Created by P.T.H.W on 08/03/2024.
 */

@Composable
fun BiometricSetupPage(
    modifier: Modifier = Modifier.background(color = Color.White),
    nexBiometric: NexBiometric = requireBiometricSetupPageEntryPoint().nexBiometric,
    context: Context = LocalContext.current,
    viewModel: BiometricViewModel = hiltViewModel(),
    navigateToVerify: () -> Unit,
) {

    Timber.w("Recompose ::::''''''''''Page")

    val title = stringResource(R.string.prompt_info_register_title)
    val desc = stringResource(R.string.prompt_info_subtitle)
    val bntText = stringResource(R.string.prompt_info_cancel)


    val biometricSignUpParams = remember {
        NexBiometric.SignUpParams(
            fragment = context as FragmentActivity,
            title = title,
            description = desc,
            btnText = bntText,
            getBiometricId = viewModel::getBiometricId
        )
    }

    viewModel.deviceId = context.androidSecureId
    var loadingState by remember { mutableStateOf(Pair<Boolean?, Boolean?>(null, null)) }


    Box {
        UiContent(modifier = modifier) {
            nexBiometric.signUp(
                params = biometricSignUpParams,
                renderer = object : DataState.Renderer<Unit>() {
                    override fun loading() {
                        loadingState = Pair(true, true)
                    }

                    override fun success(data: Unit) {
                        if (loadingState.first != false && loadingState.second != false) {
                            loadingState = Pair(true, false)
                        }
                    }
                })
        }


//    val uiState = viewModel.state.collectAsState(initial = ObjViewState.Idle()).value
//        RenderCompose(
//            state = uiState,
//            idle = {
//                loadingState = Pair(null, null)
//            },
//            loading = {
//                loadingState = Pair(true, true)
//            },
//            success = {
//                if (loadingState.first != false && loadingState.second != false) {
//                    loadingState = Pair(true, false)
//                }
//            }
//        )

        LoadingDialog(loadingState) {
            loadingState = Pair(false, false)
            navigateToVerify()
        }


    }

}


@Composable
fun UiContent(
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
                text = "Place your Finger",
                modifier = modifier,
                style = TextStyle(
                    fontSize = Dimens.TEXT_HEADING_2,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            )

            Text(
                text = "To setup Place your finger on the biometric sensor and movie",
                style = TextStyle(
                    fontSize = Dimens.TEXT_REGULAR,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            )
        }

        Image(
            painter = painterResource(id = R.drawable.img_biometric_setup),
            contentDescription = ""
        )

        Spacer(modifier = Modifier.padding(top = Dimens.MARGIN_XXLARGE))

        Button(onClick = {
            onClick()
        }) {
            Text(text = "Setup Biometric")
        }


    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SetupContentPreview() {
    BiometricWithAsymmetricTheme {
        UiContent(
            modifier = Modifier,
            onClick = {},
        )
    }
}