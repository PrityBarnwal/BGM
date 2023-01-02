package com.applaunch.bgm.viewmodel.fragment.setting

import android.os.Bundle
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.state.fragment.settings.ResetAppState
import doNothing

class ResetAppViewModel:BaseViewModel<ResetAppState>() {
    var resetAppState: ResetAppState = ResetAppState.Init
        set(value) {
            field = value
            publishState(resetAppState)
        }

    override fun onInitialized(bundle: Bundle?) {
        doNothing()
    }
    fun navigateToSplash(){
        resetAppState = ResetAppState.NavigateSplash
    }
}