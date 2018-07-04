package com.company.product.Support.Network

import com.company.product.LoginModule.Model.LoginBean
import com.company.product.LoginModule.Model.TicketListBean
import io.reactivex.Observable
import ren.yale.android.retrofitcachelibrx2.anno.Cache
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*
import java.util.concurrent.TimeUnit


interface ApiInterface {

    @Cache(time = 5, timeUnit = TimeUnit.SECONDS)
    @POST("login")
    fun userLogin(@Body map: HashMap<String, Any>): Observable<LoginBean>

    @Cache(time = 1, timeUnit = TimeUnit.SECONDS)
    @GET("ticketHistory/{api_token}")
    fun userHistory(@Path("api_token") api_token: String): Observable<TicketListBean>

    //    @Cache(time = 10,timeUnit = TimeUnit.SECONDS)
    //    @GET("Android/10/3")
    //    Observable<GankAndroid> getGankAndroid(@Query("aa") String aa);
    //
    //    @Mock(value = "{\"error\":false,\"results\":[{\"_id\":\"5941f5f3421aa92c7be61c16\",\"createdAt\":\"2017-06-15T10:50:27.317Z\",\"desc\":\"22222222\\\\u4effNice\\\\u9996\\\\u9875\\\\u56fe\\\\u7247\\\\u5217\\\\u88689\\\\u56fe\\\\u6837\\\\u5f0f\\\\uff0c\\\\u5e76\\\\u5b9e\\\\u73b0\\\\u62d6\\\\u62fd\\\\u6548\\\\u679c\",\"images\":[\"http://img.gank.io/4f54c011-e293-436a-ada1-dc03669ffb10\"],\"publishedAt\":\"2017-06-15T13:55:57.947Z\",\"source\":\"web\",\"type\":\"Android\",\"url\":\"http://www.jianshu.com/p/0ea96b952170\",\"used\":true,\"who\":\"vinod1\"}]}")
    //    @GET("Android/10/4")
    //    Observable<GankAndroid> getRamMockGankAndroid();
    //
    //    @Mock(url = "http://gank.io/api/data/Android/10/2")
    //    @GET("Android/10/1")
    //    Observable<GankAndroid> getUrlMockGankAndroid();
    //
    //    @Mock(assets = "mock/mock.json")
    //    @GET("Android/10/5")
    //    Observable<GankAndroid> getAssetsMockGankAndroid();

}
