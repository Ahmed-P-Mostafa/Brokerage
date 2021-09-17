package com.polotika.brokerage.ui.forget

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.polotika.brokerage.R
import com.polotika.brokerage.base.BaseFragment
import com.polotika.brokerage.base.BaseNavigator
import com.polotika.brokerage.base.BaseViewModel
import com.polotika.brokerage.databinding.FragmentForgetPasswordBinding
import com.polotika.brokerage.ui.onBoard.OnBoardViewModel


class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding,OnBoardViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.resetPasswordBtn.setOnClickListener {
            Toast.makeText(requireContext(), "Reset email sent", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }

    override fun initializeLayout() = R.layout.fragment_forget_password

    override fun initializeViewModel()  =  OnBoardViewModel::class.java
}