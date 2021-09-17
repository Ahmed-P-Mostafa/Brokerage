package com.polotika.brokerage.ui.register

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.polotika.brokerage.MainActivity
import com.polotika.brokerage.R
import com.polotika.brokerage.base.BaseFragment
import com.polotika.brokerage.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding,RegisterViewModel>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
    }

    private fun observers(){
        viewModel.registerNavigationEvent.observe(viewLifecycleOwner, Observer {
            when(it){
                RegisterNavigationState.LoginSucceed->{
                    finishRegister()
                }
                RegisterNavigationState.LoginFailed->{
                    showMessage("Please fulfill all fields!")
                }
            }
        })
    }
    private fun finishRegister(){
        requireActivity().startActivityFromFragment(
            this,
            Intent(activity, MainActivity::class.java),
            0,
            ActivityOptions.makeCustomAnimation(requireActivity(),R.anim.from_right,R.anim.to_left).toBundle()
        )
        requireActivity().finish()
    }



    override fun initializeLayout() = R.layout.fragment_register
    override fun initializeViewModel() = RegisterViewModel::class.java


}