package com.applaunch.bgm.model.dto.favorite_data

import com.google.gson.annotations.SerializedName

data class FavoriteListDataModel (
    @SerializedName("_id")
    val id:ItemIdDataModel,
    @SerializedName("collectionName")
    val collectionName:String,
    @SerializedName("date")
    val date:String,
    @SerializedName("image")
    val image:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("video")
    val video:String,
    @SerializedName("streamType")
    val streamType:String,
    @SerializedName("isfavorites")
    var isfavorites:Boolean,
    @SerializedName("countLikes")
    var countLikes:Int,
    @SerializedName("isLike")
    var isLike:Boolean,
    @SerializedName("webinarLink")
    val webinarLink:String,
    @SerializedName("webStartDate")
    val webStartDate:String,
    @SerializedName("webEndDate")
    val webEndDate:String,
    @SerializedName("writtenBy")
    val writtenBy:String,
    @SerializedName("articleDate")
    val articleDate:String,
    @SerializedName("description")
    val description:FavoriteDescriptionDataModel,
    var bgSelected:Boolean= false,
)