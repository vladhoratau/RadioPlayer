package com.example.radioplayer.infra

import com.example.radioplayer.domain.RadioStationsResponse
import retrofit2.Response
import javax.inject.Inject

/** Radio station repo interface with methods for specific operations */
interface RadioStationRepo {
    suspend fun getStationsByCountryCode(
        authHeader: String,
        country: Int,
        include: String? = null
    ): Response<RadioStationsResponse>
}

/**Implementation of Radio station repo class */
class RadioStationsRepoImpl @Inject constructor(private val radioStationService: RadioStationService) :
    RadioStationRepo {
    override suspend fun getStationsByCountryCode(
        authHeader: String,
        country: Int,
        include: String?
    ): Response<RadioStationsResponse> {
        return radioStationService.getStationsByCountryCode(authHeader, country, include)
    }

}
