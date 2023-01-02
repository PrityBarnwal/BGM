package com.applaunch.bgm.viewmodel.fragment.favorite

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.model_base.State
import com.applaunch.appbase.utils_base.Print
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.model.request.favorite.EditCollectionRequest
import com.applaunch.bgm.network.NetworkRepository
import com.applaunch.bgm.state.fragment.favorite.EditFragmentState
import doNothing
import kotlinx.coroutines.launch

class EditCollectionViewModel:BaseViewModel<EditFragmentState>() {
     var editCollectionRequest = EditCollectionRequest()
    var collectionId: String=""
    var deleteCollectionId: String = ""
    var editFragmentState: EditFragmentState = EditFragmentState.Init
        set(value) {
            field = value
            publishState(editFragmentState)
        }

    override fun onInitialized(bundle: Bundle?) {
        doNothing()
    }
    fun updateCollection(baseRepoListener: BaseRepoListener) {
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).updateCollection(collectionId,editCollectionRequest).collect {
                when (it) {
                    is State.Success -> {
                        Print.log("Add Collection Response ${it.data}")
                        editFragmentState = EditFragmentState.SUCCESS(it.data.message)
                    }
                    is State.Error -> {
                        Print.log("home Error Response ${it.message}")
                        editFragmentState = EditFragmentState.ERROR(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }
    fun deleteEditCollection(baseRepoListener: BaseRepoListener){
        viewModelScope.launch {
            NetworkRepository(baseRepoListener).deleteCollection(deleteCollectionId).collect{
                when(it){
                    is State.Success ->{
                        editFragmentState = EditFragmentState.DeleteCollectionSuccess(it.data)
                    }
                    is State.Error -> {
                        editFragmentState = EditFragmentState.DeleteCollectionError(it.message)
                    }
                    else -> {
                        doNothing()
                    }
                }
            }
        }
    }
}