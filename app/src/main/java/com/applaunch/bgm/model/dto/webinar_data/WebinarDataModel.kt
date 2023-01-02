package com.applaunch.bgm.model.dto.webinar_data

import com.google.gson.annotations.SerializedName

data class WebinarDataModel(
    @SerializedName("_id")
    val _id:String="",
    @SerializedName("image")
    val image:String="",
    @SerializedName("title")
    val title:String="",
    @SerializedName("streamType")
    val streamType:String="",
    @SerializedName("link")
    val link:String="",
    @SerializedName("date", alternate = ["createdAt"])
    val date: String="",
    @SerializedName("description")
    val description:ArrayList<WebinarDescriptionModel>,
    @SerializedName("speakers")
    val speakers:ArrayList<String> = arrayListOf(),
    @SerializedName("totalLikes")
    val totalLikes:Int=0,
    @SerializedName("isLike")
    val isLike:Boolean=false,
    @SerializedName("isFavorite")
    val isFavorite:Boolean=false,
)
