package com.example.radioplayer.corejava;


import com.example.radioplayer.domainjava.RadioStationsResponse;
import com.example.radioplayer.infrajava.RadioStationRepo;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Get radio stations by a specific country code usecase.
 */
public class GetRadioStationsByCountryCodeUseCase {

    private final RadioStationRepo radioStationRepo;

    public GetRadioStationsByCountryCodeUseCase(RadioStationRepo radioStationRepo) {
        this.radioStationRepo = radioStationRepo;
    }

    public Call<RadioStationsResponse> execute(int country) {
        return radioStationRepo.getStationsByCountryCode(country);
    }
}

