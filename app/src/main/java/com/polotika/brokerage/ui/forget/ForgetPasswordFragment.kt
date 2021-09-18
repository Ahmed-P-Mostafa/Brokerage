package com.polotika.brokerage.ui.forget

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.polotika.brokerage.R
import com.polotika.brokerage.base.BaseFragment
import com.polotika.brokerage.databinding.FragmentForgetPasswordBinding


class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding,ForgetPasswordViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel

        if (!isInternetAvailable()) {
            Snackbar.make(
                requireContext(),
                binding.root,
                getString(R.string.no_internet_connection),
                Snackbar.LENGTH_LONG
            ).show()
            findNavController().navigateUp()
        }


        binding.resetPasswordBtn.setOnClickListener {
            if (!viewModel.email.value.isNullOrBlank()){

                when(viewModel.sendRequest(viewModel.email.value?:"" )){
                    true->{
                        Toast.makeText(requireContext(), getString(R.string.email_sent), Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                    }
                    false->{
                        Toast.makeText(requireContext(), getString(R.string.email_error_occured), Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                    }
                }
            }
        }



    }

    override fun initializeLayout() = R.layout.fragment_forget_password

    override fun initializeViewModel()  =  ForgetPasswordViewModel::class.java
}