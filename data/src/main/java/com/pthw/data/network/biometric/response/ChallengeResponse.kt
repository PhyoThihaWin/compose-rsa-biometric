package com.pthw.data.network.biometric.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChallengeResponse(
    @SerialName("id") val id: Int?,
    @SerialName("challenge") val challenge: String?,
    @SerialName("device_id") val deviceId: String?,
    @SerialName("biometric_id") val biometricId: String?
)