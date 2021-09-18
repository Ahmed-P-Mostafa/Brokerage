package com.polotika.brokerage.ui.forget

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.polotika.brokerage.R
import com.polotika.brokerage.base.BaseFragment
import com.polotika.brokerage.base.BaseNavigator
import com.polotika.brokerage.base.BaseViewModel
import com.polotika.brokerage.databinding.FragmentForgetPasswordBinding
import com.polotika.brokerage.pojo.adapters.observeOnce
import com.polotika.brokerage.pojo.models.Event
import com.polotika.brokerage.ui.onBoard.OnBoardViewModel


class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding,ForgetPasswordViewModel>() {

    lateinit var observer :Observer<Event>
    private val TAG = "ForgetPasswordFragment"
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

        viewModel.email.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "email: $it")
        })

        observer = Observer<Event> { it ->
                Log.d(TAG, "onViewCreated: $it")
                if (it!=null){
                    when(it){
                        Event.Success-> {
                            Toast.makeText(requireContext(), getString(R.string.email_sent), Toast.LENGTH_SHORT).show()
                            findNavController().navigateUp()
                        }
                        Event.Failed ->{
                            Toast.makeText(requireContext(), getString(R.string.email_error_occured), Toast.LENGTH_SHORT).show()
                            findNavController().navigateUp()
                        }
                        else -> {

                        }
                    }

                }else{
            }

            //viewModel.requestResult.value = null
        }
        viewModel.requestResult.observe(requireActivity(), observer)






        binding.resetPasswordBtn.setOnClickListener {
            Log.d(TAG, "onViewCreated: ${viewModel.email.value}")
            if (!viewModel.email.value.isNullOrBlank()){
                viewModel.sendRequest(viewModel.email.value?:"")
            }
        }



    }

    override fun initializeLayout() = R.layout.fragment_forget_password

    override fun initializeViewModel()  =  ForgetPasswordViewModel::class.java
}