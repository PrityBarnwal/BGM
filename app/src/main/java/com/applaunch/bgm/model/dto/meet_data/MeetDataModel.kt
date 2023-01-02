package com.applaunch.bgm.model.dto.meet_data

import com.google.gson.annotations.SerializedName

data class MeetDataModel (
    @SerializedName("_id")
    val _id:String,
    @SerializedName("image")
    val image:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("streamType")
    val streamType:String,
    @SerializedName("webStartDate")
    val webStartDate:String,
    @SerializedName("webEndDate")
    val webEndDate:String,
    @SerializedName("link")
    val link:String,
    @SerializedName("date", alternate = ["createdAt"])
    val date:String,
    @SerializedName("totalLikes")
    var totalLikes:Int,
    @SerializedName("isLike")
    var isLike:Boolean,
    @SerializedName("isFavorite")
    var isFavorite:Boolean,
    @SerializedName("video")
    val video:String,
    @SerializedName("description")
    val description:MeetDescriptionDataModel,
    @SerializedName("writtenBy")
    val writtenBy:String,

    @SerializedName("collectionId")
    val collectionId: String,

    var bgSelected:Boolean = false,
        )