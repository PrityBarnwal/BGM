package com.applaunch.bgm.model.dto.article_data

import com.google.gson.annotations.SerializedName

data class ArticleDataModel(
    @SerializedName("_id")
    val id: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: ArrayList<ArticleDescriptionModel>,
    @SerializedName("writtenBy")
    val writtenBy: String = "",
    @SerializedName("date")
    val date: String = "",
    @SerializedName("countLikes")
    val countLikes: Int = 0,
    @SerializedName("isLike")
    val isLike: Boolean = false,
    @SerializedName("isFavorite")
    val isFavorite: Boolean = false,
)
