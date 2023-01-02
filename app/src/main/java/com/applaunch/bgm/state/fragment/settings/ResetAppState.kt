package com.applaunch.bgm.state.fragment.settings

sealed class ResetAppState{
    object Init:ResetAppState()
    object NavigateSplash:ResetAppState()
}
