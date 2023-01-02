package com.applaunch.bgm.model.request.favorite

import com.google.gson.annotations.SerializedName

data class EditCollectionRequest(
    @SerializedName("collectionName")
    var collectionName:String = ""
)
