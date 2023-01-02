package com.applaunch.bgm.model.dto.collection_list

import com.google.gson.annotations.SerializedName

data class CollectListData(
    @SerializedName("_id")
    val _id: String,

    @SerializedName("collectionName")
    val collectionName: String,

    @SerializedName("coverImage")
    val coverImage: String,

    )