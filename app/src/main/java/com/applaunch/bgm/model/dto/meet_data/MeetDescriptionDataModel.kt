package com.applaunch.bgm.model.dto.meet_data

import com.google.gson.annotations.SerializedName

data class MeetDescriptionDataModel(
    @SerializedName("type")
    val type:String,
    @SerializedName("content")
    val content:String,
    @SerializedName("_id")
    val _id:String
)
