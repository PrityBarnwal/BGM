package com.applaunch.bgm.model.dto.favorite_data

import com.google.gson.annotations.SerializedName

data class FavoriteDescriptionDataModel(
    @SerializedName("type")
    val type:String,
    @SerializedName("content")
    val content:String,
    @SerializedName("_id")
    val _id:String,
)
