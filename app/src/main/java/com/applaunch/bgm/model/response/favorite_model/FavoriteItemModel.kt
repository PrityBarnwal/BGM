package com.applaunch.bgm.model.response.favorite_model

import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.bgm.model.dto.favorite_data.FavoriteListDataModel
import com.google.gson.annotations.SerializedName

data class FavoriteItemModel(
    @SerializedName("data")
    val data:ArrayList<FavoriteListDataModel>
):BaseResponse()