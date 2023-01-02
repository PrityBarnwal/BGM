package com.applaunch.bgm.model.dto.favorite_data

import com.google.gson.annotations.SerializedName

data class FavoriteDataModel(
    @SerializedName("_id")
    val _id:String,
    @SerializedName("collectionName")
    val collectionName:String,
    @SerializedName("favoriteDate")
    val favoriteDate:String,
    @SerializedName("coverImage")
    val coverImage:String,
)