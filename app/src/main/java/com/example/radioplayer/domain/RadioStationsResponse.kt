package com.example.radioplayer.domain

import com.google.gson.annotations.SerializedName

/* Data class representing the entity of a Response received from the RadioPlayer api
 */
data class RadioStationsResponse(
    @SerializedName("data")
    val data: List<RadioStationData>,
    @SerializedName("meta")
    val meta: MetaData
)
