package com.pthw.biometricwithasymmetric.feature.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.onenex.biometric.exception.BiometricException
import co.onenex.biometric.model.valueclasses.BiometricId
import co.onenex.biometric.model.valueclasses.Challenge
import com.pthw.appbase.exceptionmapper.ExceptionHandler
import com.pthw.appbase.viewstate.ObjViewState
import com.pthw.domain.usecase.CreateBiometricUseCase
import com.pthw.domain.usecase.GetChallengeUseCase
import com.pthw.domain.usecase.utils.TwoParams
import com.pthw.domain.usecase.ValidateSignatureUseCase
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
    private val handler: ExceptionHandler,
    private val createBiometricUseCase: CreateBiometricUseCase,
    private val getChallengeUseCase: GetChallengeUseCase,
    private val validateSignatureUseCase: ValidateSignatureUseCase,
) : ViewModel() {

    var deviceId: String? = null

    // reference to nex-biometric create-biometric-id
    suspend fun getBiometricId(publicKey: String): BiometricId {
        runCatching {
            delay(300)
            return BiometricId(
                createBiometricUseCase.execute(
                    TwoParams(deviceId.orEmpty(), publicKey)
                )
            )
        }.getOrElse {
            throw BiometricException(handler.map(it), false)
        }
    }


    // reference to nex-biometric
    suspend fun getBiometricChallenge(biometricId: BiometricId): Challenge {
        runCatching {
            return Challenge(value = getChallengeUseCase.execute(biometricId.value))
        }.getOrElse {
            throw BiometricException(handler.map(it), false)
        }
    }


    private val _validateBiometric =
        MutableSharedFlow<ObjViewState<String>>()
    val validateBiometric = _validateBiometric.asSharedFlow()

    // signature valide
    fun validateBiometric(biometricId: String, signature: String) {
        viewModelScope.launch {
            runCatching {
                _validateBiometric.emit(ObjViewState.Loading())
                delay(300)
                Timber.w("Reached success!!")
                val data = validateSignatureUseCase.execute(
                    TwoParams(biometricId, signature)
                )
                _validateBiometric.emit(ObjViewState.Success(data))
            }.getOrElse {
                Timber.e(it)
                _validateBiometric.emit(
                    ObjViewState.Error(handler.map(it))
                )
            }
        }
    }

}

