package com.applaunch.bgm.model.response.challlenge

import com.applaunch.appbase.model_base.BaseResponse
import com.google.gson.annotations.SerializedName

data class EnterStepModel(
    @SerializedName("data")
    val data:String
):BaseResponse()
