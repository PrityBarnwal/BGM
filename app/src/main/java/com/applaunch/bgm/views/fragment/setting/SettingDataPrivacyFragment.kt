package com.applaunch.bgm.views.fragment.setting

import androidx.fragment.app.viewModels
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentDataPrivacyBinding
import com.applaunch.bgm.viewmodel.fragment.setting.DataPrivacyViewModel

class SettingDataPrivacyFragment:BaseFragment<DataPrivacyViewModel,FragmentDataPrivacyBinding>() {

    override val mViewModel: DataPrivacyViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_data_privacy
    override fun onFragmentCreated() {
        mViewBinding.title =getString(R.string.data_privacy)
        mViewBinding.commonLayout.ivBack.setOnClickListener { onManualBackPressed() }

    }

    override fun subscribeObservers() {
       //do letter
    }
}