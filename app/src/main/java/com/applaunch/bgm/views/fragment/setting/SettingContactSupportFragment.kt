package com.applaunch.bgm.views.fragment.setting

import android.view.View
import androidx.fragment.app.viewModels
import com.applaunch.appbase.utils_base.navigateNew
import com.applaunch.appbase.utils_base.navigateTo
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentSettingContactSupportBinding
import com.applaunch.bgm.state.fragment.settings.SettingContactSupportState
import com.applaunch.bgm.viewmodel.fragment.setting.SettingContactSupportViewModel

class SettingContactSupportFragment :
    BaseFragment<SettingContactSupportViewModel, FragmentSettingContactSupportBinding>() {

    override val mViewModel: SettingContactSupportViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_setting_contact_support


    override fun onFragmentCreated() {
        mViewBinding.commonLayout.ivBack.setOnClickListener {
          navigateTo(R.id.navigation_setting)
        }
        mViewBinding.clickListener = this
        mViewModel.contactSupport(iBaseRepoListener)
        mViewBinding.title = getString(R.string.contact_support)
        mViewBinding.isSuccess = false

    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this) {
            when (it) {
                is SettingContactSupportState.SUCCESS -> {
                    mViewBinding.contactData = it.data.data
                    mViewBinding.isSuccess = true
                }

                is SettingContactSupportState.ERROR -> {
                    it.msg
                }
            }
        }
    }
    fun contactForm(view:View){
        navigateNew(R.id.settingContactFormFragment)
    }
}