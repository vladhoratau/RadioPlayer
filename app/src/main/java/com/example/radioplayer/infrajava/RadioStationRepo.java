package com.example.radioplayer.infrajava;

import com.example.radioplayer.domainjava.RadioStationsResponse;

import retrofit2.Call;
import retrofit2.Response;


/** Radio station repo interface with methods for specific operations */
public interface RadioStationRepo {
    Response<RadioStationsResponse> getStationsByCountryCode(int country);

    Call<Response<RadioStationsResponse>> getStationByRPUID(String rpuid);
}

