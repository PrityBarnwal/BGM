package com.applaunch.bgm.model.request.login

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class AuthRequest(
     accessCode:String = "",
     deviceToken:String = "154878"
):BaseObservable(){

    @get:Bindable
    var accessCode = accessCode
    set(value) {

        if (value!=accessCode){
            field = value
            notifyChange()
        }
    }

    @get:Bindable
    var deviceToken = deviceToken
    set(value) {
        if (value!=deviceToken){
            field = value
            notifyChange()
        }
    }

    override fun toString(): String {
        return "Acces Code $accessCode and Device Token $deviceToken"
    }
}
