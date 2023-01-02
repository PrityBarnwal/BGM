package com.applaunch.bgm.state.fragment.challenge

import com.applaunch.bgm.model.dto.challenge_data.CheckChallengeDataModel

sealed class ChallengeState{
    object Init:ChallengeState()
    data class SUCCESS(val data: CheckChallengeDataModel) : ChallengeState()
    data class ERROR(val msg: String) : ChallengeState()
}
