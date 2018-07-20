package com.company.product.Support.Network


import android.content.Context
import android.util.Log
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import ren.yale.android.retrofitcachelibrx2.CacheInterceptorListener
import ren.yale.android.retrofitcachelibrx2.RetrofitCache
import ren.yale.android.retrofitcachelibrx2.intercept.CacheForceInterceptorNoNet
import ren.yale.android.retrofitcachelibrx2.intercept.CacheInterceptorOnNet
import ren.yale.android.retrofitcachelibrx2.transformer.CacheTransformer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by Yale on 2017/6/12.
 */
enum class NetworkClientRx {
    INSTANCE;

    private var mContext: Context? = null
    private val BASE_URL = "yourURL"

    val okHttpClient: OkHttpClient
        get() {
            val clientBuilder = OkHttpClient.Builder()
            clientBuilder.readTimeout(20, TimeUnit.SECONDS)
            clientBuilder.connectTimeout(20, TimeUnit.SECONDS)
            clientBuilder.writeTimeout(20, TimeUnit.SECONDS)
            clientBuilder.addInterceptor(LogInterceptor())
            clientBuilder.addInterceptor(CacheForceInterceptorNoNet())
            clientBuilder.addNetworkInterceptor(CacheInterceptorOnNet())

            val cacheSize = 200 * 1024 * 1024
            val cacheDirectory = File(mContext!!.cacheDir, "httpcache")
            val cache = Cache(cacheDirectory, cacheSize.toLong())
            return clientBuilder.cache(cache).build()
        }


    val api: ApiInterface?
        get() = apiInterface

    fun init(context: Context) {
        mContext = context
        if (apiInterface == null) {
            apiInterface = configRetrofit<ApiInterface>(ApiInterface::class.java, BASE_URL)
        }
        RetrofitCache.getInstance().cacheInterceptorListener = CacheInterceptorListener { request, response -> true }

    }

    private fun <T> configRetrofit(service: Class<T>, url: String): T {
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        RetrofitCache.getInstance().addRetrofit(retrofit)
        return retrofit.create(service)
    }

    fun <T> IoMain(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.compose(CacheTransformer.emptyTransformer()).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    private inner class LogInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val sb = StringBuffer()

            val response = chain.proceed(chain.request())
            val mediaType = response.body()!!.contentType()
            val content = response.body()!!.string()

            sb.append("======== request: " + request.toString() + "\r\n ======== request headers: " + request.headers().toString() + "\r\n======= response header:" + response.headers().toString() +
                    "\r\n---------- response body:\r\n")
            Log.d("retrofitCache", sb.toString())
            try {
                showLog(content)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build()
        }
    }

    companion object {
        private var apiInterface: ApiInterface? = null

        private fun showLog(str: String) {
            var str = str
            str = str.trim { it <= ' ' }
            var index = 0
            val maxLength = 2000
            var finalString = ""

            while (index < str.length) {
                if (str.length <= index + maxLength) {
                    finalString = str.substring(index)
                } else {
                    finalString = str.substring(index, index + maxLength)
                }
                index += maxLength
                Log.d("retrofitCache", finalString.trim { it <= ' ' })
            }
        }
    }
}
