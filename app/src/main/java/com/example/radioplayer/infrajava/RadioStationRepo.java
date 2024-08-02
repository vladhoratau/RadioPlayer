package com.example.radioplayer.infrajava;

import com.example.radioplayer.domainjava.RadioStationsResponse;

import retrofit2.Call;


/**
 * Radio station repo interface with methods for specific operations
 */
public interface RadioStationRepo {
    Call<RadioStationsResponse> getStationsByCountryCode(int country);

    Call<RadioStationsResponse> getStationByRPUID(String rpuid);
}

