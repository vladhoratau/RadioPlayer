package com.example.radioplayer.uijava;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.radioplayer.corejava.GetRadioStationByRPUIDUseCase;
import com.example.radioplayer.corejava.GetRadioStationsByCountryCodeUseCase;
import com.example.radioplayer.domainjava.RadioStationData;
import com.example.radioplayer.domainjava.RadioStationsResponse;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Response;

/**
 * The ViewModel used in MainActivity (later in RadioStationsFragment)
 * providing a list of radio stations from a specific country.
 */
@HiltViewModel
public class RadioStationViewModel extends ViewModel {

    private final GetRadioStationsByCountryCodeUseCase getRadioStationsByCountryCodeUseCase;
    private final GetRadioStationByRPUIDUseCase getRadioStationByRPUIDUseCase;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final MutableLiveData<List<RadioStationData>> radioStationsLiveData = new MutableLiveData<>();


    @Inject
    public RadioStationViewModel(GetRadioStationsByCountryCodeUseCase getRadioStationsByCountryCodeUseCase,
                                 GetRadioStationByRPUIDUseCase getRadioStationByRPUIDUseCase) {
        this.getRadioStationsByCountryCodeUseCase = getRadioStationsByCountryCodeUseCase;
        this.getRadioStationByRPUIDUseCase = getRadioStationByRPUIDUseCase;
    }

    public MutableLiveData<List<RadioStationData>> getRadioStationsLiveData() {
        return radioStationsLiveData;
    }

    public void getRadioStationByRPUID(String rpuid) {
        executorService.submit(() -> {
            try {
                Call<RadioStationsResponse> call = getRadioStationByRPUIDUseCase.execute(rpuid);

                if (call == null) {
                    Log.e(TAG, "Retrofit call is null. Check getRadioStationByRPUIDUseCase implementation.");
                    radioStationsLiveData.postValue(null);
                    return;
                }

                Response<RadioStationsResponse> response = call.execute();

                if (response != null && response.isSuccessful()) {
                    Log.d(TAG, "Response successful with " + response.body().getData().size() + " results by ID");
                    Log.d(TAG, "Results by ID " + response.body().getData().toString());
                    radioStationsLiveData.postValue(response.body().getData());
                } else {
                    Log.e(TAG, "Radio stations request BY ID failed with " + response.message() +
                            " with error " + (response.errorBody() != null ? response.errorBody().string() : "No error body"));
                    radioStationsLiveData.postValue(null);
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException while fetching radio station by RPUID", e);
                radioStationsLiveData.postValue(null);
            } catch (Exception e) {
                Log.e(TAG, "Error fetching radio station by RPUID", e);
                radioStationsLiveData.postValue(null);
            }
        });
    }


    public void getRadioStationsByCountryCode(int countryCode) {
        executorService.submit(() -> {
            try {
                Call<RadioStationsResponse> call = getRadioStationsByCountryCodeUseCase.execute(countryCode);

                if (call == null) {
                    Log.e(TAG, "Retrofit call is null. Check getRadioStationsByCountryCodeUseCase implementation.");
                    radioStationsLiveData.postValue(null);
                    return;
                }

                Response<RadioStationsResponse> response = call.execute();

                if (response != null && response.isSuccessful()) {
                    Log.d(TAG, "Response successful with " + response.body().getData().size() + " results");
                    Log.d(TAG, "Results " + response.body().getData().toString());
                    radioStationsLiveData.postValue(response.body().getData());
                } else {
                    Log.e(TAG, "Radio stations request failed with " + response.message() +
                            " with error " + (response.errorBody() != null ? response.errorBody().string() : "No error body"));
                    radioStationsLiveData.postValue(null);
                }
            } catch (IOException e) {
                Log.e(TAG, "IOException while fetching radio stations by country code", e);
                radioStationsLiveData.postValue(null);
            } catch (Exception e) {
                Log.e(TAG, "Error fetching radio stations by country code", e);
                radioStationsLiveData.postValue(null);
            }
        });
    }

    private static final String TAG = RadioStationViewModel.class.getSimpleName();
}

