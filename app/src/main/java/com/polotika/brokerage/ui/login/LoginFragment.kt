package com.polotika.brokerage.ui.login

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.polotika.brokerage.MainActivity
import com.polotika.brokerage.R
import com.polotika.brokerage.base.BaseFragment
import com.polotika.brokerage.databinding.FragmentLoginBinding
import com.polotika.brokerage.pojo.models.Event
import kotlinx.coroutines.flow.collect

class LoginFragment : BaseFragment<FragmentLoginBinding,LoginViewModel>() {
    private val TAG = "LoginFragment"
    private val STATE_KEY = "STATE"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        Log.d(TAG, "onViewCreated: $savedInstanceState")
        if (savedInstanceState==null){
            binding.vm = viewModel
            observers()
        }

        binding.tieEmail.addTextChangedListener {
            viewModel.setEmail( it.toString())
        }
        binding.tiePassword.addTextChangedListener {
            viewModel.setPassword( it.toString())
        }

        binding.loginFb.setOnClickListener {
            Toast.makeText(requireContext(), "Login with Facebook", Toast.LENGTH_SHORT).show()
        }
        binding.loginGo.setOnClickListener {
            Toast.makeText(requireContext(), "Login with Google", Toast.LENGTH_SHORT).show()
        }
        binding.languageBtn.setOnClickListener {
            showDialog(
                getString(R.string.app_name),
                getString(R.string.lang_dialog_message),
                getString(R.string.lang_dialog_confirm),
                { dialog, _ ->
                    val lang = binding.languageBtn.text
                    if (lang == getString(R.string.lang_eng_label))
                        viewModel.setAppLocale(requireContext(), getString(R.string.en_lang_key))
                    else
                        viewModel.setAppLocale(requireContext(), getString(R.string.ar_lang_key))
                    dialog.dismiss()
                    requireActivity().recreate()
                },
                getString(R.string.lang_dialog_cancel)
            )

        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_KEY, true)
    }

    private fun observers(){

        lifecycleScope.launchWhenCreated {
            viewModel.emailCheck.collect {
                Log.d(TAG, "onViewCreated:email check $it")
                when(it){
                    true->{ binding.tilEmail.endIconDrawable?.setTint(resources.getColor(R.color.main_blue))}
                    false->{binding.tilEmail.endIconDrawable?.setTint(resources.getColor(R.color.grey))}
                }
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.passwordCheck.collect {
                Log.d(TAG, "onViewCreated:password check $it")

                when(it){
                    true->{ binding.tilPassword.endIconDrawable?.setTint(resources.getColor(R.color.main_blue))}
                    false->{binding.tilPassword.endIconDrawable?.setTint(resources.getColor(R.color.grey))}
                }
            }


        }
        viewModel.navigationEvent.observe(requireActivity(), Observer {
            when(it){
                is LoginNavigationState.LoginSucceed->{
                    finishLogin()
                }
                is LoginNavigationState.LoginFailed->{
                    showMessage("Please fulfill all fields!")
                }
            }
        })
    }

    private fun finishLogin(){
            requireActivity().startActivityFromFragment(
            this,
            Intent(activity, MainActivity::class.java),
            0,
            ActivityOptions.makeCustomAnimation(requireActivity(),R.anim.from_right,R.anim.to_left).toBundle()
        )
        requireActivity().finish()
    }

    override fun initializeLayout()= R.layout.fragment_login

    override fun initializeViewModel() = LoginViewModel::class.java

}