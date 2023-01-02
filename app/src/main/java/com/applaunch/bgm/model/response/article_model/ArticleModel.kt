package com.applaunch.bgm.model.response.article_model

import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.bgm.model.dto.article_data.ArticleDataModel
import com.google.gson.annotations.SerializedName

data class ArticleModel(
    @SerializedName("data")
    val data: ArticleDataModel
):BaseResponse()
