package com.pthw.data.network.ktor

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(
    @SerialName(value = "data") val data: T?,
    @SerialName(value = "message") val errorMessage: String?,
    @SerialName(value = "success") val success: @Polymorphic Any?
)

@Serializable
data class DataEmptyResponse(
    @SerialName(value = "message") val message: String?
)

@Serializable
data class PageResponse<T>(
    @SerialName(value = "meta") val pageMeta: PageMeta?,
    @SerialName(value = "data") val data: List<T>?,
)

@Serializable
data class PageMeta(
    @SerialName(value = "size") val size: Int?,
    @SerialName(value = "page") val currentPage: Int?,
    @SerialName(value = "total_pages") val totalPage: Int?,
    @SerialName(value = "total_count") val totalCount: Int?
)
