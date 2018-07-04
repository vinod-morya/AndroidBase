package com.company.product.Support.Network

import com.company.product.LoginModule.Model.LoginBean
import com.company.product.LoginModule.Model.TicketListBean

class NetworkCallbackInterfaces {

    interface LoginCallbacks {
        fun onLoginSuccess(loginBean: LoginBean)

        fun onLoginFailed(loginError: String)
    }

    interface HistoryCallbacks {
        fun onHistorySuccess(loginBean: TicketListBean)

        fun onHistoryFailed(loginError: String)
    }

    interface DataManagerCallbacks {
        fun onError(error: Throwable)
    }
}
