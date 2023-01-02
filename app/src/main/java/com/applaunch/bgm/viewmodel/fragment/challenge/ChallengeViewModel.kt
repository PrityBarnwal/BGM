package com.applaunch.bgm.viewmodel.fragment.challenge

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.network.NetworkRepository
import com.applaunch.bgm.state.fragment.challenge.ChallengeState
import kotlinx.coroutines.launch

class ChallengeViewModel : BaseViewModel<ChallengeState>() {

    var challengeState : ChallengeState = ChallengeState.Init
        set(value) {
            field= value
            publishState(challengeState)
        }

    override fun onInitialized(bundle: Bundle?) {
        //do letter
    }
    fun checkChallenge(baseRepoListener: BaseRepoListener,type:String){
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).checkChallenge(type).collect{
                when(it){
                    is State.Success ->{
                        challengeState = ChallengeState.SUCCESS(it.data.data)
                    }
                    is State.Error ->{
                        challengeState = ChallengeState.ERROR(it.message)
                    }
                }
            }
        }
    }
}