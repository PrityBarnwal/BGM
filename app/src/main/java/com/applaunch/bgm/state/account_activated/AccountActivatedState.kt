package com.applaunch.bgm.state.account_activated

sealed class AccountActivatedState{
    object Init : AccountActivatedState()
    object NavigateToHome :AccountActivatedState()
}
