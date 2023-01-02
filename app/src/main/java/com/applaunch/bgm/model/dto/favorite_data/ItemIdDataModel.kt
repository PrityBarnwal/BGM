package com.applaunch.bgm.model.dto.favorite_data

import com.google.gson.annotations.SerializedName

data class ItemIdDataModel(
    @SerializedName("favoriteId")
    val favoriteId:String,
    @SerializedName("collectionId")
    val collectionId:String,
    @SerializedName("videoId", alternate = ["articleId","webinarId"])
    val streamId:String
)