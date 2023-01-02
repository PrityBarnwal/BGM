package com.applaunch.bgm.state.fragment.splash

sealed class SplashState{
    object Init:SplashState()
    object NavigateToOnboarding:SplashState()
    object NavigateToHome:SplashState()
}
