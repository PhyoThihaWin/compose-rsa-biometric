package com.pthw.data.network.biometric.mapper

import com.pthw.data.network.biometric.response.SetupBiometricResponse
import com.pthw.domain.model.CreateBiometricData
import com.pthw.domain.model.SetupBiometricData
import com.pthw.shared.extension.orZero
import com.pthw.shared.mapper.UnidirectionalSuspendMap
import javax.inject.Inject

/**
 * Created by P.T.H.W on 11/03/2024.
 */
class SetupBiometricDataMapper @Inject constructor() :
    UnidirectionalSuspendMap<SetupBiometricResponse?, SetupBiometricData> {
    override suspend fun map(item: SetupBiometricResponse?): SetupBiometricData {
        return SetupBiometricData(
            data = CreateBiometricData(
                id = item?.data?.id.orZero(),
                deviceId = item?.data?.deviceId.orEmpty(),
                publicKey = item?.data?.publicKey.orEmpty(),
                biometricId = item?.data?.biometricId.orEmpty()
            ),
            token = item?.token.orEmpty()
        )
    }
}