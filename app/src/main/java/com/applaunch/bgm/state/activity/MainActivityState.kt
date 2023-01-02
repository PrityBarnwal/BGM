package com.applaunch.bgm.state.activity

sealed class MainActivityState {
    object Init : MainActivityState()
    data class BottomNav(val isBottomNavVisible:Boolean):MainActivityState()
    data class BottomNavFragment(val fragId :Int):MainActivityState()
}