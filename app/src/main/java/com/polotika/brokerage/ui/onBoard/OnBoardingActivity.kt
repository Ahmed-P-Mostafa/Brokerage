package com.polotika.brokerage.ui.onBoard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.polotika.brokerage.LoginActivity
import com.polotika.brokerage.R
import com.polotika.brokerage.base.BaseActivity
import com.polotika.brokerage.databinding.ActivityOnBoardingBinding
import com.polotika.brokerage.pojo.adapters.BoardsViewPagerAdapter
import com.polotika.brokerage.pojo.adapters.FadePageTransformer
import com.polotika.brokerage.pojo.adapters.setViewPager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding, OnBoardViewModel>() {
    private val TAG = "OnBoardingActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = BoardsViewPagerAdapter(viewModel.pages.value ?: emptyList())
        binding.viewPager.adapter = adapter
        binding.viewPager.setPageTransformer(FadePageTransformer())
        binding.tabLayout.setViewPager(binding.viewPager)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setCurrentPage(position)
            }
        })

        binding.nextBtn.setOnClickListener {
            viewModel.setCurrentPage(binding.viewPager.currentItem + 1)
        }
        binding.skipBtn.setOnClickListener {
            viewModel.completeOnBoarding()
        }

        viewModel.navigationEvent.observe(this, Observer {
            when (it) {
                is OnBoardingFinish -> {
                    finishOnBoarding()
                }
            }
        })

        viewModel.currentPage.observe(this, Observer { position ->
            Log.d(TAG, "onCreate: $position")
            when (position) {
                in 0..1 -> {
                    binding.viewPager.currentItem = position
                    binding.nextBtn.text = getString(R.string.next)
                }
                else -> {
                    binding.viewPager.currentItem = position
                    binding.nextBtn.text = getString(R.string.done)
                }
            }
        })

        binding.languageBtn.setOnClickListener {
            showDialog(
                getString(R.string.app_name),
                getString(R.string.lang_dialog_message),
                getString(R.string.lang_dialog_confirm),
                { dialog, _ ->
                    val lang = binding.languageBtn.text
                    if (lang == getString(R.string.lang_eng_label))
                        viewModel.setAppLocale(this, getString(R.string.en_lang_key))
                    else
                        viewModel.setAppLocale(this, getString(R.string.ar_lang_key))
                    dialog.dismiss()
                    recreate()
                },
                getString(R.string.lang_dialog_cancel)
            )

        }

    }

    private fun finishOnBoarding() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun initializeLayout() = R.layout.activity_on_boarding

    override fun initializeViewModel() = OnBoardViewModel::class.java

}