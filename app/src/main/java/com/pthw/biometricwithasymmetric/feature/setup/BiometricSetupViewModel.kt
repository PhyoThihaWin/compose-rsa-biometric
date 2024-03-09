package com.pthw.biometricwithasymmetric.feature.setup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocket.customer.domain.TwoParams
import com.pthw.biometricwithasymmetric.appbase.viewstate.ObjViewState
import com.pthw.biometricwithasymmetric.core.domain.usecase.CreateBiometricUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by P.T.H.W on 08/03/2024.
 */

@HiltViewModel
class BiometricSetupViewModel @Inject constructor(
    private val createBiometricUseCase: CreateBiometricUseCase
) : ViewModel() {


    var createBiometricState by mutableStateOf<ObjViewState<String>>(ObjViewState.Idle())
        private set

    fun createBiometric(deviceId: String, publicKey: String) {
        createBiometricState = ObjViewState.Loading()
        viewModelScope.launch {
            runCatching {
                val data = createBiometricUseCase.execute(TwoParams(deviceId, publicKey))
                createBiometricState = ObjViewState.Success(data)
            }.getOrElse {
                Timber.e(it)
            }
        }
    }


}