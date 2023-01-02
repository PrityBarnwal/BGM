package com.applaunch.bgm.views.fragment.challenge

import androidx.fragment.app.viewModels
import com.applaunch.appbase.utils_base.equalIgnore
import com.applaunch.appbase.utils_base.navigateNew
import com.applaunch.appbase.utils_base.navigateTo
import com.applaunch.appbase.utils_base.onBackPressed
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentChallengeBinding
import com.applaunch.bgm.state.fragment.challenge.ChallengeState
import com.applaunch.bgm.viewmodel.fragment.challenge.ChallengeViewModel

class ChallengesFragment : BaseFragment<ChallengeViewModel, FragmentChallengeBinding>() {

    override val layoutId: Int = R.layout.fragment_challenge
    override val mViewModel: ChallengeViewModel by viewModels()

    override fun onFragmentCreated() {

      mViewBinding.clickListener = this

    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this) {
            when (it) {
                is ChallengeState.SUCCESS ->{
                    val value = it.data.value
                    val type = it.data.type
                    if (type.equalIgnore("steps") && value){
                        navigateNew(R.id.stepSubmitFragment)
                    }else if (type.equalIgnore("steps") && !value){
                        navigateNew(R.id.stepChallengeFragment)
                    }else if (type.equalIgnore("relax") && value){
                        navigateNew(R.id.relaxSubmitFragment)
                    }else if (type.equalIgnore("relax") && !value){
                        navigateNew(R.id.relaxationChallengeFragment)
                    }
                }
            }
        }
    }

    fun onClick(type:String){
        mViewModel.checkChallenge(iBaseRepoListener,type)
    }
}