package com.applaunch.bgm.model.response.home_model

import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.bgm.model.dto.home_data.HomeDataModel
import com.google.gson.annotations.SerializedName

data class HomeModel(
    @SerializedName("data")
    val data: ArrayList<HomeDataModel>
):BaseResponse()
