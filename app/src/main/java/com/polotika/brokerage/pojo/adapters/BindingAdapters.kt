package com.polotika.brokerage.pojo.adapters

import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator



@BindingAdapter("setImage")
fun setSrcImage(iv:ImageView,src:Int){
    iv.setImageResource(src)
}

@BindingAdapter(value = ["viewPager"], requireAll = false)
fun TabLayout.setViewPager(viewPager: ViewPager2) {
    viewPager.viewTreeObserver?.addOnGlobalLayoutListener(object :
        ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            TabLayoutMediator(this@setViewPager, viewPager) { _, _ -> }.attach()
            viewPager.viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    })
}
