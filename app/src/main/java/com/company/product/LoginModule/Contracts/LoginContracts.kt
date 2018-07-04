package com.company.product.LoginModule.Contracts

import com.company.product.LoginModule.Model.LoginBean
import com.company.product.LoginModule.Model.TicketListBean

interface LoginContracts {
    interface ILoginView {
        fun showProgress()
        fun hideProgress()
        fun onSignUpDataReceived(ticketListBean: TicketListBean)
        fun onLoginDataReceived(loginBean: LoginBean)
    }

    interface LoginPresenter {
        fun onLoginClick(bean: LoginBean)
        fun onSignUpClick(mToken: String?)
    }

    interface LoginInteractor {
        fun onSignUpInitiated(onSignUpInitiatedListener: LoginContracts.LoginInteractor.onSignUpInitiatedListener, mToken: String?)
        fun onLoginInitiated(onLoginInitiatedListener: LoginContracts.LoginInteractor.onLoginInitiatedListener, bean: LoginBean)

        interface onSignUpInitiatedListener{
            fun onSignUpDataSuccess(loginBean: TicketListBean)
            fun onSignUpDataFailed(s: String)
        }

        interface onLoginInitiatedListener{
            fun onLoginSuccess(loginBean: LoginBean)
            fun onLoginFail(s: String)
        }
    }
}