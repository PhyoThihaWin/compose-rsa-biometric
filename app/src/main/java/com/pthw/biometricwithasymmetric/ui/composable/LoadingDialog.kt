package com.pthw.biometricwithasymmetric.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pthw.biometricwithasymmetric.R
import com.pthw.biometricwithasymmetric.ui.theme.Dimens

@Composable
fun LoadingDialog(
    modifier: Modifier = Modifier,
    loadingState: Pair<Boolean?, Boolean?>, onDismissRequest: () -> Unit
) {
    if (loadingState.first == true) {
        Dialog(
            onDismissRequest = onDismissRequest,
            DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(Dimens.RADIUS_MEDIUM)
                    )
                    .padding(all = Dimens.MARGIN_MEDIUM),
                contentAlignment = Alignment.Center
            ) {
                if (loadingState.second != false) {
                    CircularProgressIndicator()
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.img_biometric_success),
                        contentDescription = "",
                    )
                }
            }
        }
    }
}