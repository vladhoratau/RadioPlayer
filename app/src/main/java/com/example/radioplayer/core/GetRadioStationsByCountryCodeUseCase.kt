//package com.example.radioplayer.core
//
//import com.example.radioplayer.domain.RadioStationsResponse
//import com.example.radioplayer.infra.RadioStationRepo
//import retrofit2.Response
//import javax.inject.Inject
//
///** Get radio stations by a specific country code usecase */
//class GetRadioStationsByCountryCodeUseCase @Inject constructor(private val radioStationRepo: RadioStationRepo) {
//    suspend operator fun invoke(
//        country: Int,
//    ): Response<RadioStationsResponse> {
//        return radioStationRepo.getStationsByCountryCode(country)
//    }
//}