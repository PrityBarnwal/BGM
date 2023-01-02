package com.applaunch.bgm.viewmodel.fragment.account_activated

import android.os.Bundle
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.state.account_activated.AccountActivatedState
import doNothing

class AccountActivatedViewModel : BaseViewModel<AccountActivatedState>() {

    var activatedState: AccountActivatedState = AccountActivatedState.Init
        set(value) {
            field = value
            publishState(activatedState)
        }

    override fun onInitialized(bundle: Bundle?) {
        doNothing()
    }


    fun navigateToHome(){
        activatedState = AccountActivatedState.NavigateToHome
    }

}