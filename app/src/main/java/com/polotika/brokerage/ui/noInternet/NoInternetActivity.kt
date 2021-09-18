package com.polotika.brokerage.ui.noInternet

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.polotika.brokerage.MainActivity
import com.polotika.brokerage.R
import com.polotika.brokerage.base.BaseActivity
import com.polotika.brokerage.databinding.ActivityNoInternetBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NoInternetActivity : BaseActivity<ActivityNoInternetBinding, NoInternetViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityNoInternetBinding>(
            this,
            R.layout.activity_no_internet
        )

        binding.wifiIv.setOnClickListener {
            showLoader()
            isInternetAvailable().run {
                lifecycleScope.launch {
                    delay(1000)
                    when (this@run) {
                        true -> {
                            startActivity(Intent(this@NoInternetActivity, MainActivity::class.java))
                            finish()
                        }
                        false -> {
                            Snackbar.make(
                                binding.root,
                                getString(R.string.no_internet_connection),
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                    hideLoader()

                }

            }
        }

    }

    override fun initializeLayout() = R.layout.activity_no_internet

    override fun initializeViewModel() = NoInternetViewModel::class.java
}

