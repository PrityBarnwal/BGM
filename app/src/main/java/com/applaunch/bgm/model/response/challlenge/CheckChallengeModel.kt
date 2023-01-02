package com.applaunch.bgm.model.response.challlenge

import com.applaunch.appbase.model_base.BaseResponse
import com.applaunch.bgm.model.dto.challenge_data.CheckChallengeDataModel
import com.google.gson.annotations.SerializedName

data class CheckChallengeModel(
    @SerializedName("data")
    val data: CheckChallengeDataModel
) : BaseResponse()