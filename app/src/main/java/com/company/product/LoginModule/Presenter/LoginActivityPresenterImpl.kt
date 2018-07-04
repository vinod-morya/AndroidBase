package com.company.product.LoginModule.Presenter

import com.alibaba.fastjson.JSON
import com.company.product.LoginModule.Contracts.LoginContracts
import com.company.product.LoginModule.Interactor.LoginActivityInteractorImpl
import com.company.product.LoginModule.LoginActivity
import com.company.product.LoginModule.Model.LoginBean
import com.company.product.LoginModule.Model.TicketListBean
import com.company.product.LoginModule.Model.UserLoginModel
import com.company.product.Support.Database.DatabaseManager
import com.orhanobut.logger.Logger

class LoginActivityPresenterImpl : LoginContracts.LoginPresenter, LoginContracts.LoginInteractor.onSignUpInitiatedListener, LoginContracts.LoginInteractor.onLoginInitiatedListener {
    private var mILoginView: LoginContracts.ILoginView
    private var mLoginInteractor: LoginContracts.LoginInteractor

    constructor(ILoginView: LoginActivity) {
        this.mILoginView = ILoginView
        this.mLoginInteractor = LoginActivityInteractorImpl()
    }

    override fun onLoginClick(bean: LoginBean) {
        mLoginInteractor.onLoginInitiated(this, bean)
    }

    override fun onSignUpClick(mToken: String?) {
        mILoginView.showProgress()
        mLoginInteractor.onSignUpInitiated(this, mToken)
    }

    override fun onSignUpDataSuccess(loginBean: TicketListBean) {
        mILoginView.hideProgress()
        mILoginView.onSignUpDataReceived(loginBean)
    }

    override fun onSignUpDataFailed(s: String) {
    }

    override fun onLoginSuccess(loginBean: LoginBean) {
        mILoginView.onLoginDataReceived(loginBean)
        Logger.json(JSON.toJSONString(loginBean))
        var model = UserLoginModel()
        model.resultBean?.userId= loginBean.data?.ecn!!
        model.resultBean?.userFullName = loginBean.data?.username
        model.resultBean?.userEmail = "vinod@vinodmorya.com"
        model.resultBean?.userLocation = "noida"
        model.resultBean?.userPhone = "9971034666"
        DatabaseManager.saveMasterUser(mILoginView as LoginActivity, model)
    }

    override fun onLoginFail(s: String) {

    }
}