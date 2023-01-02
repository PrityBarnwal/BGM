package com.applaunch.bgm.viewmodel.fragment.challenge

import android.os.Bundle
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.state.fragment.challenge.RelaxationChallengeState
import doNothing

class RelaxationChallengeViewModel: BaseViewModel<RelaxationChallengeState>() {

    var relaxationChallengeState : RelaxationChallengeState = RelaxationChallengeState.Init
        set(value) {
            field= value
            publishState(relaxationChallengeState)
        }

    override fun onInitialized(bundle: Bundle?) {
        doNothing()
    }
}