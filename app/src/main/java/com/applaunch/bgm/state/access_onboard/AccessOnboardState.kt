package com.applaunch.bgm.state.access_onboard

import com.applaunch.bgm.model.response.auth_model.AuthModel

sealed class AccessOnboardState{
    object Init :AccessOnboardState()
    data class SUCCESS(val data:AuthModel):AccessOnboardState()
    data class ERROR(val msg:String):AccessOnboardState()
    object NavigateToAccountActivated:AccessOnboardState()
}
