package com.pthw.data.network.biometric

import com.pthw.data.network.biometric.mapper.SetupBiometricDataMapper
import com.pthw.data.network.biometric.response.ChallengeResponse
import com.pthw.data.network.ktor.DataResponse
import com.pthw.data.network.biometric.response.CreateBiometricResponse
import com.pthw.data.network.biometric.response.SetupBiometricResponse
import com.pthw.data.network.ktor.DataEmptyResponse
import com.pthw.data.network.ktor.placeholders
import com.pthw.data.network.ktor.toKtor
import com.pthw.domain.model.SetupBiometricData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.parameters
import kotlinx.serialization.Serializable
import timber.log.Timber
import javax.inject.Inject


private const val SUB_CREATE_BIOMETRIC = "user/biometric"
private const val SUB_GET_CHALLENGE = "user/biometric/challenge/{id}"
private const val SUB_VALIDATE_SIGNATURE = "user/biometric/verify"

@Serializable
data class CreateBiometricRequest(
    val device_id: String,
    val public_key: String,
)

class BiometricService @Inject constructor(
    private val client: HttpClient,
    private val setupBiometricDataMapper: SetupBiometricDataMapper,
) {

    suspend fun createBiometric(deviceId: String, publicKey: String): SetupBiometricData {
        val endpoint = SUB_CREATE_BIOMETRIC.toKtor()
        val raw = client.post(endpoint) {
            setBody(
                CreateBiometricRequest(
                    device_id = deviceId,
                    public_key = publicKey
                )
            )
        }.body<DataResponse<SetupBiometricResponse>>()
        return setupBiometricDataMapper.map(raw.data)
    }

    suspend fun getChallenge(deviceId: String): String {
        val endpoint = SUB_GET_CHALLENGE.placeholders(mapOf("id" to deviceId)).toKtor()
        val raw = client.get(endpoint).body<DataResponse<ChallengeResponse>>()
        return raw.data?.challenge.toString()
    }

    suspend fun validateSignature(biometricId: String, signature: String): String {
        val endpoint = SUB_VALIDATE_SIGNATURE.toKtor()
        return client.submitForm(
            url = endpoint,
            formParameters = parameters {
                append("biometric_id", biometricId)
                append("signature", signature)
            }
        ).body<DataResponse<String>>().data.toString()
    }

}