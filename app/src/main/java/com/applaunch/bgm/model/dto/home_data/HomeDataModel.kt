package com.applaunch.bgm.model.dto.home_data

import com.google.gson.annotations.SerializedName

data class HomeDataModel(
    @SerializedName("_id")
    val _id: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("streamType")
    val streamType: String?,

    @SerializedName("link")
    val link: String,

   /* @SerializedName("createdAt")
    val createdAt: String,*/

    @SerializedName("isLike")
    var isLike: Boolean,

    @SerializedName("isFavorite")
    var isFavorite: Boolean,

    @SerializedName("totalLikes")
    var totalLikes: Int,

    @SerializedName("writtenBy")
    val writtenBy: String,

    @SerializedName("description")
    val description: HomeDescriptionDataModel,

    @SerializedName("date", alternate = ["createdAt"])
    val date: String,

    @SerializedName("video")
    val video: String,

    @SerializedName("collectionId")
    val collectionId: String? = null,

    var bgSelected:Boolean = false
)
