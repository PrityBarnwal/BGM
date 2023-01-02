package com.applaunch.bgm.model.request.favorite

import com.google.gson.annotations.SerializedName

data class FavoriteListRequest(
    @SerializedName("keyword")
    var keyword:String=""
)
