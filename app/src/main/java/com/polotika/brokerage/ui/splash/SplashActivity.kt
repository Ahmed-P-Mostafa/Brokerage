package com.polotika.brokerage.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.polotika.brokerage.MainActivity
import com.polotika.brokerage.R
import com.polotika.brokerage.base.BaseActivity
import com.polotika.brokerage.databinding.ActivitySplashBinding
import com.polotika.brokerage.ui.login.LoginActivity
import com.polotika.brokerage.ui.onBoard.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding,SplashViewModel>() {
    private val TAG = "SplashActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        var intent = Intent()

        binding.animationView.playAnimation()

        viewModel.splashNavigationEvent.observe(this, {event->
            Handler().postDelayed(Runnable {
                val mIntent = getAppropriateIntent(event)
                launchActivity(mIntent)
            },3000)
        })

    }

    override fun initializeLayout() = R.layout.activity_splash

    override fun initializeViewModel() = SplashViewModel::class.java

    private fun getAppropriateIntent(event: SplashNavigationEvent):Intent{
       return when(event){
            is SplashNavigationEvent.OnBoardEvent->{
                Intent(this,OnBoardingActivity::class.java)
            }
           is SplashNavigationEvent.HomeEvent->{
               Intent(this,MainActivity::class.java)

           }
           is SplashNavigationEvent.LoginEvent->{
               Intent(this, LoginActivity::class.java)

           }
           else->{
               Intent(this,MainActivity::class.java)
           }
        }

    }


    private fun launchActivity(intent:Intent){
        startActivity(intent)
        finish()
    }
}