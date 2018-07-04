package com.company.product.SplashModule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler

import com.company.product.LoginModule.LoginActivity
import com.company.product.Support.Network.NetworkClientRx
import com.company.product.R

import ren.yale.android.retrofitcachelibrx2.RetrofitCache

/**
 * Created by Pawan on 5/31/2018.
 */

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_view)
        NetworkClientRx.INSTANCE.init(this.application)
        RetrofitCache.getInstance().init(this).enableMock(true)
        RetrofitCache.getInstance().addIgnoreParam("api_token")
        Handler().postDelayed(/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

        {
            val i = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {
        private val SPLASH_TIME_OUT = 1500
    }
}
