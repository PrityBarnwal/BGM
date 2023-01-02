package com.applaunch.bgm.viewmodel.fragment.access_onboard

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.utils_base.Print
import com.applaunch.appbase.utils_base.session.SessionManager
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.model.dto.auth_data.AuthDataModel
import com.applaunch.bgm.model.request.login.AuthRequest
import com.applaunch.bgm.network.NetworkRepository
import com.applaunch.bgm.state.access_onboard.AccessOnboardState
import com.applaunch.bgm.utils.session.SessionConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccessOnBoardViewModel:BaseViewModel<AccessOnboardState>() {

    val authRequest = AuthRequest()

    var accessOnboardState: AccessOnboardState = AccessOnboardState.Init
    set(value) {
        field = value
        publishState(accessOnboardState)
    }
    override fun onInitialized(bundle: Bundle?) {
        //do letter
    }


    fun login(baseRepoListener: BaseRepoListener){
        Print.log(authRequest.toString())
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).login(authRequest).collect{
                when(it){
                    is State.Success -> {
                        Print.log("Auth Success Response ${it.data}")
                       accessOnboardState =  AccessOnboardState.SUCCESS(it.data)

                    }
                    is State.Error -> {
                        Print.log("Auth Error Response ${it.message}")
                        accessOnboardState = AccessOnboardState.ERROR(it.message)
                    }
                }
            }
        }
    }


    fun saveUserData(session: SessionManager, data: AuthDataModel) {
        viewModelScope.launch(Dispatchers.IO) {
            session.setPreference(SessionConstant.ACCESS_TOKEN, data.accessToken)
            session.setPreference(SessionConstant.REFRESH_TOKEN, data.refreshToken)
            session.setPreference(SessionConstant.COMPANY_LOGO, data.companyLogo)
            session.setPreference(SessionConstant.COMPANY_COLOR, data.companyColorCode)
        }

        accessOnboardState = AccessOnboardState.NavigateToAccountActivated

    }
}