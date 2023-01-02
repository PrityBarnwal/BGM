package com.applaunch.bgm.viewmodel.fragment.setting

import android.os.Bundle
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.state.fragment.settings.DataPrivacyState
import doNothing

class DataPrivacyViewModel:BaseViewModel<DataPrivacyState>() {

    var dataPrivacyState: DataPrivacyState = DataPrivacyState.Init
        set(value) {
            field = value
            publishState(dataPrivacyState)
        }
    override fun onInitialized(bundle: Bundle?) {
        doNothing()
    }
}