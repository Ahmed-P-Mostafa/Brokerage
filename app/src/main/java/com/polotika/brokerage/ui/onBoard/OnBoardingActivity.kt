package com.polotika.brokerage.ui.onBoard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.polotika.brokerage.MainActivity
import com.polotika.brokerage.R
import com.polotika.brokerage.databinding.ActivityOnBoardingBinding
import com.polotika.brokerage.pojo.adapters.BoardsViewPagerAdapter
import com.polotika.brokerage.pojo.adapters.FadePageTransformer

class OnBoardingActivity : AppCompatActivity() {
    lateinit var binding: ActivityOnBoardingBinding
    lateinit var viewModel: OnBoardViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_boarding)
        viewModel = ViewModelProvider(this).get(OnBoardViewModel::class.java)
        val adapter = BoardsViewPagerAdapter(viewModel.pages.value ?: emptyList())
        binding.viewPager.adapter = adapter
        binding.viewPager.setPageTransformer(FadePageTransformer())

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    in 0..1 -> {
                        binding.nextBtn.text = getString(R.string.next)
                    }
                    else -> {
                        binding.nextBtn.text = getString(R.string.done)
                    }
                }
            }
        })

        binding.nextBtn.setOnClickListener {
            when (binding.viewPager.currentItem) {
                in 0..1 -> {
                    binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1)
                }
                else -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        }
    }
}