package com.company.product.Support.Network

import com.company.product.LoginModule.Model.LoginBean
import com.company.product.LoginModule.Model.TicketListBean
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject
import java.util.*

class NetworkDataManager private constructor() {

    init {
        apiClient = NetworkClientRx.INSTANCE.api!!
    }

    fun login(userBean: LoginBean, callbacks: NetworkCallbackInterfaces.LoginCallbacks) {
        val query = HashMap<String, Any>()
        query["username"] = "65566"
        query["password"] = "R1O1EE75"

        //		65566   R1O1EE75

        apiClient.userLogin(query)
                .compose(NetworkClientRx.INSTANCE.IoMain())
                .subscribe(object : Subject<LoginBean>() {
                    override fun hasObservers(): Boolean {
                        return false
                    }

                    override fun hasThrowable(): Boolean {
                        return false
                    }

                    override fun hasComplete(): Boolean {
                        return false
                    }

                    override fun getThrowable(): Throwable? {
                        return null
                    }

                    override fun subscribeActual(observer: Observer<in LoginBean>) {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(loginBean: LoginBean) {
                        //						Log.e("vinod response", JSON.toJSONString(loginBean));
                        callbacks.onLoginSuccess(loginBean)
                        //						mTextView.setText(JSON.toJSONString(gankAndroid));
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
    }

    fun getHistory(token: String?, callbacks: NetworkCallbackInterfaces.HistoryCallbacks) {

        apiClient.userHistory(token!!)
                .compose(NetworkClientRx.INSTANCE.IoMain())
                .subscribe(object : Subject<TicketListBean>() {
                    override fun hasObservers(): Boolean {
                        return false
                    }

                    override fun hasThrowable(): Boolean {
                        return false
                    }

                    override fun hasComplete(): Boolean {
                        return false
                    }

                    override fun getThrowable(): Throwable? {
                        return null
                    }

                    override fun subscribeActual(observer: Observer<in TicketListBean>) {

                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(ticketListBean: TicketListBean) {
                        callbacks.onHistorySuccess(ticketListBean)
                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })
    }

    companion object {

        private var instance: NetworkDataManager? = null
        private lateinit var apiClient: ApiInterface

        fun getInstance(): NetworkDataManager {
            if (instance == null) {
                instance = NetworkDataManager()
            }
            return instance as NetworkDataManager
        }
    }

}