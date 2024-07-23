package com.example.radioplayer.domain

import com.google.gson.annotations.SerializedName

/* Data class representing the entity of a StreamRestriction
 */
data class BitRate(
    @SerializedName("target")
    val target: Int?
)