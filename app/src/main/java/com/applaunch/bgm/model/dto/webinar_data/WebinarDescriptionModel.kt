package com.applaunch.bgm.model.dto.webinar_data

import com.google.gson.annotations.SerializedName

data class WebinarDescriptionModel(
    @SerializedName("type")
    val type: String = "",
    @SerializedName("content")
    val content: String = "",
    @SerializedName("_id")
    val id: String = ""
)
