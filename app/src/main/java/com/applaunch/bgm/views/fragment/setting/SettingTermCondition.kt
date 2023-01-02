package com.applaunch.bgm.views.fragment.setting

import androidx.fragment.app.viewModels
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentSettingTermConditionBinding
import com.applaunch.bgm.viewmodel.fragment.setting.TermConditionViewModel

class SettingTermCondition:BaseFragment<TermConditionViewModel,FragmentSettingTermConditionBinding>() {

    override val mViewModel: TermConditionViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_setting_term_condition
    override fun onFragmentCreated() {
        mViewBinding.title =getString(R.string.terms_condition)
        mViewBinding.commonLayout.ivBack.setOnClickListener { onManualBackPressed()}

    }

    override fun subscribeObservers() {
       //do letter
    }

}