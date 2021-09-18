package com.polotika.brokerage.pojo.adapters

import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.polotika.brokerage.R
import com.polotika.brokerage.pojo.models.ServiceItem


@BindingAdapter("setImage")
fun setSrcImage(iv: ImageView, src: Int) {
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


@BindingAdapter("goToRegister")
fun goToRegister(textView: TextView, value: Boolean) {
    when (value) {
        true -> {
            textView.setOnClickListener {
                it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }
}

@BindingAdapter("serviceDetails")
fun serviceDetails(layout: ConstraintLayout, item: ServiceItem) {
    layout.setOnClickListener {
        Toast.makeText(layout.context, item.name, Toast.LENGTH_SHORT).show()
    }

}

@BindingAdapter("goToForgetPassword")
fun goToForgerPassword(textView: TextView, value: Boolean) {
    when (value) {
        true -> {
            textView.setOnClickListener {
                it.findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
            }
        }
    }
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
            //removeObservers(lifecycleOwner)
        }
    })
}
