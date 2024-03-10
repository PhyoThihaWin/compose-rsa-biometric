package com.pthw.biometricwithasymmetric.feature.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.onenex.biometric.exception.BiometricException
import co.onenex.biometric.model.valueclasses.BiometricId
import co.onenex.biometric.model.valueclasses.Challenge
import com.pthw.biometricwithasymmetric.appbase.viewstate.ObjViewState
import com.pthw.biometricwithasymmetric.core.domain.usecase.CreateBiometricUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by P.T.H.W on 08/03/2024.
 */

@HiltViewModel
class BiometricViewModel @Inject constructor(
    private val createBiometricUseCase: CreateBiometricUseCase
) : ViewModel() {


    private val _state = MutableSharedFlow<ObjViewState<String>>()
    val state = _state.asSharedFlow()


    fun createBiometric(deviceId: String, publicKey: String) {
//        createBiometricState = ObjViewState.Loading()
        viewModelScope.launch {
//            runCatching {
//                val data = createBiometricUseCase.execute(TwoParams(deviceId, publicKey))
//                createBiometricState = ObjViewState.Success(data)
//            }.getOrElse {
//                Timber.e(it)
//            }


            _state.emit(ObjViewState.Loading())
            delay(1000)
            _state.emit(ObjViewState.Success("gg"))

//            createBiometricState = ObjViewState.Success("")
        }
    }


    // reference to nex-biometric create-biometric-id
    suspend fun getBiometricId(publicKey: String): BiometricId {
        delay(300)
        return BiometricId("")
    }

    // reference to nex-biometric
    suspend fun getBiometricChallenge(biometricId: BiometricId): Challenge {
        runCatching {
            return Challenge(value = "getBiometricChallengeUseCase.execute(biometricId.value)")
        }.getOrElse {
            throw BiometricException("exception.map(it)", false)
        }
    }


}