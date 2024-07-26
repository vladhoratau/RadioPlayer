package com.example.radioplayer.core

import com.example.radioplayer.domain.RadioStationsResponse
import com.example.radioplayer.infra.RadioStationRepo
import retrofit2.Response
import javax.inject.Inject

/** Get radio stations by a specific rpuid usecase */
class GetRadioStationByRPUIDUseCase @Inject constructor(private val radioStationRepo: RadioStationRepo) {
    suspend operator fun invoke(
        rpuid: String
    ): Response<RadioStationsResponse> {
        return radioStationRepo.getStationByRPUID(rpuid)
    }
}