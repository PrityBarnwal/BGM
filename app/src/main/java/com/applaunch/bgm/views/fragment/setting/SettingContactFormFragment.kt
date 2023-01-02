package com.applaunch.bgm.views.fragment.setting

import android.view.View
import androidx.fragment.app.viewModels
import com.applaunch.appbase.utils_base.*
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentSettingContactFormBinding
import com.applaunch.bgm.state.fragment.settings.SettingContactFormState
import com.applaunch.bgm.viewmodel.fragment.setting.SettingContactFormViewModel

class SettingContactFormFragment :BaseFragment<SettingContactFormViewModel,FragmentSettingContactFormBinding>() {

    override val mViewModel: SettingContactFormViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_setting_contact_form
    override fun onFragmentCreated() {

        mViewBinding.isValid= false
        mViewBinding.clickListener = this
        mViewBinding.title = getString(R.string.contact_form)

        mViewBinding.commonLayout.ivBack.setOnClickListener { onManualBackPressed() }


        mViewBinding.etEmail.afterTextChanged { mViewBinding.isValid = isValid() }
        mViewBinding.etSubject.afterTextChanged { mViewBinding.isValid = isValid() }
        mViewBinding.etMessage.afterTextChanged { mViewBinding.isValid = isValid() }

    }
    private fun isValid():Boolean{
        return (
                mViewBinding.etSubject.isNN() &&
                mViewBinding.etMessage.isNN())
    }

    fun onSendClick(view:View){
        mViewModel.contactFormRequest.email =  mViewBinding.etEmail.getValue().isValidEmail().toString()
        mViewModel.contactFormRequest.subject =  mViewBinding.etSubject.getValue()
        mViewModel.contactFormRequest.message =  mViewBinding.etMessage.getValue()

        mViewModel.contactForm(iBaseRepoListener)
    }
    override fun subscribeObservers() {
       mViewModel.stateObserver.observe(this){
           when(it){
               is SettingContactFormState.SUCCESS ->{
                   toastMessage(it.data.message)
                   navigateNew(R.id.navigation_setting)
               }
               is SettingContactFormState.ERROR ->{
                   toastMessage(it.msg)
               }
           }
       }
    }
}