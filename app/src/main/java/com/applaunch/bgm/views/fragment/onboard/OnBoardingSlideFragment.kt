package com.applaunch.bgm.views.fragment.onboard

import androidx.fragment.app.viewModels
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.OnBoardingSlideBinding
import com.applaunch.bgm.state.fragment.OnBoardState
import com.applaunch.bgm.viewmodel.fragment.onboard.OnBoardViewModel

class OnBoardingSlideFragment : BaseFragment<OnBoardViewModel, OnBoardingSlideBinding>() {

    override val mViewModel: OnBoardViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_onboarding_slide

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this) {
            when (it) {
                is OnBoardState.UpdateOnBoardData -> {
                    mViewBinding.position = it.pos
                }
            }
        }
    }

    override fun onFragmentCreated() {
        //do letter
    }
}