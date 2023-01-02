package com.applaunch.bgm.views.fragment.challenge

import android.view.View
import androidx.fragment.app.viewModels
import com.applaunch.appbase.utils_base.navigateTo
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentStepChallengeBinding
import com.applaunch.bgm.viewmodel.fragment.challenge.StepChallengeViewModel

class StepChallengeFragment : BaseFragment<StepChallengeViewModel, FragmentStepChallengeBinding>() {
    override val layoutId: Int = R.layout.fragment_step_challenge
    override val mViewModel: StepChallengeViewModel by viewModels()
    override fun onFragmentCreated() {
        mViewBinding.clickListener= this
       mViewBinding.title =getString(R.string.step_challenges)
        mViewBinding.commonLayout.ivBack.setOnClickListener { onManualBackPressed() }
    }

    override fun subscribeObservers() {
        //do letter
    }

    fun btnContinue(view:View){
        navigateTo(R.id.stepSubmitFragment)
    }
    fun btnNotInterested(view:View){
       onManualBackPressed()
    }
}