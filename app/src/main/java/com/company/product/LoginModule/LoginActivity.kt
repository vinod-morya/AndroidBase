package com.company.product.LoginModule

import am.appwise.components.ni.NoInternetDialog
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.alibaba.fastjson.JSON
import com.company.product.LoginModule.Contracts.LoginContracts
import com.company.product.LoginModule.Model.LoginBean
import com.company.product.LoginModule.Model.TicketListBean
import com.company.product.LoginModule.Model.UserLoginModel
import com.company.product.LoginModule.Presenter.LoginActivityPresenterImpl
import com.company.product.LoginModule.ViewModel.UserViewModel
import com.company.product.R
import com.company.product.Support.LottieDialogFragment
import com.company.product.Support.Network.NetworkCallbackInterfaces
import com.company.product.Support.NetworkState.NetworkStateReceiver
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.login_view.*


class LoginActivity : AppCompatActivity(), LoginContracts.ILoginView, View.OnClickListener,
        NetworkCallbackInterfaces.DataManagerCallbacks, NetworkCallbackInterfaces.LoginCallbacks, NetworkStateReceiver.NetworkStateReceiverListener {
    private var mToken: String? = null
    private lateinit var mLoginPresenter: LoginContracts.LoginPresenter
    private var userViewModel: UserViewModel? = null
    private var noInternetDialog: NoInternetDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_view)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        mLoginPresenter = LoginActivityPresenterImpl(this);
        initObservers()
        initView()

//        val dispatcher = FirebaseJobDispatcher(GooglePlayDriver(this.getApplicationContext()))
//
//        val job = dispatcher.newJobBuilder().setService(ConnectivityJob::class.java)
//                .setTag("connectivity-job").setLifetime(Lifetime.FOREVER).setRetryStrategy(RetryStrategy.DEFAULT_LINEAR)
//                .setRecurring(true).setReplaceCurrent(true).setTrigger(Trigger.executionWindow(0, 5)).build()
//        dispatcher.schedule(job)

        noInternetDialog = NoInternetDialog.Builder(this).setCancelable(true)
                .setBgGradientStart(ContextCompat.getColor(this, R.color.green_300))
                .setBgGradientEnd(ContextCompat.getColor(this, R.color.green_900))
                .setBgGradientCenter(ContextCompat.getColor(this, R.color.green_600))
                .setButtonColor(ContextCompat.getColor(this, R.color.blue_600))
                .setDialogRadius(20f).build()
    }

    private fun initObservers() {
        observerPersonListResults(userViewModel?.getPersonsByCityLive()!!)
        observePersonByMobile(userViewModel?.getPersonsByMobile()!!)
    }

    private fun initView() {
        btnRbSignUp!!.setOnClickListener(this)
        btnRbLogin!!.setOnClickListener(this)
        initLoader()
    }

    private fun initLoader() {
        val lottieDialog = LottieDialogFragment().newInstance(null, true)
        lottieDialog.isCancelable = true
        lottieDialog.show(fragmentManager, "lottieDialog2")
    }

    override fun onClick(view: View) {

        when (view.id) {
            R.id.btnRbSignUp -> {
                if (mToken != null)
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
        userViewModel?.setMobile((etSearch).text.toString())
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

    override fun networkAvailable() {
        Logger.d("Network available")
        Log.d("app", "Network available")
    }

    override fun networkUnavailable() {
        Logger.e("Network unavailable")
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

    override fun onDestroy() {
        super.onDestroy()
        noInternetDialog!!.onDestroy()
    }

}
