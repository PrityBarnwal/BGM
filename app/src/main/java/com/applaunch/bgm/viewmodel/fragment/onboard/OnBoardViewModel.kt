package com.applaunch.bgm.viewmodel.fragment.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.applaunch.appbase.viewmodel_base.BaseViewModel
import com.applaunch.bgm.state.fragment.OnBoardState
import com.applaunch.bgm.utils.Constant
import com.applaunch.bgm.views.fragment.onboard.OnBoardingSlideFragment

class OnBoardViewModel : BaseViewModel<OnBoardState>() {

    private var onBoardState: OnBoardState = OnBoardState.Init
        set(value) {
            field = value
            publishState(onBoardState)
        }

    override fun onInitialized(bundle: Bundle?) {
        if (bundle != null && bundle.containsKey(Constant.BundleKey.position)){
            onBoardState = OnBoardState.UpdateOnBoardData(bundle[Constant.BundleKey.position] as Int)
        }
    }

    /**Passing respective position in bundle for each fragment**/
    fun getOnBoardSlider(position:Int):Fragment{
        val fragment = OnBoardingSlideFragment()
        val bundle = Bundle()
        bundle.putInt(Constant.BundleKey.position, position)
        fragment.arguments = bundle
        return fragment
    }

    fun navigateToAccess(){
        onBoardState = OnBoardState.NavigateToAccess

    }
}