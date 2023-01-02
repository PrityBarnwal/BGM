package com.applaunch.bgm.viewmodel.fragment.splash

import android.os.Bundle
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.state.fragment.splash.SplashState
import com.applaunch.bgm.utils.Constant
import doNothing
import postDelayed


class SplashViewModel : BaseViewModel<SplashState>() {

    private var splashState: SplashState = SplashState.Init
        set(value) {
            field = value
            publishState(splashState)
        }

    override fun onInitialized(bundle: Bundle?) {
        doNothing()
    }


    fun checkIfUserLoggedIn(accessToken: String) {
        postDelayed(
            { getUserData(accessToken) },
            Constant.HandlerTime.splashTime
        )
    }


    private fun getUserData(accessToken: String) {
        splashState = if (accessToken.isNotEmpty()) {
            SplashState.NavigateToHome
        } else {
            SplashState.NavigateToOnboarding
        }
    }
}