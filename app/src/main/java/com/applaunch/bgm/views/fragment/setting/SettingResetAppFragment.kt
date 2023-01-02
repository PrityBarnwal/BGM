package com.applaunch.bgm.views.fragment.setting

import androidx.fragment.app.viewModels
import com.applaunch.appbase.utils_base.gone
import com.applaunch.appbase.utils_base.navigateNew
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentSettingRestartBinding
import com.applaunch.bgm.state.fragment.settings.ResetAppState
import com.applaunch.bgm.viewmodel.fragment.setting.ResetAppViewModel

class SettingResetAppFragment:BaseFragment<ResetAppViewModel,FragmentSettingRestartBinding>() {
    override val mViewModel: ResetAppViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_setting_restart
    override fun onFragmentCreated() {
        mViewBinding.title =getString(R.string.app_reseted)
        mViewBinding.apply {
            commonLayout.apply {
                tvSuccessFullyContent.gone()
                ivProfile.setImageResource(R.drawable.ic_reset)
                btnActivatedContinue.setOnClickListener {
                    mViewModel.navigateToSplash()
                }
            }
        }
    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this){
            when (it) {
                is ResetAppState.NavigateSplash -> navigateNew(R.id.splashFragment)
            }
        }
    }
}