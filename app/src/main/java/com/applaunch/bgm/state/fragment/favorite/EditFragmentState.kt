package com.applaunch.bgm.state.fragment.favorite

import com.applaunch.appbase.model_base.BaseResponse

sealed class EditFragmentState {
    object Init:EditFragmentState()
    data class ERROR(val msg: String) : EditFragmentState()
    data class SUCCESS(val msg: String) : EditFragmentState()
    //delete Collection
    data class DeleteCollectionSuccess(val data: BaseResponse) : EditFragmentState()
    data class DeleteCollectionError(val msg: String) : EditFragmentState()
}