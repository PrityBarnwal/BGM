package com.applaunch.bgm.views.fragment.challenge

import android.view.View
import androidx.fragment.app.viewModels
import com.applaunch.appbase.utils_base.navigateTo
import com.applaunch.appbase.utils_base.onClick
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentStepSubmitBinding
import com.applaunch.bgm.utils.openDialogSubmitted
import com.applaunch.bgm.viewmodel.fragment.challenge.StepSubmitViewModel

class StepSubmitFragment : BaseFragment<StepSubmitViewModel,FragmentStepSubmitBinding>() {

    override val layoutId: Int = R.layout.fragment_step_submit
    override val mViewModel: StepSubmitViewModel by viewModels()
    override fun onFragmentCreated() {
        mViewBinding.title =getString(R.string.step_challenges)
        mViewBinding.commonLayout.ivBack.onClick { navigateTo(R.id.navigation_challenges) }
        mViewBinding.clickListener = this
    }

    override fun subscribeObservers() {
        //do letter
    }

    fun btnSubmit(view:View){
        openDialogSubmitted(requireContext(),getString(R.string.succesfully_submitted))
    }
    fun cancel(view: View){
        navigateTo(R.id.navigation_challenges)
    }
}