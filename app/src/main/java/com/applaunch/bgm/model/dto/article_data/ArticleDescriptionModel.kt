package com.applaunch.bgm.model.dto.article_data

import com.google.gson.annotations.SerializedName

data class ArticleDescriptionModel(
    @SerializedName("type")
    val type: String = "",
    @SerializedName("content")
    val content: String = "",
    @SerializedName("_id")
    val id: String = ""
)
