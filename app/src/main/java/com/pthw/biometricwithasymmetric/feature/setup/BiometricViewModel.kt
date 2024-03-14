package com.pthw.biometricwithasymmetric.feature.setup

import androidx.lifecycle.viewModelScope
import co.onenex.biometric.exception.BiometricException
import co.onenex.biometric.model.valueclasses.BiometricId
import co.onenex.biometric.model.valueclasses.Challenge
import com.pthw.domain.usecase.CreateBiometricUseCase
import com.pthw.domain.usecase.GetChallengeUseCase
import com.pthw.domain.usecase.utils.ValidateSignatureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by P.T.H.W on 08/03/2024.
 */

@HiltViewModel
class BiometricViewModel @Inject constructor(
    private val createBiometricUseCase: CreateBiometricUseCase,
    private val getChallengeUseCase: GetChallengeUseCase,
    private val validateSignatureUseCase: ValidateSignatureUseCase
) : com.pthw.appbase.BaseViewModel() {

    var deviceId: String? = null

    // reference to nex-biometric create-biometric-id
    suspend fun getBiometricId(publicKey: String): BiometricId {
        runCatching {
            delay(300)
            return BiometricId(
                createBiometricUseCase.execute(
                    com.pthw.domain.usecase.utils.TwoParams(
                        deviceId.orEmpty(),
                        publicKey
                    )
                )
            )
        }.getOrElse {
            throw BiometricException(exception.map(it), false)
        }
    }


    private val _validateBiometric = MutableSharedFlow<com.pthw.appbase.viewstate.ObjViewState<String>>()
    val validateBiometric = _validateBiometric.asSharedFlow()

    fun validateBiometric(biometricId: String, signature: String) {
        viewModelScope.launch {
            runCatching {
                _validateBiometric.emit(com.pthw.appbase.viewstate.ObjViewState.Loading())
                delay(300)
                Timber.w("Reached success!!")
                val data = validateSignatureUseCase.execute(
                    com.pthw.domain.usecase.utils.TwoParams(
                        biometricId,
                        signature
                    )
                )
                _validateBiometric.emit(com.pthw.appbase.viewstate.ObjViewState.Success(data))
            }.getOrElse {
                Timber.e(it)
                _validateBiometric.emit(com.pthw.appbase.viewstate.ObjViewState.Error(exception.map(it)))
            }
        }
    }

    // reference to nex-biometric
    suspend fun getBiometricChallenge(biometricId: BiometricId): Challenge {
        runCatching {
            return Challenge(value = getChallengeUseCase.execute(biometricId.value))
        }.getOrElse {
            throw BiometricException(exception.map(it), false)
        }
    }


}