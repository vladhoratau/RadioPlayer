package com.example.radioplayer.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radioplayer.core.GetRadioStationByRPUIDUseCase
import com.example.radioplayer.core.GetRadioStationsByCountryCodeUseCase
import com.example.radioplayer.domain.RadioStationData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/** The viewModel used in MainActivity (later in RadioStationsFragment) providing a list of radio stations from a specific country*/
@HiltViewModel
class RadioStationViewModel @Inject constructor(
    private val getRadioStationsByCountryCodeUseCase: GetRadioStationsByCountryCodeUseCase,
    private val getRadioStationByRPUIDUseCase: GetRadioStationByRPUIDUseCase
) : ViewModel() {

    val radioStationsLiveData = MutableLiveData<List<RadioStationData>?>()

    fun getRadioStationsByCountryCode(countryCode: Int) = viewModelScope.launch {
        getRadioStationsByCountryCodeUseCase.invoke(countryCode).let {
            if (it.isSuccessful) {
                Log.d(TAG, "Response successful with ${it.body()?.data?.size} + results")
                Log.d(TAG, "Results ${it.body()?.data.toString()}")
                radioStationsLiveData.postValue(it.body()?.data)
            } else {
                Log.d(
                    TAG,
                    "Radio stations request failed with ${it.message()} with error ${it.errorBody()}"
                )
                radioStationsLiveData.postValue(null)
            }
        }
    }

    fun getRadioStaionByRPUID(rpuid: String) = viewModelScope.launch {
        getRadioStationByRPUIDUseCase.invoke(rpuid).let {
            if (it.isSuccessful){
                Log.d(TAG, "Response successful with ${it.body()?.data?.size} + results by ID")
                Log.d(TAG, "Results by ID  ${it.body()?.data.toString()}")
                radioStationsLiveData.postValue(it.body()?.data)
            } else {
                Log.d(
                    TAG,
                    "Radio stations request BY ID failed with ${it.message()} with error ${it.errorBody()}"
                )
                radioStationsLiveData.postValue(null)
            }
        }
    }

    companion object {
        private val TAG = RadioStationViewModel::class.simpleName
    }
}