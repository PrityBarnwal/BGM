package com.applaunch.bgm.viewmodel.fragment.home

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.adapter.recycler.adapter.WebinarAdapter
import com.applaunch.bgm.model.dto.webinar_data.WebinarDescriptionModel
import com.applaunch.bgm.network.NetworkRepository
import com.applaunch.bgm.state.fragment.home.WebinarState
import doNothing
import kotlinx.coroutines.launch

class WebinarViewModel :BaseViewModel<WebinarState>(){
    var webinarId:String=""
    lateinit var webinarAdapter: WebinarAdapter
    var webinarState: WebinarState = WebinarState.Init
        set(value) {
            field = value
            publishState(webinarState)
        }
    override fun onInitialized(bundle: Bundle?) {
        //do letter
    }
    fun getWebinar(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).webinar(webinarId).collect {
                when (it) {
                    is State.Success -> {
                        webinarState = WebinarState.SUCCESS(it.data.data)
                    }
                    is State.Error -> {
                        webinarState = WebinarState.ERROR(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }
    fun initWebinarList(dataList: ArrayList<WebinarDescriptionModel>) {
        webinarAdapter = WebinarAdapter(dataList, this)
        webinarState = WebinarState.WebinarList(webinarAdapter)
    }

}