package com.applaunch.bgm.viewmodel.fragment.challenge

import android.os.Bundle
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.state.fragment.challenge.StepChallengeState
import doNothing

class StepChallengeViewModel: BaseViewModel<StepChallengeState>() {

    var stepChallengeState : StepChallengeState = StepChallengeState.Init
        set(value) {
            field= value
            publishState(stepChallengeState)
        }

    override fun onInitialized(bundle: Bundle?) {
        doNothing()
    }
}