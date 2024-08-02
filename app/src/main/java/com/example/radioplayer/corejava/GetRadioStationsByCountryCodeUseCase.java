package com.example.radioplayer.corejava;


import com.example.radioplayer.domainjava.RadioStationsResponse;
import com.example.radioplayer.infrajava.RadioStationRepo;

import retrofit2.Response;
import javax.inject.Inject;

/**
 * Get radio stations by a specific country code usecase.
 */
public class GetRadioStationsByCountryCodeUseCase {

    private final RadioStationRepo radioStationRepo;

    @Inject
    public GetRadioStationsByCountryCodeUseCase(RadioStationRepo radioStationRepo) {
        this.radioStationRepo = radioStationRepo;
    }

    public Response<RadioStationsResponse> execute(int country) {
        // Assuming this method is called from a coroutine or asynchronous context
        // In a real implementation, you may need to handle async execution differently
        return radioStationRepo.getStationsByCountryCode(country);
    }
}

