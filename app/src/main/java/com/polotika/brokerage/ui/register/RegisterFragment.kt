package com.polotika.brokerage.ui.register

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.polotika.brokerage.MainActivity
import com.polotika.brokerage.R
import com.polotika.brokerage.base.BaseFragment
import com.polotika.brokerage.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {
    private val TAG = "RegisterFragment"
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
        observers()
    }

    private fun observers() {
        viewModel.registerNavigationEvent.observe(viewLifecycleOwner, Observer {
            when (it) {
                RegisterNavigationState.LoginSucceed -> {
                    finishRegister()
                }
                RegisterNavigationState.LoginFailed -> {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.register_error),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        })
        viewModel.firstNameError.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "observers: firstName $it")
            showViewError(binding.firstNameTil, getString(R.string.first_name_error), it)
        })
        viewModel.lastNameError.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "observers: lastName $it")
            showViewError(binding.lastNameTil, getString(R.string.last_name_error), it)
        })
        viewModel.emailError.observe(viewLifecycleOwner, Observer {
            showViewError(binding.emailTil, getString(R.string.email_error), it)

        })
        viewModel.mobileError.observe(viewLifecycleOwner, Observer {
            showViewError(binding.mobileTil, getString(R.string.mobile_error), it)
        })
        viewModel.passwordError.observe(viewLifecycleOwner, Observer {

            showViewError(binding.passwordTil, getString(R.string.password_error), it)

        })
        viewModel.isChecked.observe(viewLifecycleOwner, Observer {
            when (it) {
                false -> binding.termsCb.error = getString(R.string.terms_error)
                true -> binding.termsCb.error = null
            }


        })
    }

    private fun finishRegister() {
        requireActivity().startActivityFromFragment(
            this,
            Intent(activity, MainActivity::class.java),
            0,
            ActivityOptions.makeCustomAnimation(
                requireActivity(),
                R.anim.from_right,
                R.anim.to_left
            ).toBundle()
        )
        requireActivity().finish()
    }

    private fun showViewError(view: TextInputLayout, message: String?, isError: Boolean) {
        view.isErrorEnabled = isError
        if (isError) {
            view.isErrorEnabled = isError
            view.error = message
        }
    }

    override fun initializeLayout() = R.layout.fragment_register
    override fun initializeViewModel() = RegisterViewModel::class.java


}