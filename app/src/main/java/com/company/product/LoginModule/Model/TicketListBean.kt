package com.company.product.LoginModule.Model

import com.google.gson.annotations.Expose

import java.util.ArrayList

/**
 * Created by Shivam on 3/5/2018.
 */

class TicketListBean {

    @Expose
    var status: String? = null
    @Expose
    var statusCode: Int? = null
    @Expose
    var message: String? = null
    @Expose
    var data: ArrayList<TicketHistoryBean>? = null
}