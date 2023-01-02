package com.applaunch.bgm.model.response.webinar_model

import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.bgm.model.dto.webinar_data.WebinarDataModel
import com.google.gson.annotations.SerializedName

data class WebinarModel(
    @SerializedName("data")
    val data: WebinarDataModel
):BaseResponse()
