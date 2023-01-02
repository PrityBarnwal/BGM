package com.applaunch.bgm.model.response.setting_model

import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.bgm.model.dto.setting_data.ContactSupportDataModel
import com.google.gson.annotations.SerializedName

data class ContactSupportModel(
    @SerializedName("data")
    val data: ContactSupportDataModel
) : BaseResponse()