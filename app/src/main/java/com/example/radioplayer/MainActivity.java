package com.example.radioplayer;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.radioplayer.corejava.GetRadioStationByRPUIDUseCase;
import com.example.radioplayer.corejava.GetRadioStationsByCountryCodeUseCase;
import com.example.radioplayer.domainjava.RadioStationData;
import com.example.radioplayer.infrajava.RadioStationRepo;
import com.example.radioplayer.infrajava.RadioStationService;
import com.example.radioplayer.infrajava.RadioStationsRepoImpl;
import com.example.radioplayer.infrajava.RetrofitHelper;
import com.example.radioplayer.uijava.RadioStationViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    RadioStationService radioStationService;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure you set your layout here

        RadioStationViewModel radioStationViewModel = new ViewModelProvider(this).get(RadioStationViewModel.class);

        //radioStationViewModel.getRadioStationsByCountryCode(250);
        radioStationViewModel.getRadioStationByRPUID("2501341");

        radioStationViewModel.getRadioStationsLiveData().observe(this, new Observer<List<RadioStationData>>() {
            @Override
            public void onChanged(List<RadioStationData> radioStations) {
                if (radioStations != null && !radioStations.isEmpty()) {
                    Log.d(TAG, "Radio name " + radioStations.get(0).getName() + " + " + radioStations.get(0).getCountry());
                }
            }
        });
    }

    private static final String TAG = MainActivity.class.getSimpleName();
}