package com.applaunch.bgm.state.fragment.challenge

import com.applaunch.appbase.model_base.BaseResponse

sealed class StepSubmitState {
    object Init:StepSubmitState()
    data class SUCCESS(val data:BaseResponse):StepSubmitState()
    data class ERROR(val msg:String):StepSubmitState()
}