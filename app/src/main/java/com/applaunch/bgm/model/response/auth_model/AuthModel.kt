package com.applaunch.bgm.model.response.auth_model

import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.bgm.model.dto.auth_data.AuthDataModel
import com.google.gson.annotations.SerializedName

data class AuthModel(
    @SerializedName("data")
    val data: AuthDataModel
) : BaseResponse()