package com.company.product.SplashModule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.company.product.LoginModule.LoginActivity
import com.company.product.R
import com.company.product.Support.Network.NetworkClientRx
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import kotlinx.android.synthetic.main.splash_view.*
import ren.yale.android.retrofitcachelibrx2.RetrofitCache



class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_view)

        AppCenter.start(application, "c0fdab1f-df3a-4769-bbd7-d2fc4b96961f",
                Analytics::class.java, Crashes::class.java)
        NetworkClientRx.INSTANCE.init(this.application)
        RetrofitCache.getInstance().init(this).enableMock(true)
        RetrofitCache.getInstance().addIgnoreParam("api_token")
        Glide.with(this).load("https://homepages.cae.wisc.edu/~ece533/images/watch.png").into(imageView)
        Handler().postDelayed(
                {
                    val i = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(i)
                    finish()
                }, SPLASH_TIME_OUT.toLong())
    }

    companion object {
        private val SPLASH_TIME_OUT = 2000
    }
}
