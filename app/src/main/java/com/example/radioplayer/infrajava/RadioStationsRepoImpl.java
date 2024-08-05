package com.example.radioplayer.infrajava;

import com.example.radioplayer.domainjava.RadioStationsResponse;

import retrofit2.Call;

/**
 * Implementation of Radio station repo class
 */
public class RadioStationsRepoImpl implements RadioStationRepo {

    private final RadioStationService radioStationService;

    public RadioStationsRepoImpl(RadioStationService radioStationService) {
        this.radioStationService = radioStationService;
    }

    @Override
    public Call<RadioStationsResponse> getStationsByCountryCode(int country) {
        return radioStationService.getStationsByCountryCode(country);
    }

    @Override
    public Call<RadioStationsResponse> getStationByRPUID(String rpuid) {
        return radioStationService.getRadioByRpuid(rpuid);
    }
}
