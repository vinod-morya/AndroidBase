package com.company.product.LoginModule.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName




class LoginBean {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("status_code")
    @Expose
    var statusCode: Int? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("data")
    @Expose
    var data: Data? = null

    inner class UserState {

        @SerializedName("sID")
        @Expose
        var sid: Int? = null
        @SerializedName("Item")
        @Expose
        var item: String? = null

    }


    inner class Data {

        @SerializedName("user_type")
        @Expose
        var userType: String? = null
        @SerializedName("user_state")
        @Expose
        var userState: List<UserState>? = null
        @SerializedName("ECN")
        @Expose
        var ecn: String? = null
        @SerializedName("api_token")
        @Expose
        var apiToken: String? = null
        @SerializedName("Username")
        @Expose
        var username: String? = null


        fun geteCN(): String? {
            return ecn
        }

        fun seteCN(eCN: String) {
            this.ecn = eCN
        }

    }

}


