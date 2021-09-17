package com.polotika.brokerage.utils

import android.app.ActivityOptions
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.polotika.brokerage.MainActivity
import com.polotika.brokerage.R

@BindingAdapter("goToRegister")
fun goToRegister(textView: TextView,value:Boolean){
    when(value){
        true->{
            textView.setOnClickListener {
                it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }
}
@BindingAdapter("goToForgetPassword")
fun goToForgerPassword(textView: TextView,value:Boolean){
    when(value){
        true->{
            textView.setOnClickListener {
                it.findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
            }
        }
    }
}
