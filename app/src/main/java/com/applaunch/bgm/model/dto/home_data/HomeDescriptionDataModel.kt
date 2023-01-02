package com.applaunch.bgm.model.dto.home_data

import com.google.gson.annotations.SerializedName

data class HomeDescriptionDataModel(
    @SerializedName("type")
    val type:String,

    @SerializedName("content")
    val content:String,

    @SerializedName("_id")
    val _id:String
)
