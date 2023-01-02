package com.applaunch.bgm.viewmodel.fragment.home

import android.os.Bundle
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.state.fragment.home.HomeVideoState

class HomeVideoViewModel:BaseViewModel<HomeVideoState>() {
    override fun onInitialized(bundle: Bundle?) {
        //do letter
    }
    var homeVideoState :HomeVideoState= HomeVideoState.Init
    set(value) {
        field = value
        publishState(homeVideoState)
    }
}