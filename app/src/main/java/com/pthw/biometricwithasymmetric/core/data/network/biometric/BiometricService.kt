package com.pthw.biometricwithasymmetric.core.data.network.biometric

import com.pthw.biometricwithasymmetric.core.data.network.DataResponse
import com.pthw.biometricwithasymmetric.core.data.network.biometric.response.CreateBiometricResponse
import com.pthw.biometricwithasymmetric.core.data.network.toKtor
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Parameters
import io.ktor.http.parameters
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.inject.Inject


private const val SUB_CREATE_BIOMETRIC = "user/biometric"
private const val SUB_GET_CHALLENGE = "user/biometric/challenge/%s"
private const val SUB_VALIDATE_SIGNATURE = "user/biometric/verify"

@Serializable
data class CreateBiometricRequest(
    val device_id: String,
    val public_key: String,
)

class BiometricService @Inject constructor(private val client: HttpClient) {

    suspend fun createBiometric(deviceId: String, publicKey: String): String {
        val endpoint = SUB_CREATE_BIOMETRIC.toKtor()
        return client.post(endpoint) {
            setBody(
                CreateBiometricRequest(
                    device_id = deviceId,
                    public_key = publicKey
                )
            )
        }.body<DataResponse<CreateBiometricResponse>>().data?.biometricId.orEmpty()
    }

    suspend fun getChallenge(deviceId: String): String {
        val endpoint = String.format(SUB_GET_CHALLENGE, deviceId).toKtor()
        return client.post(endpoint).body<String>()
    }

    suspend fun validateSignature(biometricId: String, signature: String): String {
        val endpoint = SUB_VALIDATE_SIGNATURE.toKtor()
        return client.post(endpoint) {
            setBody(formData {
                append("biometric_id", biometricId)
                append("signature", signature)
            })
        }.body<String>()
    }

}