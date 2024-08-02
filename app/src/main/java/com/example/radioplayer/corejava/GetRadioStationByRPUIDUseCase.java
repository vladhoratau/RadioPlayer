package com.example.radioplayer.corejava;


import com.example.radioplayer.domainjava.RadioStationsResponse;
import com.example.radioplayer.infrajava.RadioStationRepo;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Get radio stations by a specific rpuid usecase.
 */
public class GetRadioStationByRPUIDUseCase {

    private final RadioStationRepo radioStationRepo;

    @Inject
    public GetRadioStationByRPUIDUseCase(RadioStationRepo radioStationRepo) {
        this.radioStationRepo = radioStationRepo;
    }

    public Call<RadioStationsResponse> execute(String rpuid) {
        return radioStationRepo.getStationByRPUID(rpuid);
    }
}
