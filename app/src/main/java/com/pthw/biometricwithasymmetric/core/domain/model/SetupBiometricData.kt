package com.pthw.biometricwithasymmetric.core.domain.model

/**
 * Created by P.T.H.W on 11/03/2024.
 */

data class CreateBiometricData(
    val id: Int,
    val deviceId: String,
    val publicKey: String,
    val biometricId: String,
)

data class SetupBiometricData(
    val data: CreateBiometricData,
    val token: String,
)