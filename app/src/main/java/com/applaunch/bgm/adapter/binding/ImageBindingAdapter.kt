package com.applaunch.bgm.adapter.binding

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.applaunch.bgm.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.*
import com.bumptech.glide.request.RequestOptions


@BindingAdapter(value = ["imgId"], requireAll = false)
fun loadImage(view: ImageView, imgId: Int) {
    view.setImageResource(imgId)
}

@SuppressLint("CheckResult")
@BindingAdapter(
    value = ["imageUrl", "radius", "round", "radiusCenterCrop"],
    requireAll = false
)
fun loadImageFromUrl(
    view: ImageView,
    imageUrl: String,
    radius: Int,
    round: Boolean,
    radiusCenterCrop: Int,

    ) {
    val requestOption = RequestOptions()

    if (round)
        requestOption.transform(CircleCrop())
    if (radius > 0) {
        requestOption.transform(
            RoundedCorners(radius)
        )
    }
    if (radiusCenterCrop > 0)
        requestOption.transform(CenterCrop(), RoundedCorners(radiusCenterCrop))

    Glide.with(view.context)
        .setDefaultRequestOptions(requestOption)
        .load(BuildConfig.IMAGE_BASE_URL + imageUrl)
        .placeholder(com.applaunch.appbase.R.drawable.ic_place_holder).into(view)

}


