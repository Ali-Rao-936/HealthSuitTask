package com.health.suit.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.health.suit.R

@BindingAdapter ("imageFromUrl")
fun ImageView.imageFromUrl(url:String){
    Glide
        .with(this.context)
        .load(url)
        .placeholder(R.drawable.placeholder)
        .into(this)
}