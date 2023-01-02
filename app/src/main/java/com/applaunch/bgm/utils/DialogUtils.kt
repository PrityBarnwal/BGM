package com.applaunch.bgm.utils

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import com.applaunch.bgm.R
import com.applaunch.bgm.databinding.LayoutSubmitSuccessfullyBinding

fun openDialogSubmitted(context: Context,title:String) {
    val dialogSubmit = Dialog(context)
    val dialogBindingSubmit =
        LayoutSubmitSuccessfullyBinding.inflate(LayoutInflater.from(context))
    dialogSubmit.apply {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(dialogBindingSubmit.root)
        dialogBindingSubmit.apply {
            clDialog.setOnClickListener { dismiss() }
            tvTitle.text= title
            window!!.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            window?.setBackgroundDrawable(ColorDrawable(context.getColor(R.color.dark_blur)))
            show()
        }
    }
}
