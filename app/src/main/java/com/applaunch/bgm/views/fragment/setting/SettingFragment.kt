package com.applaunch.bgm.views.fragment.setting

import android.app.Dialog
import android.view.View
import androidx.fragment.app.viewModels
import com.applaunch.appbase.utils_base.CustomDialog
import com.applaunch.appbase.utils_base.navigateTo
import com.applaunch.appbase.utils_base.onBackPressed
import com.applaunch.appbase.utils_base.visible
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.data.LocalData
import com.applaunch.bgm.databinding.FragmentSettingsBinding
import com.applaunch.bgm.databinding.LayoutDialogFavoriteBinding
import com.applaunch.bgm.state.fragment.settings.SettingFragmentState
import com.applaunch.bgm.viewmodel.fragment.setting.SettingFragmentViewModel

class SettingFragment:BaseFragment<SettingFragmentViewModel,FragmentSettingsBinding>() {

    lateinit var resetDialog:Pair<View,Dialog>
    override val mViewModel : SettingFragmentViewModel by viewModels()
    override val layoutId:Int = R.layout.fragment_settings
    override fun onFragmentCreated() {

        mViewModel.initSettingList(LocalData.getSettingList(requireContext()))

    }

    override fun subscribeObservers() {
       mViewModel.stateObserver.observe(this){
           when(it){
               is SettingFragmentState.NavigateContact  -> navigateTo(R.id.settingContactFragment)
               is SettingFragmentState.NavigateTermCondition  -> navigateTo(R.id.settingTermConditionFragment)
               is SettingFragmentState.NavigateDataPrivacy  -> navigateTo(R.id.dataPrivacyFragment)
               is SettingFragmentState.NavigateResetApp  -> openResetDialog()
               is SettingFragmentState.SettingList -> mViewBinding.recSettings.adapter = it.adapter
           }
       }
    }

    private fun openResetDialog(){
        val binding= LayoutDialogFavoriteBinding.inflate(layoutInflater)
        resetDialog = CustomDialog(requireContext()).createCustomDialog(binding)
        binding.tvDialogContent.text=getString(R.string.are_you_sure_you_want_to_reset_the_app)
        binding.tvContentSetting.visible()
        binding.tvYes.setOnClickListener {
            if (this::resetDialog.isInitialized) resetDialog.second.dismiss()
            navigateTo(R.id.settingRestartFragment)
        }
        binding.tvNo.setOnClickListener {
            resetDialog.second.dismiss()
        }
    }
}