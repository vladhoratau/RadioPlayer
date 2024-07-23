package com.example.radioplayer.core

import com.example.radioplayer.domain.RadioStationData
import com.example.radioplayer.infra.RadioStationRepo
import retrofit2.Response
import javax.inject.Inject

class GetRadioStationsByCountryCodeUseCase @Inject constructor(private val radioStationRepo: RadioStationRepo) {
//    suspend operator fun invoke(
//        authHeader: String,
//        stationId: String,
//        include: String
//    ): Response<List<RadioStationData>> {
//        return radioStationRepo.getStationsByCountryCode()
//    }
}