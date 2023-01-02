package com.applaunch.bgm.viewmodel.fragment.setting

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.utils_base.Print
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.network.NetworkRepository
import com.applaunch.bgm.state.fragment.settings.SettingContactSupportState
import kotlinx.coroutines.launch

class SettingContactSupportViewModel:BaseViewModel<SettingContactSupportState>() {

    var settingContactSupportState: SettingContactSupportState = SettingContactSupportState.Init
        set(value) {
            field = value
            publishState(settingContactSupportState)
        }

    override fun onInitialized(bundle: Bundle?) {
        //do letter
    }

    fun contactSupport(baseRepoListener: BaseRepoListener){
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).contactSupport().collect{
                when(it){
                    is State.Success ->{
                        Print.log("ContactSupport Success Response ${it.data}")
                        settingContactSupportState =  SettingContactSupportState.SUCCESS(it.data)
                    }
                    is State.Error ->{
                        Print.log("ContactSupport Error Response ${it.message}")
                        settingContactSupportState = SettingContactSupportState.ERROR(it.message)
                    }
                }
            }

        }
    }
}