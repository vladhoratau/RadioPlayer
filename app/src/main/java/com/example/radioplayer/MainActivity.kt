package com.example.radioplayer

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.radioplayer.infra.RadioStationService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var radioStationService: RadioStationService

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchAllStations()
        //fetchStationsByCountry(250)

        //Not working
        //fetchStationsById(listOf("2502819"))
    }

    private fun fetchStationsByCountry(countryCode: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = radioStationService.getStationsByCountryCode(
                    "",
                    countryCode
                )
                if (response.isSuccessful) {
                    val stations = response.body()
                    Log.d("Vlad", "Stations: $stations")
                } else {
                    Log.e("Vlad", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("Vlad", "Failed to fetch stations: ${e.message}")
            }
        }
    }

    private fun fetchAllStations() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = radioStationService.getAllStations(
                    ""
                )
                if (response.isSuccessful) {
                    val radioStationsResponse = response.body()
                    val stations = radioStationsResponse?.data
                    Log.d("Vlad", "Stations: $stations")
                } else {
                    Log.e("Vlad", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("Vlad", "Failed to fetch stations: ${e.message}")
            }
        }
    }

    private fun fetchStationsById(id: List<String>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = radioStationService.getRadioByRpuid(
                    id
                )
                if (response.isSuccessful) {
                    val radioStationsResponse = response.body()
                    val stations = radioStationsResponse?.data
                    Log.d("Vlad", "Stations: $stations")
                } else {
                    Log.e("Vlad", "Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("Vlad", "Failed to fetch stations: ${e.message}")
            }
        }
    }
}
