package com.company.product.LoginModule

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.alibaba.fastjson.JSON
import com.company.product.LoginModule.Contracts.LoginContracts
import com.company.product.LoginModule.Model.LoginBean
import com.company.product.LoginModule.Model.TicketListBean
import com.company.product.LoginModule.Model.UserLoginModel
import com.company.product.LoginModule.Presenter.LoginActivityPresenterImpl
import com.company.product.LoginModule.ViewModel.UserViewModel
import com.company.product.R
import com.company.product.Support.Network.NetworkCallbackInterfaces
import com.orhanobut.logger.Logger


/**
 * Created by Pawan on 6/7/2018.
 */

class LoginActivity : AppCompatActivity(), LoginContracts.ILoginView, View.OnClickListener, NetworkCallbackInterfaces.DataManagerCallbacks, NetworkCallbackInterfaces.LoginCallbacks {
    private var btnRbSignUp: Button? = null
    private var btnRbLogin: Button? = null
    private var tvHistory: TextView? = null
    private var mToken: String? = null
    private lateinit var mLoginPresenter: LoginContracts.LoginPresenter
    private var userViewModel: UserViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_view)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        mLoginPresenter = LoginActivityPresenterImpl(this);
        initObservers()
        initView()
    }

    private fun initObservers() {
        observerPersonListResults(userViewModel?.getPersonsByCityLive()!!)
        observePersonByMobile(userViewModel?.getPersonsByMobile()!!)
    }

    private fun initView() {

        tvHistory = findViewById(R.id.tvHistory)
        btnRbSignUp = findViewById(R.id.btnRbSignUp)
        btnRbSignUp!!.setOnClickListener(this)

        btnRbLogin = findViewById(R.id.btnRbLogin)
        btnRbLogin!!.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.btnRbSignUp -> {
                if(mToken != null)
                mLoginPresenter.onSignUpClick(mToken)
            }

            R.id.btnRbLogin -> {
                val bean = LoginBean()
                bean.message = "abc"
                mLoginPresenter.onLoginClick(bean)
            }
        }
    }

    fun getPersonByMobile(view: View) {
        userViewModel?.setMobile((findViewById<View>(R.id.etSearch) as EditText).text.toString())
    }

    override fun hideProgress() {

    }

    override fun showProgress() {

    }

    override fun onError(error: Throwable) {

    }

    override fun onSignUpDataReceived(ticketListBean: TicketListBean) {
        tvHistory!!.text = JSON.toJSONString(ticketListBean.data)
    }

    override fun onLoginDataReceived(loginBean: LoginBean) {
        mToken = loginBean?.data?.apiToken
        tvHistory!!.text = mToken
        Logger.json(JSON.toJSONString(loginBean))
        var model = UserLoginModel.ResultBean()
        model.userId = loginBean.data?.ecn!!
        model.userFullName = loginBean.data?.username
        model.userEmail = "abc@abc.com"
        model.userLocation = "noida"
        model.userPhone = "9971034666"
        userViewModel?.addPerson(model)

        var modelSql = UserLoginModel()
        modelSql.resultBean = model
//        DatabaseManager.saveMasterUser(this, modelSql)
    }


    override fun onLoginSuccess(loginBean: LoginBean) {

    }

    override fun onLoginFailed(loginError: String) {

    }

    private fun observerPersonListResults(personsLive: LiveData<List<UserLoginModel.ResultBean>>) {
        //observer LiveData
        personsLive.observe(this, object : Observer<List<UserLoginModel.ResultBean>> {
            override fun onChanged(person: List<UserLoginModel.ResultBean>?) {
                if (person == null) {
                    return
                }
                Toast.makeText(this@LoginActivity, "Number of person objects in the response: " + person.size, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun observePersonByMobile(personByMob: LiveData<UserLoginModel.ResultBean>) {
       var ober = personByMob.observe(this, Observer<UserLoginModel.ResultBean> { user ->
            if (user == null) {
                tvHistory?.text = "No data found!! Try again"
                return@Observer
            }

            tvHistory?.text = JSON.toJSONString(user)
        })
    }
}
