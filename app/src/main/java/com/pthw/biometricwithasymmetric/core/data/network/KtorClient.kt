package com.pthw.biometricwithasymmetric.core.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 15L
private const val READ_TIMEOUT = 60L
private const val WRITE_TIMEOUT = 15L

// Ktor BaseUrl
private const val KTOR_BASE_URL =
    "http://ec2-47-129-33-88.ap-southeast-1.compute.amazonaws.com:9090/"

// extension function to concat with baseUrl
fun String.toKtor() = "$KTOR_BASE_URL$this"

// ktor http client
@OptIn(ExperimentalSerializationApi::class)
val ktorHttpClient: (Interceptor) -> HttpClient = { interceptor ->
    HttpClient(OkHttp) {

        // okhttp engine config
        engine {
            config {
                connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                addInterceptor(interceptor)
            }
        }

        // kotlinx serialization
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                }
            )
        }

        // ktor logger
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) = Timber.tag("Ktor logger:").v(message)
            }
        }

        // ktor response observer
        install(ResponseObserver) {
            onResponse { response ->
                Timber.tag("HTTP status:").d("${response.status.value}")
            }
        }

        // request content-type
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

    }
}