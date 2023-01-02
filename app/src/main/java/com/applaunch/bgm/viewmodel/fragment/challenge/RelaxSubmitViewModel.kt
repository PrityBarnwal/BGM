package com.applaunch.bgm.viewmodel.fragment.challenge

import android.os.Bundle
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.state.fragment.challenge.RelaxSubmitState
import doNothing

class RelaxSubmitViewModel : BaseViewModel<RelaxSubmitState>() {
    var relaxSubmitState: RelaxSubmitState = RelaxSubmitState.Init
        set(value) {
            field = value
            publishState(relaxSubmitState)
        }

    override fun onInitialized(bundle: Bundle?) {
        doNothing()
    }
}