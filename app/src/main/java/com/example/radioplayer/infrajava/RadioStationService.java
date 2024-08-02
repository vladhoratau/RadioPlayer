package com.example.radioplayer.infrajava;

import com.example.radioplayer.domainjava.RadioStationsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Response;

/** Radio service interface with the main API requests */
public interface RadioStationService {

    @GET("v2/stations/{rpuids}")
    Call<Response<RadioStationsResponse>> getRadioByRpuid(
            @Path("rpuids") String rpuids
    );

    @GET("v2/stations")
    Response<RadioStationsResponse> getStationsByCountryCode(
            @Query("country") int country
    );

    @GET("v2/stations")
    Call<Response<RadioStationsResponse>> getAllStations();
}
