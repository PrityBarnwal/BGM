package com.applaunch.bgm.views.fragment.account_activated

import androidx.fragment.app.viewModels
import com.applaunch.appbase.utils_base.navigateNew
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentAccountActivatedBinding
import com.applaunch.bgm.state.account_activated.AccountActivatedState
import com.applaunch.bgm.viewmodel.fragment.account_activated.AccountActivatedViewModel
import doNothing

class AccountActivatedFragment :
    BaseFragment<AccountActivatedViewModel, FragmentAccountActivatedBinding>() {
    override val mViewModel: AccountActivatedViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_account_activated

    override fun onFragmentCreated() {
        mViewBinding.clickListener = this
        mViewBinding.title =getString(R.string.account_activated)

        mViewBinding.btnActivatedContinue.setOnClickListener {
            mViewModel.navigateToHome()
        }
    }

    override fun subscribeObservers() {

        mViewModel.stateObserver.observe(this) {
            when (it) {
                is AccountActivatedState.NavigateToHome -> navigateNew(R.id.navigation_home)
                else -> {
                    doNothing()
                }
            }
        }
    }
}