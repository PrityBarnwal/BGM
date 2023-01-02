package com.applaunch.bgm.views.fragment.challenge

import android.view.View
import androidx.fragment.app.viewModels
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentRelaxSubmitBinding
import com.applaunch.bgm.utils.openDialogSubmitted
import com.applaunch.bgm.viewmodel.fragment.challenge.RelaxSubmitViewModel

class RelaxSubmitFragment : BaseFragment<RelaxSubmitViewModel,FragmentRelaxSubmitBinding>() {

    override val layoutId: Int= R.layout.fragment_relax_submit
    override val mViewModel:RelaxSubmitViewModel by viewModels()
    override fun onFragmentCreated() {
        mViewBinding.title =getString(R.string.relaxation)
        mViewBinding.commonLayout.ivBack.setOnClickListener { onManualBackPressed() }
        mViewBinding.clickListener = this
    }

    override fun subscribeObservers() {
        //do letter
    }

    fun btnSubmit(view:View){
        openDialogSubmitted(requireContext(),getString(R.string.succesfully_submitted))
    }
    fun cancel(view: View){
        onManualBackPressed()
    }
}