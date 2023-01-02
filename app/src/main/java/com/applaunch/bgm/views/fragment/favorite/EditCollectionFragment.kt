package com.applaunch.bgm.views.fragment.favorite

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.applaunch.appbase.utils_base.*
import com.applaunch.appbase.view_base.BaseFragment
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.FragmentEditBinding
import com.applaunch.bgm.databinding.LayoutDialogFavoriteBinding
import com.applaunch.bgm.state.fragment.favorite.EditFragmentState
import com.applaunch.bgm.viewmodel.fragment.favorite.EditCollectionViewModel

class EditCollectionFragment : BaseFragment<EditCollectionViewModel, FragmentEditBinding>() {
    override val layoutId: Int = R.layout.fragment_edit
    override val mViewModel: EditCollectionViewModel by viewModels()
    lateinit var deleteCollectionDialog: Pair<View, Dialog>
    private var collectionId = ""
    private var collectionName = ""

    override fun onFragmentCreated() {
        mViewBinding.clickListener = this

        collectionId = arguments?.getString("edit") ?: ""
        collectionName = arguments?.getString("collectionName") ?: ""
        mViewBinding.etName.afterTextChanged { mViewBinding.isValid = mViewBinding.etName.isNN() }
    }

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this) {
            when (it) {
                is EditFragmentState.SUCCESS -> {
                    val bundle= Bundle()
                    bundle.putString("favoriteId",collectionId)
                    bundle.putString("collectionName",mViewBinding.etName.getValue())
                    navigateTo(R.id.favoriteListFragment,bundle)
                }
                is EditFragmentState.ERROR -> {
                    it.msg
                }
                is EditFragmentState.DeleteCollectionSuccess ->{
                    if (this::deleteCollectionDialog.isInitialized) deleteCollectionDialog.second.dismiss()
                    navigateTo(R.id.navigation_favorites)
                    toastMessage(it.data.message)
                }
                is EditFragmentState.DeleteCollectionError ->{
                    if (this::deleteCollectionDialog.isInitialized) deleteCollectionDialog.second.dismiss()
                    toastMessage(it.msg)
                }
            }
        }
    }

    fun backClick(view:View){
        onManualBackPressed()
    }
    fun deleteCollection(view: View){
        openDeleteCollectionDialog()
    }

    private fun openDeleteCollectionDialog() {
        val binding = LayoutDialogFavoriteBinding.inflate(layoutInflater)
        deleteCollectionDialog = CustomDialog(requireContext()).createCustomDialog(binding)
        binding.tvNo.setOnClickListener {
            deleteCollectionDialog.second.dismiss()
        }
        binding.tvDialogContent.text = getString(R.string.are_you_sure_you_want_to_delete_the_collection)
        binding.tvYes.setOnClickListener {
            mViewModel.deleteCollectionId = collectionId
            mViewModel.deleteEditCollection(iBaseRepoListener)
        }
    }

    fun saveChanges(view: View){
        if (mViewBinding.etName.getValue().isNotEmpty()) {
            mViewModel.editCollectionRequest.collectionName = mViewBinding.etName.getValue()
            mViewModel.collectionId = collectionId
            mViewModel.updateCollection(iBaseRepoListener)
        } else {
            toastMessage(getString(R.string.enterCollection))
        }
    }
}