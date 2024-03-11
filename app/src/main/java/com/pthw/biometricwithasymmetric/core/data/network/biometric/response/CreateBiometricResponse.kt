package com.pthw.biometricwithasymmetric.core.data.network.biometric.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateBiometricResponse(
    @SerialName("id") val id: Int?,
    @SerialName("device_id") val deviceId: String?,
    @SerialName("public_key") val publicKey: String?,
    @SerialName("biometric_id") val biometricId: String?
)

@Serializable
data class SetupBiometricResponse(
    @SerialName("data") val data: CreateBiometricResponse?,
    @SerialName("token") val token: String?,
)