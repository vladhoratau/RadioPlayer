package com.example.radioplayer.infrajava;

import com.example.radioplayer.domainjava.RadioStationsResponse;
import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/** Implementation of Radio station repo class */
public class RadioStationsRepoImpl implements RadioStationRepo {

    private final RadioStationService radioStationService;

    @Inject
    public RadioStationsRepoImpl(RadioStationService radioStationService) {
        this.radioStationService = radioStationService;
    }

    @Override
    public Response<RadioStationsResponse> getStationsByCountryCode(int country) {
        return radioStationService.getStationsByCountryCode(country);
    }

    @Override
    public Call<Response<RadioStationsResponse>> getStationByRPUID(String rpuid) {
        return radioStationService.getRadioByRpuid(rpuid);
    }
}
