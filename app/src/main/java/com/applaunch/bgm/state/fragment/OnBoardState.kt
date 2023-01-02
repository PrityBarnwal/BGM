package com.applaunch.bgm.state.fragment

sealed class OnBoardState {
    object Init :OnBoardState()
    data class UpdateOnBoardData(val pos:Int):OnBoardState()
    object NavigateToAccess:OnBoardState()

}