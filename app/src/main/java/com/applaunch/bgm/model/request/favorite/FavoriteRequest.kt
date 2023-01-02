package com.applaunch.bgm.model.request.favorite

import com.google.gson.annotations.SerializedName

data class FavoriteRequest(
    @SerializedName("collectionName")
    var collectionName:String=""
)
