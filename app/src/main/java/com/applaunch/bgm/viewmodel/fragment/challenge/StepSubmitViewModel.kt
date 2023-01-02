package com.applaunch.bgm.viewmodel.fragment.challenge

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.model.request.challenge.EnterStepRequest
import com.applaunch.bgm.network.NetworkRepository
import com.applaunch.bgm.state.fragment.challenge.StepSubmitState
import doNothing
import kotlinx.coroutines.launch

class StepSubmitViewModel : BaseViewModel<StepSubmitState>() {
    var enterStepRequest = EnterStepRequest()
    var stepSubmitState: StepSubmitState = StepSubmitState.Init
        set(value) {
            field = value
            publishState(stepSubmitState)
        }

    override fun onInitialized(bundle: Bundle?) {
        doNothing()
    }

    fun enterSteps(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).enterSteps(enterStepRequest).collect{
                when(it){
                    is State.Success->{
                        stepSubmitState = StepSubmitState.SUCCESS(it.data)
                    }
                    is State.Error->{
                        stepSubmitState = StepSubmitState.ERROR(it.message)
                    }
                }

            }
        }
    }

}