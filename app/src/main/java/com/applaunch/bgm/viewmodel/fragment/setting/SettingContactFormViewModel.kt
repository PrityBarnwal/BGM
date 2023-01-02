package com.applaunch.bgm.viewmodel.fragment.setting

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.model.request.setting.ContactFormRequest
import com.applaunch.bgm.network.NetworkRepository
import com.applaunch.bgm.state.fragment.settings.SettingContactFormState
import doNothing
import kotlinx.coroutines.launch

class SettingContactFormViewModel:BaseViewModel<SettingContactFormState>() {
     var contactFormRequest = ContactFormRequest()

    var settingContactFormState : SettingContactFormState = SettingContactFormState.Init
    set(value) {
        field= value
        publishState(settingContactFormState)
    }
    override fun onInitialized(bundle: Bundle?) {
        doNothing()
    }

    fun contactForm(baseRepoListener: BaseRepoListener){
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).contactForm(contactFormRequest).collect{
                when(it){
                    is State.Success ->{
                        settingContactFormState = SettingContactFormState.SUCCESS(it.data)
                    }
                    is State.Error ->{
                        settingContactFormState = SettingContactFormState.ERROR(it.message)
                    }
                }
            }
        }
    }
}