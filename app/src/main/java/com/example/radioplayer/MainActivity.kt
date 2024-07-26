package com.example.radioplayer

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.radioplayer.infra.RadioStationService
import com.example.radioplayer.ui.RadioStationViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var radioStationService: RadioStationService

    private val radioStationViewModel: RadioStationViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // radioStationViewModel.getRadioStationsByCountryCode(250)
        radioStationViewModel.getRadioStaionByRPUID("2501341")
        radioStationViewModel.radioStationsLiveData.observe(this) { radioStations ->
            Log.d(
                TAG,
                "Radio name ${radioStations?.get(0)?.name}  + ${radioStations?.get(0)?.country}"
            )
        }
    }

    companion object {
        private val TAG = MainActivity::class.simpleName
    }
}
