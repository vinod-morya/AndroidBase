package com.company.product.LoginModule.Interactor

import com.company.product.LoginModule.Contracts.LoginContracts
import com.company.product.LoginModule.Model.LoginBean
import com.company.product.LoginModule.Model.TicketListBean
import com.company.product.Support.Network.NetworkCallbackInterfaces
import com.company.product.Support.Network.NetworkDataManager

class LoginActivityInteractorImpl : LoginContracts.LoginInteractor, NetworkCallbackInterfaces.HistoryCallbacks, NetworkCallbackInterfaces.LoginCallbacks {


    private lateinit var onSignUpInitiatedListener: LoginContracts.LoginInteractor.onSignUpInitiatedListener
    private lateinit var onLoginInitiatedListener: LoginContracts.LoginInteractor.onLoginInitiatedListener


    override fun onSignUpInitiated(onSignUpInitiatedListener: LoginContracts.LoginInteractor.onSignUpInitiatedListener, mToken: String?) {
        this.onSignUpInitiatedListener = onSignUpInitiatedListener
        NetworkDataManager.getInstance().getHistory(mToken, this)
    }

    override fun onLoginInitiated(onLoginInitiatedListener: LoginContracts.LoginInteractor.onLoginInitiatedListener, bean: LoginBean) {
        this.onLoginInitiatedListener = onLoginInitiatedListener
        NetworkDataManager.getInstance().login(bean, this)
    }

    override fun onHistorySuccess(loginBean: TicketListBean) {
        onSignUpInitiatedListener.onSignUpDataSuccess(loginBean)
        // save data in database here.
    }

    override fun onHistoryFailed(loginError: String) {
        onSignUpInitiatedListener.onSignUpDataFailed("Signup failed. Try again")
    }

    override fun onLoginSuccess(loginBean: LoginBean) {
        onLoginInitiatedListener.onLoginSuccess(loginBean)
    }

    override fun onLoginFailed(loginError: String) {
        onLoginInitiatedListener.onLoginFail("Login Failed. Try again")
    }
}