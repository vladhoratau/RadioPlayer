package com.example.radioplayer.corejava;


import com.example.radioplayer.domainjava.RadioStationsResponse;
import com.example.radioplayer.infrajava.RadioStationRepo;

import retrofit2.Call;
import retrofit2.Response;
import javax.inject.Inject;

/**
 * Get radio stations by a specific rpuid usecase.
 */
public class GetRadioStationByRPUIDUseCase {

    private final RadioStationRepo radioStationRepo;

    @Inject
    public GetRadioStationByRPUIDUseCase(RadioStationRepo radioStationRepo) {
        this.radioStationRepo = radioStationRepo;
    }

    public Call<Response<RadioStationsResponse>> execute(String rpuid) {
        // Assuming this method is called from a coroutine or asynchronous context
        // In a real implementation, you may need to handle async execution differently
        return radioStationRepo.getStationByRPUID(rpuid);
    }
}
