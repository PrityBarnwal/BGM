package com.applaunch.bgm.views.fragment.challenge

import android.view.View
import androidx.fragment.app.viewModels
import com.applaunch.appbase.utils_base.navigateTo
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentRelaxationChallengeBinding
import com.applaunch.bgm.viewmodel.fragment.challenge.RelaxationChallengeViewModel

class RelaxationChallengeFragment :
    BaseFragment<RelaxationChallengeViewModel, FragmentRelaxationChallengeBinding>() {

    override val layoutId: Int = R.layout.fragment_relaxation_challenge
    override val mViewModel: RelaxationChallengeViewModel by viewModels()
    override fun onFragmentCreated() {
        mViewBinding.clickListener =this
        mViewBinding.title = getString(R.string.relaxation)
        mViewBinding.commonLayout.ivBack.setOnClickListener { onManualBackPressed() }
    }

    override fun subscribeObservers() {
        //do letter
    }

    fun btnContinue(view:View){
        navigateTo(R.id.relaxSubmitFragment)
    }
    fun notInterested(view: View){
        onManualBackPressed()
    }
}