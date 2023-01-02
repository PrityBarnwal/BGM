package com.applaunch.bgm.model.response.favorite_model

import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.bgm.model.dto.favorite_data.FavoriteDataModel
import com.google.gson.annotations.SerializedName

data class FavoriteModel(
    @SerializedName("data")
    val data:ArrayList<FavoriteDataModel>
):BaseResponse()
